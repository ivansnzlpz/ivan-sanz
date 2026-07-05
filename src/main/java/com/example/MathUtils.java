package com.example;

import java.util.List;

public class MathUtils {

    public static int product(int x, int y) {
        return x * y;
    }

    public static String joinStrings(String x, String y) {
        if (x == null || y == null) {
            return "empty";
        }
        return x + y;
    }

    public static double add(double x, double y) {
        return x + y;
    }

    public static double applyDiscount(double total, double percentage) {
        if (percentage < 0 || percentage > 100) {
            throw new IllegalArgumentException("Invalid discount percentage");
        }

        double discountAmount = total * (percentage / 100);
        return total - discountAmount;
    }

    public static double sumAll(List<Double> values) {
        if (values == null || values.isEmpty()) return 0.0;

        double sum = 0.0;
        for (Double value : values) {
            if (value != null) {
                sum += value;
            }
        }
        return sum;
    }
}