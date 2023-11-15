package com.example.softmeth4.businesslogic;

import com.example.softmeth4.pizzas.Pizza;

import java.util.ArrayList;

public class Order {
    private int orderNumber;
    private ArrayList<Pizza> pizzas;
    public Order(){
        pizzas = new ArrayList<>();
    }
    public Order(ArrayList<Pizza> pizzas) {
        this.pizzas = pizzas;
    }
    public void addPizza(Pizza pizza){
        pizzas.add(pizza);
    }
    public void setOrderNumber(int orderNumber) {
        this.orderNumber = orderNumber;
    }
    public String toString() {
        StringBuilder orderString = new StringBuilder();
        orderString.append("Order Number: ").append(orderNumber).append("\n");

        for (int i = 0; i < pizzas.size(); i++) {
            Pizza pizza = pizzas.get(i);
            orderString.append("Pizza ").append(i + 1).append(": ").append(pizza.toString()).append("\n");
        }

        return orderString.toString();
    }

}
