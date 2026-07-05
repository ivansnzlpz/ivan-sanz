package com.example.controller;

import com.example.BookingRepository;
import com.example.CurrencyConverter;
import com.example.model.Booking;
import com.example.view.BookingFormDialogView;
import com.example.view.BookingView;

import javax.swing.*;
import java.util.List;

public class BookingController {

    private final BookingView view;
    private final List<Booking> bookings;
    private final BookingRepository repository;
    private final CurrencyConverter currencyConverter;

    public BookingController(BookingView view, List<Booking> bookings, BookingRepository repository) {
        this.view = view;
        this.bookings = bookings;
        this.repository = repository;
        this.currencyConverter = new CurrencyConverter();

        initController();
    }

    private void initController() {
        view.setSearchButtonListener(e -> searchBooking());
        view.setCreateButtonListener(e -> createBooking());
        view.setDeleteButtonListener(e -> deleteBooking());
        loadBookingIds();
    }

    // Fills the left-hand list with every booking id currently in memory
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

        Booking found = findBookingById(bookingId);

        if (found != null) {
            // Only ask for the exchange rate once we actually have a booking to show
            double rate = currencyConverter.getEurToUsdRate();
            view.displayBooking(found, rate);
        } else {
            view.showMessage("Booking not found");
        }
    }

    // Opens the "new booking" form, waits for the user to finish, and only
    // adds the booking if the id isn't already taken
    private void createBooking() {
        BookingFormDialogView dialog = new BookingFormDialogView(view);
        dialog.setVisible(true);

        Booking newBooking = dialog.getResult();
        if (newBooking == null) {
            return; // user cancelled, nothing to do
        }

        if (findBookingById(newBooking.getId()) != null) {
            view.showMessage("Ya existe una reserva con ese ID");
            return;
        }

        bookings.add(newBooking);
        repository.saveBookings(bookings);
        loadBookingIds();
        view.showMessage("Reserva creada correctamente");
    }

    // Deletes whatever booking id is currently typed in the search field
    private void deleteBooking() {
        String bookingId = view.getSearchInput();

        if (bookingId == null || bookingId.isEmpty()) {
            view.showMessage("Please enter a booking ID");
            return;
        }

        Booking found = findBookingById(bookingId);

        if (found == null) {
            view.showMessage("Booking not found");
            return;
        }

        bookings.remove(found);
        repository.saveBookings(bookings);
        loadBookingIds();
        view.showMessage("Reserva eliminada correctamente");
    }

    // Shared by search, create (uniqueness check) and delete, so the same
    // "find by id" logic isn't copy-pasted three times
    private Booking findBookingById(String bookingId) {
        for (Booking booking : bookings) {
            if (booking.getId().equalsIgnoreCase(bookingId)) {
                return booking;
            }
        }
        return null;
    }
}
