package com.example.softmeth4;

import com.example.softmeth4.businesslogic.Order;
import com.example.softmeth4.enums.Topping;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;

public class BuildOwnController {
    private Order order;
    @FXML
    private ListView<Topping> selectedToppings;
    @FXML
    private ListView<Topping> notSelectedToppings;
    public BuildOwnController(){
        order = HelloApplication.getOrder();
    }
}
