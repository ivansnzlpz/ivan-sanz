package com.example.view;

import com.example.model.Booking;
import com.example.model.Service;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

// Small popup window (JDialog) used to build a new Booking before handing it
// back to the controller. It doesn't know about the other bookings at all,
// checking if the id is already taken is the controller's job, not this class's
public class BookingFormDialogView extends JDialog {

    private JTextField idField;
    private JTextField nameField;
    private JTextField quantityField;
    private JTextField priceField;
    private JTextField discountField;
    private DefaultTableModel tableModel;
    private JTable serviceTable;

    private final List<Service> services = new ArrayList<>();
    private Booking result; // stays null if the user cancels or just closes the window

    public BookingFormDialogView(Frame owner) {
        super(owner, "Nueva reserva", true);
        setSize(500, 450);
        setLocationRelativeTo(owner);
        setLayout(new BorderLayout());

        initComponents();
    }

    private void initComponents() {
        // Booking id at the top
        JPanel idPanel = new JPanel();
        idField = new JTextField(15);
        idPanel.add(new JLabel("Booking ID:"));
        idPanel.add(idField);
        add(idPanel, BorderLayout.NORTH);

        // Fields to type in one service at a time
        JPanel servicePanel = new JPanel(new GridLayout(2, 4, 5, 5));
        nameField = new JTextField();
        quantityField = new JTextField();
        priceField = new JTextField();
        discountField = new JTextField();
        servicePanel.add(new JLabel("Nombre"));
        servicePanel.add(new JLabel("Cantidad"));
        servicePanel.add(new JLabel("Precio unitario"));
        servicePanel.add(new JLabel("Descuento (%)"));
        servicePanel.add(nameField);
        servicePanel.add(quantityField);
        servicePanel.add(priceField);
        servicePanel.add(discountField);

        JButton addServiceButton = new JButton("Añadir servicio");
        addServiceButton.addActionListener(e -> addServiceRow());

        // Table underneath shows every service added so far
        JPanel centerPanel = new JPanel(new BorderLayout());
        centerPanel.add(servicePanel, BorderLayout.NORTH);
        centerPanel.add(addServiceButton, BorderLayout.SOUTH);

        tableModel = new DefaultTableModel(new Object[]{"Nombre", "Cantidad", "Precio", "Descuento"}, 0);
        serviceTable = new JTable(tableModel);
        centerPanel.add(new JScrollPane(serviceTable), BorderLayout.CENTER);

        add(centerPanel, BorderLayout.CENTER);

        // Save / cancel buttons at the bottom
        JPanel bottomPanel = new JPanel();
        JButton saveButton = new JButton("Guardar reserva");
        JButton cancelButton = new JButton("Cancelar");
        saveButton.addActionListener(e -> saveBooking());
        cancelButton.addActionListener(e -> {
            result = null;
            dispose();
        });
        bottomPanel.add(saveButton);
        bottomPanel.add(cancelButton);
        add(bottomPanel, BorderLayout.SOUTH);
    }

    // Reads the one-service form, adds it to the list, then clears the fields
    // so the user can type the next service straight away
    private void addServiceRow() {
        try {
            String name = nameField.getText().trim();
            int quantity = Integer.parseInt(quantityField.getText().trim());
            double price = Double.parseDouble(priceField.getText().trim());
            double discount = Double.parseDouble(discountField.getText().trim());

            if (name.isEmpty()) {
                JOptionPane.showMessageDialog(this, "El nombre del servicio no puede estar vacío");
                return;
            }

            services.add(new Service(name, quantity, price, discount));
            tableModel.addRow(new Object[]{name, quantity, price, discount});

            nameField.setText("");
            quantityField.setText("");
            priceField.setText("");
            discountField.setText("");
        } catch (NumberFormatException e) {
            // quantity/price/discount weren't valid numbers
            JOptionPane.showMessageDialog(this, "Cantidad, precio y descuento deben ser numéricos");
        }
    }

    // Builds the final Booking from everything typed in and closes the dialog
    private void saveBooking() {
        String id = idField.getText().trim();

        if (id.isEmpty()) {
            JOptionPane.showMessageDialog(this, "El Booking ID no puede estar vacío");
            return;
        }

        result = new Booking(id, new ArrayList<>(services));
        dispose();
    }

    // Whoever opened this dialog calls this afterwards to get the new booking,
    // or null if the user backed out
    public Booking getResult() {
        return result;
    }
}