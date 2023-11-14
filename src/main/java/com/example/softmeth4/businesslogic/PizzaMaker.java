package com.example.softmeth4.businesslogic;

import com.example.softmeth4.enums.Sauce;
import com.example.softmeth4.enums.Size;
import com.example.softmeth4.enums.Topping;
import com.example.softmeth4.pizzas.*;

import java.util.ArrayList;

public class PizzaMaker {
    //takes string of Pizzatype + more args depending on pizzatype.
    public static Pizza createPizza(String pizzaType){
        String[] parsedPizza = pizzaType.split(" ");
        Size size = Size.fromString(parsedPizza[1]);
        boolean extraSauce = Boolean.parseBoolean(parsedPizza[2]);
        boolean extraCheese = Boolean.parseBoolean(parsedPizza[3]);
        if (parsedPizza[0] == "Supreme")
            return new Supreme(size, extraSauce, extraCheese);
        else if (parsedPizza[0] == "Seafood")
            return new Seafood(size, extraSauce, extraCheese);
        else if (parsedPizza[0] == "Meatzza")
            return new Meatzza(size, extraSauce, extraCheese);
        else if (parsedPizza[0] == "Deluxe")
            return new Deluxe(size, extraSauce, extraCheese);
        else if (parsedPizza[0] == "BYO"){
            Sauce sauce = Sauce.fromString(parsedPizza[4]);
            ArrayList<Topping> toppings = null;
            for (int x = 5; x < parsedPizza.length; x++){
                toppings.add(Topping.fromString(parsedPizza[x]));
            }
            return new BuildYourOwn(size, extraSauce, extraCheese, sauce,toppings);
        }
        return null;
    }
}
