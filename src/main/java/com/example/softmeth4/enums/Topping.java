package com.example.softmeth4.enums;

import java.util.Arrays;

public enum Topping {
    SAUSAGE,
    BEEF,
    PEPPERONI,
    HAM,
    ONION,
    GREEN_PEPPER,
    MUSHROOM,
    BLACK_OLIVE,
    SHRIMP,
    SQUID,
    CRAB_MEAT,
    SPAM,
    FISH;
    public static Topping fromString(String toppingString) {
        return Arrays.stream(values())
                .filter(value -> value.name().equalsIgnoreCase(toppingString))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Invalid topping: " + toppingString));
    }

}
