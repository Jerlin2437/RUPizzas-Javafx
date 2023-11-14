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
    }
}
