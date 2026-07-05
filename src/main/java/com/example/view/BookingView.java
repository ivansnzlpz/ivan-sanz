package com.example.view;

import com.example.model.Booking;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class BookingView extends JFrame {

    private JTextField searchField;
    private JButton searchButton;
    private JTextArea resultArea;
    private JList<String> bookingList;

    public BookingView() {
        setTitle("Booking App");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        initComponents();

        setVisible(true);
    }

    private void initComponents() {
        // Top panel (search)
        JPanel topPanel = new JPanel();
        searchField = new JTextField(15);
        searchButton = new JButton("Search");

        topPanel.add(new JLabel("Booking ID:"));
        topPanel.add(searchField);
        topPanel.add(searchButton);

        add(topPanel, BorderLayout.NORTH);

        // Left panel (list)
        bookingList = new JList<>();
        add(new JScrollPane(bookingList), BorderLayout.WEST);

        // Center panel (result)
        resultArea = new JTextArea();
        resultArea.setEditable(false);
        add(new JScrollPane(resultArea), BorderLayout.CENTER);
    }

    public String getSearchInput() {
        return searchField.getText();
    }

    public void setSearchButtonListener(ActionListener listener) {
        searchButton.addActionListener(listener);
    }

    public void setBookingList(DefaultListModel<String> listModel) {
        bookingList.setModel(listModel);
    }

    public void displayBooking(Booking booking) {
        resultArea.setText(booking.toString());
    }

    public void showMessage(String message) {
        JOptionPane.showMessageDialog(this, message);
    }
}
