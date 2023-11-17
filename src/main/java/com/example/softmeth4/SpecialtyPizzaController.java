package com.example.softmeth4;

import com.example.softmeth4.businesslogic.Order;
import com.example.softmeth4.businesslogic.StoreOrders;
import com.example.softmeth4.pizzas.Deluxe;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.util.ResourceBundle;

public class SpecialtyPizzaController{
    private Order order;
    @FXML
    private RadioButton tomato;
    @FXML
    private RadioButton alfredo;
    @FXML
    private CheckBox extraCheese;
    @FXML
    private CheckBox extraSauce;

    @FXML
    private RadioButton small;
    @FXML
    private RadioButton medium;
    @FXML
    private RadioButton large;

    @FXML
    private ComboBox<String> chooseSpecialty;
    @FXML
    private ListView<String> toppings;
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

    @FXML
    public void initialize(){
        chooseSpecialty.getItems().addAll("Deluxe", "Supreme", "Meatzza", "Seafood", "Pepperoni");
        chooseSpecialty.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            //clear existing toppings in the ListView
            toppings.getItems().clear();

            if (newValue != null) {
                if (newValue.equals("Deluxe")) {
                    specialtySauceButtonGroup.selectToggle(tomato);
                    toppings.getItems().addAll("Sausage", "Pepperoni", "Green Pepper", "Onion", "Mushroom");
                } else if (newValue.equals("Supreme")) {
                    specialtySauceButtonGroup.selectToggle(tomato);
                    toppings.getItems().addAll("Sausage", "Pepperoni", "Green Pepper", "Onion", "Mushroom", "Ham", "Black Olive");
                } else if (newValue.equals("Meatzza")) {
                    specialtySauceButtonGroup.selectToggle(tomato);
                    toppings.getItems().addAll("Sausage", "Beef", "Ham", "Pepperoni");
                } else if (newValue.equals("Seafood")) {
                    specialtySauceButtonGroup.selectToggle(alfredo);
                    toppings.getItems().addAll("Shrimp", "Squid", "Crab Meat");
                } else if (newValue.equals("Pepperoni")) {
                    specialtySauceButtonGroup.selectToggle(tomato);
                    toppings.getItems().addAll("Pepperoni");
                }
            }
        });
    }



}
