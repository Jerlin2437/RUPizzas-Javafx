package com.example.softmeth4.businesslogic;

import com.example.softmeth4.pizzas.Pizza;

import java.util.ArrayList;
import java.util.List;

public class Order {
    private int orderNumber;
    private ArrayList<Pizza> pizzas;
    private double subTotalValue;
    private double salesTaxValue;
    private double orderTotalValue;
    private String finalSubTotal;
    private String finalSalesTax;
    private String finalOrderTotal;
    public Order(){
        pizzas = new ArrayList<>();
    }
    public Order(ArrayList<Pizza> pizzas) {
        this.pizzas = pizzas;
    }
    public void addPizza(Pizza pizza){
        pizzas.add(pizza);
    }
    public void removePizza(Pizza pizza){pizzas.remove(pizza);}

    public ArrayList<Pizza> getPizzas() { return pizzas; }
    public double getOrderTotalValue() { return orderTotalValue;}
    public int getOrderNumber() { return orderNumber;}

    public void setOrderNumber(int orderNumber) {
        this.orderNumber = orderNumber;
    }

    public List<String> getPizzaDetails() {
        List<String> pizzaDetails = new ArrayList<>();
        for (int i = 0; i < pizzas.size(); i++) {
            Pizza pizza = pizzas.get(i);
            pizzaDetails.add("Pizza " + (i + 1) + ": \n" + pizza.toString() + ("\n"));
        }
        return pizzaDetails;
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

    //instead of just subtotal, displays subtotal, sales tax, and order total
    public String toFinalOrderDetailsString() {
        StringBuilder orderString = new StringBuilder();
        orderString.append("Order Number: ").append(orderNumber).append("\n");

        for (int i = 0; i < pizzas.size(); i++) {
            Pizza pizza = pizzas.get(i);
            orderString.append("Pizza ").append(i + 1).append(": ").append(pizza.toString()).append("\n");
        }
        calculateFinalOrderValues();
        orderString.append("Subtotal: $").append(finalSubTotal).append("\n");
        orderString.append("Sales Tax: $").append(finalSalesTax).append("\n");
        orderString.append("Order Total: $").append(finalOrderTotal).append("\n");

        return orderString.toString();
    }

    private void calculateFinalOrderValues(){
        subTotalValue = 0.0;
        for (Pizza pizza : pizzas) {
            subTotalValue += pizza.price();
        }
        double taxRate = 0.06625;
        salesTaxValue = subTotalValue * taxRate;
        orderTotalValue = subTotalValue + salesTaxValue;

        finalSubTotal = String.format("%.2f", subTotalValue);
        finalSalesTax = String.format("%.2f", salesTaxValue);
        finalOrderTotal = String.format("%.2f", orderTotalValue);
    }

}
