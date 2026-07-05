package com.example;

import com.example.controller.BookingController;
import com.example.model.Booking;
import com.example.view.BookingView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class Main {

    private static final Logger log = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        List<Booking> bookings = loadBookings("/bookings.json");

        for (Booking booking : bookings) {
            log.debug("Loaded booking: {}", booking.getId());
        }

        // Initialize MVC
        BookingView view = new BookingView();
        new BookingController(view, bookings);
    }

    // Reads bookings.json from the classpath (src/main/resources) and parses it into a list of Booking objects
    private static List<Booking> loadBookings(String resourcePath) {
        Gson gson = new Gson();

        try (InputStream inputStream = Main.class.getResourceAsStream(resourcePath)) {
            if (inputStream == null) {
                throw new IllegalStateException("Resource not found: " + resourcePath);
            }

            try (InputStreamReader reader = new InputStreamReader(inputStream, StandardCharsets.UTF_8)) {
                Type listType = new TypeToken<List<Booking>>() {}.getType();
                return gson.fromJson(reader, listType);
            }
        } catch (IOException e) {
            log.error("Error reading resource {}", resourcePath, e);
            throw new RuntimeException(e);
        }
    }
}