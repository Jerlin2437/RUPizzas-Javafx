package com.example.softmeth4.pizzas;

import com.example.softmeth4.enums.Sauce;
import com.example.softmeth4.enums.Size;
import com.example.softmeth4.enums.Topping;

/**
 * This class represents a pizza of type "Pepperoni" that a customer can order,
 * containing methods to return information about a pizza and its price after adding
 * toppings, its size, and extra sauce/cheese.
 *
 * @author Jerlin Yuen, Jason Lei
 */
public class Pepperoni extends Pizza {
    private static final double PEPPERONI_PRICE = 10.99;

    /**
     * Parameterized constructor allows for the creation of a Pepperoni pizza,
     * contains list of preset toppings and sauce.
     *
     * @param size        - size of pizza
     * @param extraSauce  - choice of extra sauce
     * @param extraCheese - choice of extra cheese
     */
    public Pepperoni(Size size, boolean extraSauce, boolean extraCheese) {
        super(size, extraSauce, extraCheese);
        toppings.add(Topping.PEPPERONI);
        sauce = Sauce.TOMATO;
    }

    /**
     * Getter method (accessor)
     *
     * @return "Pepperoni"
     */
    @Override
    protected String getPizzaType() {
        return "Pepperoni";
    }

    /**
     * Calculates and returns the price of the pizza based on the base cost,
     * the size of the pizza, whether there is extra sauce and/or extra cheese.
     *
     * @return price of the pizza
     */
    @Override
    public double price() {
        double extraCost = 0.0;
        if (hasExtraSauce(extraSauce)) {
            extraCost += 1.0;
        }
        if (hasExtraCheese(extraCheese)) {
            extraCost += 1.0;
        }
        return PEPPERONI_PRICE + size.getPrice() + extraCost;

    }
}
