package com.example;

import com.example.controller.BookingController;
import com.example.model.Booking;
import com.example.view.BookingView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class Main {

    private static final Logger log = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        // Load whatever bookings are already saved on disk
        BookingRepository repository = new BookingRepository();
        List<Booking> bookings = repository.loadBookings();

        // Just a debug trace so we can confirm everything loaded fine
        for (Booking booking : bookings) {
            log.debug("Loaded booking: {}", booking.getId());
        }

        // Initialize MVC
        BookingView view = new BookingView();
        new BookingController(view, bookings, repository);
    }
}