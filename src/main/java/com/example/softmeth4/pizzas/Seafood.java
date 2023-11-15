package com.example.softmeth4.pizzas;

import com.example.softmeth4.enums.Sauce;
import com.example.softmeth4.enums.Size;
import com.example.softmeth4.enums.Topping;

public class Seafood extends Pizza{
    public Seafood(Size size, boolean extraSauce, boolean extraCheese){
        super( size, extraSauce,  extraCheese);
        toppings.add(Topping.SHRIMP);
        toppings.add(Topping.SQUID);
        toppings.add(Topping.CRAB_MEAT);
        sauce = Sauce.ALFREDO;
    }
    @Override
    protected String getPizzaType() {
        return "Seafood";
    }
    @Override
    public double price() {
        return 17.99 + size.getPrice();

    }
}
