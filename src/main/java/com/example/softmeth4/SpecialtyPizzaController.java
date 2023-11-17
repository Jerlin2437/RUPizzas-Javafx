package com.example.softmeth4;

import com.example.softmeth4.businesslogic.Order;
import com.example.softmeth4.businesslogic.PizzaMaker;
import com.example.softmeth4.businesslogic.StoreOrders;
import com.example.softmeth4.pizzas.Deluxe;
import com.example.softmeth4.pizzas.Pizza;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.util.ResourceBundle;

public class SpecialtyPizzaController{
    public TextField sauceType;
    private Order order;
    private StoreOrders storeOrders;
    private String pizzaType;
    private String size;
    private String hasExtraSauce;
    private String hasExtraCheese;


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
        storeOrders = HelloApplication.getStoreOrders();
    }

    @FXML
    public void initialize(){
        chooseSpecialty.getItems().addAll("Deluxe", "Supreme", "Meatzza", "Seafood", "Pepperoni");
        chooseSpecialty.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            //clear existing toppings in the ListView
            toppings.getItems().clear();

            if (newValue != null) {
                if (newValue.equals("Deluxe")) {
                    sauceType.setText("Tomato");
                    toppings.getItems().addAll("Sausage", "Pepperoni", "Green Pepper", "Onion", "Mushroom");
                } else if (newValue.equals("Supreme")) {
                    sauceType.setText("Tomato");
                    toppings.getItems().addAll("Sausage", "Pepperoni", "Green Pepper", "Onion", "Mushroom", "Ham", "Black Olive");
                } else if (newValue.equals("Meatzza")) {
                    sauceType.setText("Tomato");
                    toppings.getItems().addAll("Sausage", "Beef", "Ham", "Pepperoni");
                } else if (newValue.equals("Seafood")) {
                    sauceType.setText("Alfredo");
                    toppings.getItems().addAll("Shrimp", "Squid", "Crab Meat");
                } else if (newValue.equals("Pepperoni")) {
                    sauceType.setText("Tomato");
                    toppings.getItems().addAll("Pepperoni");
                }
            }
        });
    }

    //receive order, parse order, select necessary buttons and inputs on the gui,
    //calculate price and whatnot, and send back to helloapp to add to storeorders?
    public void processOrder(){
        updateInterface();

//        String pizzaType = createPizzaFromInput();
//        Pizza pizza = PizzaMaker.createPizza(pizzaType);
//
//        order.addPizza(pizza);

//        double calculatedPrice = calculatePrice();
//        price.setText(String.valueOf(calculatedPrice));

//        storeOrders.addOrder(order);
//
//        //clears order for next order
//        order = new Order();
    }
    //gonna need a different parseOrder for BYO...if parts.length > 4
    private void parseOrder(String orderString){
        String[] parts = orderString.split("\\s+");

        if (parts.length == 4){
            pizzaType = parts[0];
            size = parts[1];
            hasExtraSauce = String.valueOf(Boolean.parseBoolean(parts[2]));
            hasExtraCheese = String.valueOf(Boolean.parseBoolean(parts[3]));
        } else{
            //put try catch exception here so it handles exception properly
            System.out.println("Invalid order format: " + orderString);
        }
    }

    private double calculatePrice(Order order){
        return 0.0;
    }

    private void updateInterface(){
        toppings.getItems().clear();
        specialtySauceButtonGroup.getToggles().clear();
        specialtyRadioButtonGroup.getToggles().clear();

        if (pizzaType != null) {
            if (pizzaType.equals("Deluxe")) {
                sauceType.setText("Tomato");
                toppings.getItems().addAll("Sausage", "Pepperoni", "Green Pepper", "Onion", "Mushroom");
            } else if (pizzaType.equals("Supreme")) {
                sauceType.setText("Tomato");
                toppings.getItems().addAll("Sausage", "Pepperoni", "Green Pepper", "Onion", "Mushroom", "Ham", "Black Olive");
            } else if (pizzaType.equals("Meatzza")) {
                sauceType.setText("Tomato");
                toppings.getItems().addAll("Sausage", "Beef", "Ham", "Pepperoni");
            } else if (pizzaType.equals("Seafood")) {
                sauceType.setText("Alfredo");
                toppings.getItems().addAll("Shrimp", "Squid", "Crab Meat");
            } else if (pizzaType.equals("Pepperoni")) {
                sauceType.setText("Tomato");
                toppings.getItems().addAll("Pepperoni");
            }
        }

        if (size != null){
            switch (size) {
                case "SMALL" -> specialtyRadioButtonGroup.selectToggle(small);
                case "MEDIUM" -> specialtyRadioButtonGroup.selectToggle(medium);
                case "LARGE" -> specialtyRadioButtonGroup.selectToggle(large);
                default -> System.out.println("Error");
            }
        }

        if (hasExtraSauce.equals("true")){
            extraSauce.setSelected(true);
        }else
            extraSauce.setSelected(false);

        if (hasExtraCheese.equals("true"))
            extraCheese.setSelected(true);
        else
            extraCheese.setSelected(false);
    }




}
