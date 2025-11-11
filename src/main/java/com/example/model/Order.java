package com.example.model;

import java.util.List;

public class Order {

    // Encapsulated attributes

    private String orderId;
    private List<Article> articles;

    // Constructor

    public Order(String orderId, List<Article> articles) {
        this.orderId = orderId;
        this.articles = articles;
    }

    // Getters/Setters

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public List<Article> getArticles() {
        return articles;
    }

    public void setArticles(List<Article> articles) {
        this.articles = articles;
    }

    // Methods

    public double getGrossTotal() {
        double total = 0.0;
        if (!articles.isEmpty()) {
            for (Article article : articles) {
                total += article.getGrossAmount();
            }
            return total;
        }else return total;
    }

    public double getDiscountedTotal() {
        double total = 0.0;
        if (!articles.isEmpty()) {
            for (Article article : articles) {
                total += article.getDiscountedAmount();
            }
            return total;
        }else return total; 
    }
}
