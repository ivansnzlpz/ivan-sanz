package com.example;

import com.example.model.Booking;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.util.List;

// Everything related to reading/writing bookings.json lives here, so Main and
// BookingController don't need to know how the file is actually stored
public class BookingRepository {

    private static final Logger log = LoggerFactory.getLogger(BookingRepository.class);

    // Both loading and saving point at this exact same file path on purpose.
    // Reading it as a classpath resource instead would read the copy Maven puts
    // in target/classes, which could be out of date compared to what we just saved
    private static final String BOOKINGS_PATH = "src/main/resources/bookings.json";

    // Reads bookings.json and turns it into a List<Booking> we can use in the app
    public List<Booking> loadBookings() {
        Gson gson = new Gson();

        try (FileReader reader = new FileReader(BOOKINGS_PATH, StandardCharsets.UTF_8)) {
            Type listType = new TypeToken<List<Booking>>() {}.getType();
            return gson.fromJson(reader, listType);
        } catch (IOException e) {
            log.error("Error reading {}", BOOKINGS_PATH, e);
            throw new RuntimeException(e);
        }
    }

    // Overwrites bookings.json with whatever list is currently in memory.
    // Called every time a booking is created or deleted, so the file always
    // matches what the app is showing
    public void saveBookings(List<Booking> bookings) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        try (FileWriter writer = new FileWriter(BOOKINGS_PATH, StandardCharsets.UTF_8)) {
            gson.toJson(bookings, writer);
        } catch (IOException e) {
            log.error("Error writing to {}", BOOKINGS_PATH, e);
            throw new RuntimeException(e);
        }
    }
}
