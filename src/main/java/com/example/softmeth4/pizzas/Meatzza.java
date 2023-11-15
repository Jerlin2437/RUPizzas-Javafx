package com.example.softmeth4.pizzas;

import com.example.softmeth4.enums.Sauce;
import com.example.softmeth4.enums.Size;
import com.example.softmeth4.enums.Topping;

public class Meatzza extends Pizza{
    public Meatzza(Size size, boolean extraSauce, boolean extraCheese) {
        super(size, extraSauce, extraCheese);
        sauce = Sauce.TOMATO;
        toppings.add(Topping.SAUSAGE);
        toppings.add(Topping.BEEF);
        toppings.add(Topping.HAM);
        toppings.add(Topping.PEPPERONI);
    }
    @Override
    protected String getPizzaType() {
        return "Meatzza";
    }
    @Override
    public double price() {
        return 16.99 + size.getPrice();

    }
}
