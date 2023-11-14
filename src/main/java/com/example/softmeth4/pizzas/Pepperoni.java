package com.example.softmeth4.pizzas;

import com.example.softmeth4.enums.Sauce;
import com.example.softmeth4.enums.Size;
import com.example.softmeth4.enums.Topping;

public class Pepperoni extends Pizza{
    public Pepperoni(Size size, boolean extraSauce, boolean extraCheese) {
        super(size, extraSauce, extraCheese);
        toppings.add(Topping.PEPPERONI);
        sauce = Sauce.TOMATO;
    }

    @Override
    public double price() {
        return 10.99 + size.getPrice();

    }
}
