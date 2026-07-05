package com.example.model;

import com.example.MathUtils;
import com.google.gson.annotations.SerializedName;

public class Service {

    // Encapsulated attributes

    private String name;
    private int quantity;
    @SerializedName("price")
    private double unitPrice;
    private double discount;

    // Empty constructor (required by Gson to instantiate the object)

    public Service() {
    }

    // Constructor

    public Service(String name, int quantity, double unitPrice, double discount) {
        this.name = name;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
        this.discount = discount;
    }

    // Getters/Setters

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    // Methods

    // Calculate total cost (quantity * unitPrice), usando MathUtils.add en bucle
    // porque MathUtils no tiene un multiply de int por double
    public double getTotalCost() {
        double total = 0.0;
        for (int i = 0; i < quantity; i++) {
            total = MathUtils.add(total, unitPrice);
        }
        return total;
    }

    // Calculate discounted cost applying the discount over the total cost
    public double getDiscountedCost() {
        return MathUtils.applyDiscount(getTotalCost(), discount);
    }

    @Override
    public String toString() {
        return "Service{" +
                "name='" + name + '\'' +
                ", quantity=" + quantity +
                ", unitPrice=" + unitPrice +
                ", discount=" + discount +
                '}';
    }
}
