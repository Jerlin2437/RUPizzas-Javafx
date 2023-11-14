package com.example.softmeth4.pizzas;

import com.example.softmeth4.enums.Sauce;
import com.example.softmeth4.enums.Size;

public class Supreme extends Pizza{


    public Supreme(Size size, boolean extraSauce, boolean extraCheese) {
        super(size, extraSauce, extraCheese);
    }

    @Override
    public double price() {
        return 15.99 + size.getPrice();

    }
}
