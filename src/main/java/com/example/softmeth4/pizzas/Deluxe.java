package com.example.softmeth4.pizzas;

import com.example.softmeth4.enums.Sauce;
import com.example.softmeth4.enums.Size;

public class Deluxe extends Pizza{
    public Deluxe(Size size, boolean extraSauce, boolean extraCheese) {
        super(size, extraSauce, extraCheese);
        sauce = Sauce.TOMATO;
    }

    @Override
    public double price() {
        return 14.99 + size.getPrice();
    }
}
