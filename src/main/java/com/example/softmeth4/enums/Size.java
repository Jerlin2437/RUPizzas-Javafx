package com.example.softmeth4.enums;

public enum Size {
    SMALL(0),
    MEDIUM(2.00),
    LARGE(4.00);

    private final double price;

    Size(double price) {
        this.price = price;
    }

    public double getPrice() {
        return price;
    }
    public static Size fromString(String sizeString) {
        return switch (sizeString.toLowerCase()) {
            case "small" -> SMALL;
            case "medium" -> MEDIUM;
            case "large" -> LARGE;
            default -> throw new IllegalArgumentException("Invalid size: " + sizeString);
        };
    }
}
