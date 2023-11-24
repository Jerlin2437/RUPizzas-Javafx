package com.example.softmeth4.pizzas;

import org.junit.Test;

import com.example.softmeth4.enums.Size;
import com.example.softmeth4.enums.Sauce;
import com.example.softmeth4.enums.Topping;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.*;

/**
 * This class tests the price() method in the BuildYourOwn class using the Black-Box Testing Technique, going through
 * test cases involving various numbers of toppings, different sizes, and the addition of extra sauce and/or cheese.
 *
 * @author Jason Lei, Jerlin Yuen
 */

public class BuildYourOwnTest {

    /**
     * Tests if the value the price() method gives is equal to the expected value for a pizza with no extra sauce/cheese
     * and three toppings (the minimum amount of toppings for a BYO pizza).
     */
    @Test
    public void testPriceNoExtraThreeToppings() {
        ArrayList<Topping> toppings = new ArrayList<>(Arrays.asList(Topping.SAUSAGE, Topping.ONION, Topping.GREEN_PEPPER));
        BuildYourOwn pizza = new BuildYourOwn(Size.SMALL, false, false, Sauce.TOMATO, toppings);

        assertEquals(8.99 + Size.SMALL.getPrice() + ((3-3) * 1.49), pizza.price(), 0.001);
    }
    /**
     * Tests if the value the price() method gives is equal to the expected value for a pizza with no extra sauce/cheese
     * and some toppings (between the minimum and maximum amount of toppings).
     */
    @Test
    public void testPriceNoExtraSomeToppings() {
        ArrayList<Topping> toppings = new ArrayList<>(Arrays.asList(Topping.SAUSAGE, Topping.ONION, Topping.GREEN_PEPPER, Topping.BEEF, Topping.BLACK_OLIVE));
        BuildYourOwn pizza = new BuildYourOwn(Size.MEDIUM, false, false, Sauce.TOMATO, toppings);

        assertEquals(8.99 + Size.MEDIUM.getPrice() + ((5-3) * 1.49), pizza.price(), 0.001);
    }

    /**
     * Tests if the value the price() method gives is equal to the expected value for a pizza with no extra sauce/cheese
     * and many toppings (the maximum amount of toppings).
     */
    @Test
    public void testPriceNoExtraManyToppings() {
        ArrayList<Topping> toppings = new ArrayList<>(Arrays.asList(Topping.SAUSAGE, Topping.ONION, Topping.GREEN_PEPPER, Topping.BEEF, Topping.BLACK_OLIVE, Topping.HAM, Topping.SQUID));
        BuildYourOwn pizza = new BuildYourOwn(Size.MEDIUM, false, false, Sauce.TOMATO, toppings);

        assertEquals(8.99 + Size.MEDIUM.getPrice() + ((7-3) * 1.49), pizza.price(), 0.001);
    }

    /**
     * Tests if the value the price() method gives is equal to the expected value for a pizza with extra cheese
     * and some toppings.
     */
    @Test
    public void testPriceExtraCheese() {
        ArrayList<Topping> toppings = new ArrayList<>(Arrays.asList(Topping.SAUSAGE, Topping.ONION, Topping.BLACK_OLIVE));
        BuildYourOwn pizza = new BuildYourOwn(Size.MEDIUM, false, true, Sauce.TOMATO, toppings);

        assertEquals(8.99 + Size.MEDIUM.getPrice() + ((3-3) * 1.49) + 1.0, pizza.price(), 0.001);
    }

    /**
     * Tests if the value the price() method gives is equal to the expected value for a pizza with extra sauce
     * and some toppings.
     */
    @Test
    public void testPriceExtraSauce() {
        ArrayList<Topping> toppings = new ArrayList<>(Arrays.asList(Topping.SAUSAGE, Topping.ONION, Topping.GREEN_PEPPER, Topping.BEEF, Topping.BLACK_OLIVE));
        BuildYourOwn pizza = new BuildYourOwn(Size.LARGE, true, false, Sauce.TOMATO, toppings);

        assertEquals(8.99 + Size.LARGE.getPrice() + ((5-3) * 1.49) + 1.0, pizza.price(), 0.001);
    }

    /**
     * Tests if the value the price() method gives is equal to the expected value for a pizza with no extra sauce/cheese
     * and no toppings.
     */
    @Test
    public void testPriceNoExtraNoToppings() {
        ArrayList<Topping> toppings = new ArrayList<>(Arrays.asList());
        BuildYourOwn pizza = new BuildYourOwn(Size.LARGE, false, false, Sauce.TOMATO, toppings);

        assertEquals(8.99 + Size.LARGE.getPrice(), pizza.price(), 0.001);
    }

    /**
     * Tests if the value the price() method gives is equal to the expected value for a pizza with extra cheese
     * and many toppings.
     */
    @Test
    public void testPriceExtraCheeseManyToppings() {
        ArrayList<Topping> toppings = new ArrayList<>(Arrays.asList(Topping.SAUSAGE, Topping.ONION, Topping.GREEN_PEPPER, Topping.BEEF, Topping.BLACK_OLIVE, Topping.PEPPERONI, Topping.HAM));
        BuildYourOwn pizza = new BuildYourOwn(Size.LARGE, false, true, Sauce.TOMATO, toppings);

        assertEquals(8.99 + Size.LARGE.getPrice() + ((7-3) * 1.49) + 1.0, pizza.price(), 0.001);
    }

    /**
     * Tests if the value the price() method gives is equal to the expected value for a pizza with extra sauce
     * and many toppings.
     */
    @Test
    public void testPriceExtraSauceManyToppings() {
        ArrayList<Topping> toppings = new ArrayList<>(Arrays.asList(Topping.SAUSAGE, Topping.ONION, Topping.GREEN_PEPPER, Topping.BEEF, Topping.BLACK_OLIVE, Topping.PEPPERONI, Topping.HAM));
        BuildYourOwn pizza = new BuildYourOwn(Size.LARGE, true, false, Sauce.ALFREDO, toppings);

        assertEquals(8.99 + Size.LARGE.getPrice() + ((7-3) * 1.49) + 1.0, pizza.price(), 0.001);
    }

    /**
     * Tests if the value the price() method gives is equal to the expected value for a pizza with extra cheese, extra sauce
     * and many toppings.
     */
    @Test
    public void testPriceExtraSauceExtraCheeseManyToppings() {
        ArrayList<Topping> toppings = new ArrayList<>(Arrays.asList(Topping.SAUSAGE, Topping.ONION, Topping.GREEN_PEPPER, Topping.BEEF, Topping.BLACK_OLIVE, Topping.PEPPERONI, Topping.HAM));
        BuildYourOwn pizza = new BuildYourOwn(Size.LARGE, true, true, Sauce.ALFREDO, toppings);

        assertEquals(8.99 + Size.LARGE.getPrice() + ((7-3) * 1.49) + 1.0 + 1.0, pizza.price(), 0.001);
    }


}