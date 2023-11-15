package com.example.softmeth4.pizzas;

import com.example.softmeth4.enums.Sauce;
import com.example.softmeth4.enums.Size;
import com.example.softmeth4.enums.Topping;

import java.util.ArrayList;

public abstract class Pizza {
    protected ArrayList<Topping> toppings;
    protected Size size;
    protected Sauce sauce;
    protected boolean extraSauce;
    protected boolean extraCheese;
    public abstract double price();

    public Pizza(Size size, boolean extraSauce, boolean extraCheese) {
        this.size = size;
        this.extraSauce = extraSauce;
        this.extraCheese = extraCheese;
        toppings = new ArrayList<>();
    }
    @Override
    public String toString() {
        StringBuilder pizzaString = new StringBuilder();
        pizzaString.append("Type: ").append(getPizzaType()).append("\n");
        pizzaString.append("Size: ").append(size).append("\n");
        pizzaString.append("Extra Sauce: ").append(extraSauce).append("\n");
        pizzaString.append("Extra Cheese: ").append(extraCheese).append("\n");
        pizzaString.append("Toppings: ").append(toppingsToString()).append("\n");
        pizzaString.append("Price: $").append(price()).append("\n");

        return pizzaString.toString();
    }

    protected abstract String getPizzaType();

    private String toppingsToString() {
        if (toppings.isEmpty()) {
            return "None";
        } else {
            StringBuilder toppingsString = new StringBuilder();
            for (Topping topping : toppings) {
                toppingsString.append(topping).append(", ");
            }
            // Remove the trailing comma and space
            toppingsString.setLength(toppingsString.length() - 2);
            return toppingsString.toString();
        }
    }
}
