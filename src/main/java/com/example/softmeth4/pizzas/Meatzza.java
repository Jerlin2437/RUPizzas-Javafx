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
        double extraCost = 0.0;
        if (hasExtraSauce(extraSauce)){
            extraCost += 1.0;
        }
        if (hasExtraCheese(extraCheese)){
            extraCost += 1.0;
        }
        return 16.99 + size.getPrice() + extraCost;

    }
}
