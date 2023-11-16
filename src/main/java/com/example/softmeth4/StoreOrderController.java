package com.example.softmeth4;

import com.example.softmeth4.businesslogic.Order;
import com.example.softmeth4.businesslogic.StoreOrders;

public class StoreOrderController {
    private Order order;
    private StoreOrders storeOrders;
    public StoreOrderController(){
        order = HelloApplication.getOrder();
        storeOrders = HelloApplication.getStoreOrders();
    }
}
