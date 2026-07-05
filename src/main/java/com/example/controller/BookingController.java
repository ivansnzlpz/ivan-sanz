package com.example.controller;

import com.example.model.Booking;
import com.example.view.BookingView;

import javax.swing.*;
import java.util.List;

public class BookingController {

    private BookingView view;
    private List<Booking> bookings;

    public BookingController(BookingView view, List<Booking> bookings) {
        this.view = view;
        this.bookings = bookings;

        initController();
    }

    private void initController() {
        view.setSearchButtonListener(e -> searchBooking());
        loadBookingIds();
    }

    private void loadBookingIds() {
        DefaultListModel<String> listModel = new DefaultListModel<>();
        for (Booking booking : bookings) {
            listModel.addElement(booking.getId());
        }
        view.setBookingList(listModel);
    }

    private void searchBooking() {
        String bookingId = view.getSearchInput();

        if (bookingId == null || bookingId.isEmpty()) {
            view.showMessage("Please enter a booking ID");
            return;
        }

        Booking found = null;
        for (Booking booking : bookings) {
            if (booking.getId().equalsIgnoreCase(bookingId)) {
                found = booking;
                break;
            }
        }

        if (found != null) {
            view.displayBooking(found);
        } else {
            view.showMessage("Booking not found");
        }
    }
}
