package com.example.softmeth4.businesslogic;

import com.example.softmeth4.enums.Size;
import com.example.softmeth4.enums.Topping;
import com.example.softmeth4.pizzas.BuildYourOwn;
import com.example.softmeth4.pizzas.Meatzza;
import com.example.softmeth4.pizzas.Pizza;

import java.util.ArrayList;

public class StoreOrders {
    private static int nextOrderNum;
    private ArrayList<Order> orders;

    public StoreOrders() {
        orders = new ArrayList<>();
    }
    public void addOrder(Order order){
        order.setOrderNumber(nextOrderNum);
        nextOrderNum++;
        orders.add(order);
    }
    @Override
    public String toString() {
        StringBuilder storeOrdersString = new StringBuilder();
        for (Order order : orders) {
            storeOrdersString.append(order.toString());
            storeOrdersString.append("\n");
        }
        return storeOrdersString.toString();
    }
    public static void main(String[] args) {
        // Create pizzas
        String pizzaType1 = "Supreme LARGE true false";
        Pizza pizza1 = PizzaMaker.createPizza(pizzaType1);

        String pizzaType2 = "BYO MEDIUM true true Tomato PEPPERONI MUSHROOM";
        Pizza pizza2 = PizzaMaker.createPizza(pizzaType2);

        StoreOrders storeOrders = new StoreOrders();
        Order order = new Order();
        order.addPizza(pizza1);
        order.addPizza(pizza2);
        storeOrders.addOrder(order);
        // Display pizza details

        System.out.println(storeOrders);

    }
    public static int getNextOrderNum() {
        return nextOrderNum;
    }
}
