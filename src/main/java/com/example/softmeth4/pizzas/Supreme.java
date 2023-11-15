package com.example.softmeth4.pizzas;

import com.example.softmeth4.enums.Sauce;
import com.example.softmeth4.enums.Size;
import com.example.softmeth4.enums.Topping;

public class Supreme extends Pizza{


    public Supreme(Size size, boolean extraSauce, boolean extraCheese) {
        super(size, extraSauce, extraCheese);
        sauce = Sauce.TOMATO;
        toppings.add(Topping.SAUSAGE);
        toppings.add(Topping.PEPPERONI);
        toppings.add(Topping.GREEN_PEPPER);
        toppings.add(Topping.ONION);
        toppings.add(Topping.MUSHROOM);
        toppings.add(Topping.HAM);
        toppings.add(Topping.BLACK_OLIVE);
    }
    @Override
    protected String getPizzaType() {
        return "Supreme";
    }
    @Override
    public double price() {
        return 15.99 + size.getPrice();

    }
}
