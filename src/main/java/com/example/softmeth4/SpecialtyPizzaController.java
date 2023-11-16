package com.example.softmeth4;

import com.example.softmeth4.businesslogic.Order;
import com.example.softmeth4.businesslogic.StoreOrders;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class SpecialtyPizzaController {
    private Order order;
    @FXML
    private CheckBox extraCheese;
    @FXML
    private CheckBox extraSauce;

    @FXML
    private ComboBox<?> chooseSpecialty;
    @FXML
    private ListView<?> toppings;
    @FXML
    private TextField price;
    @FXML
    private Button addToOrder;

    @FXML
    private ToggleGroup specialtyRadioButtonGroup;
    @FXML
    private ToggleGroup specialtySauceButtonGroup;
    public SpecialtyPizzaController(){
        order = HelloApplication.getOrder();
    }



}
