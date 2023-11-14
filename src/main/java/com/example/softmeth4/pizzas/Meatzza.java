package com.example.softmeth4.pizzas;

import com.example.softmeth4.enums.Sauce;
import com.example.softmeth4.enums.Size;

public class Meatzza extends Pizza{
    public Meatzza(Size size, boolean extraSauce, boolean extraCheese) {
        super(size, extraSauce, extraCheese);
        sauce = Sauce.TOMATO;
    }

    @Override
    public double price() {
        return 16.99 + size.getPrice();

    }
}
