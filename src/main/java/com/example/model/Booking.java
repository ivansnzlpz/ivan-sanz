package com.example.model;

import com.example.MathUtils;

import java.util.List;

public class Booking {

    // Encapsulated attributes

    private String id;
    private List<Service> services;

    // Constructor

    public Booking(String id, List<Service> services) {
        this.id = id;
        this.services = services;
    }

    // Getters/Setters

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<Service> getServices() {
        return services;
    }

    public void setServices(List<Service> services) {
        this.services = services;
    }

    // Methods

    public double getTotalCost() {
        double total = 0.0;
        for (Service service : services) {
            total = MathUtils.add(total, service.getTotalCost());
        }
        return total;
    }

    public double getTotalWithDiscount() {
        double total = 0.0;
        for (Service service : services) {
            total = MathUtils.add(total, service.getDiscountedCost());
        }
        return total;
    }

    @Override
    public String toString() {
        return "Booking{" +
                "id='" + id + '\'' +
                ", services=" + services +
                '}';
    }
}
