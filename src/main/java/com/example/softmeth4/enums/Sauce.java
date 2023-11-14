package com.example.softmeth4.enums;

public enum Sauce {
    TOMATO,
    ALFREDO;

    public static Sauce fromString(String sauceString) {
        return switch (sauceString.toLowerCase()) {
            case "tomato" -> TOMATO;
            case "alfredo" -> ALFREDO;
            default -> throw new IllegalArgumentException("Invalid sauce: " + sauceString);
        };
    }
}
