package com.example.view;

import com.example.model.Booking;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.net.URL;

public class BookingView extends JFrame {

    private JTextField searchField;
    private JButton searchButton;
    private JButton createButton;
    private JButton deleteButton;
    private JTextArea resultArea;
    private JList<String> bookingList;

    public BookingView() {
        setTitle("Booking App");
        // 900 wide to match the banner image, otherwise it gets cut off
        setSize(900, 520);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        initComponents();

        setVisible(true);
    }

    private void initComponents() {
        // Banner on top, search bar right below it
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.Y_AXIS));
        topPanel.add(createBannerLabel());

        JPanel searchPanel = new JPanel();
        searchField = new JTextField(15);
        searchButton = new JButton("Search");
        createButton = new JButton("Nueva reserva");
        deleteButton = new JButton("Eliminar reserva");

        searchPanel.add(new JLabel("Booking ID:"));
        searchPanel.add(searchField);
        searchPanel.add(searchButton);
        searchPanel.add(createButton);
        searchPanel.add(deleteButton);

        topPanel.add(searchPanel);
        add(topPanel, BorderLayout.NORTH);

        // Left panel (list)
        bookingList = new JList<>();
        add(new JScrollPane(bookingList), BorderLayout.WEST);

        // Center panel (result)
        resultArea = new JTextArea();
        resultArea.setEditable(false);
        add(new JScrollPane(resultArea), BorderLayout.CENTER);
    }

    // Loads the banner image from resources/images. If it's missing for some
    // reason, just show a plain text title instead of crashing the app
    private JLabel createBannerLabel() {
        URL bannerUrl = getClass().getResource("/images/banner.png");
        if (bannerUrl == null) {
            return new JLabel("Booking App");
        }
        ImageIcon icon = new ImageIcon(bannerUrl);
        return new JLabel(icon);
    }

    public String getSearchInput() {
        return searchField.getText();
    }

    public void setSearchButtonListener(ActionListener listener) {
        searchButton.addActionListener(listener);
    }

    public void setCreateButtonListener(ActionListener listener) {
        createButton.addActionListener(listener);
    }

    public void setDeleteButtonListener(ActionListener listener) {
        deleteButton.addActionListener(listener);
    }

    public void setBookingList(DefaultListModel<String> listModel) {
        bookingList.setModel(listModel);
    }

    // Shows the booking details plus the total in both EUR and USD, using
    // whatever exchange rate the controller passes in
    public void displayBooking(Booking booking, double eurToUsdRate) {
        double totalEur = booking.getTotalWithDiscount();
        double totalUsd = totalEur * eurToUsdRate;

        String text = booking.toString()
                + String.format("%n%nTotal (con descuento): %.2f EUR / %.2f USD", totalEur, totalUsd);

        resultArea.setText(text);
    }

    public void showMessage(String message) {
        JOptionPane.showMessageDialog(this, message);
    }
}
