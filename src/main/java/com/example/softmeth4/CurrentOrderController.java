package com.example.softmeth4;

import com.example.softmeth4.businesslogic.Order;

public class CurrentOrderController {
    private Order order; 
    public CurrentOrderController(){
        order = HelloApplication.getOrder();
    }
}
