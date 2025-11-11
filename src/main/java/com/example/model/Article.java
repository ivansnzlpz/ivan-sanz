package com.example.model;

import com.example.Calculator;

public class Article {

    // Encapsulated attributes

    private String name;
    private int quantity;
    private double price;
    private double discount;
    private Calculator calculator;

    // Constructor

    public Article(String name, int quantity, double price, double discount) {
        this.calculator = new Calculator();
        this.name = name;
        this.quantity = quantity;
        this.price = price;
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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    // Methods

    // Calculate gross amount 
    public double getGrossAmount() {
        return calculator.multiplyDouble(quantity, price);
    }

    // Calculate discounted amount
    public double getDiscountedAmount() {
        double grossAmount = getGrossAmount();
        return calculator.discount(grossAmount, discount);
    }
}
