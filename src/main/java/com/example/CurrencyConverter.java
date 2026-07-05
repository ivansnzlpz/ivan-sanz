package com.example;

import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Map;

public class CurrencyConverter {

    private static final Logger log = LoggerFactory.getLogger(CurrencyConverter.class);

    // Free API, no key needed - base currency is EUR so "rates" comes back
    // already relative to the euro
    private static final String API_URL = "https://open.er-api.com/v6/latest/EUR";

    // Backup value in case the API is down or there is no internet connection.
    // This is not the real rate, just a safety net so the app doesn't crash
    private static final double FALLBACK_EUR_TO_USD_RATE = 1.08;

    // Small class that mirrors only the parts of the API response we actually care about.
    // Gson fills this in automatically from the JSON, we don't build it by hand
    private static class ExchangeRateResponse {
        String result;
        Map<String, Double> rates;
    }

    // Asks the API for the current EUR -> USD rate and returns it
    public double getEurToUsdRate() {
        try {
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(API_URL))
                    .GET()
                    .build();

            // Sends the request and waits for the response as plain text
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            // If the server didn't answer with a normal 200 OK, don't even try to parse it
            if (response.statusCode() != 200) {
                log.warn("Exchange rate API returned status {}, using fallback rate", response.statusCode());
                return FALLBACK_EUR_TO_USD_RATE;
            }

            // Turns the JSON text into our small ExchangeRateResponse object
            Gson gson = new Gson();
            ExchangeRateResponse parsed = gson.fromJson(response.body(), ExchangeRateResponse.class);

            // Sanity check: make sure the response actually has what we need before using it
            if (parsed == null || !"success".equals(parsed.result) || parsed.rates == null || !parsed.rates.containsKey("USD")) {
                log.warn("Unexpected exchange rate API response, using fallback rate");
                return FALLBACK_EUR_TO_USD_RATE;
            }

            return parsed.rates.get("USD");

        } catch (IOException | InterruptedException e) {
            // No internet, timeout, etc. - fall back instead of crashing the app
            log.warn("Could not reach exchange rate API, using fallback rate", e);
            if (e instanceof InterruptedException) {
                Thread.currentThread().interrupt();
            }
            return FALLBACK_EUR_TO_USD_RATE;
        }
    }
}
