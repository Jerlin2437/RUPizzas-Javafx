package com.example.softmeth4;

import com.example.softmeth4.businesslogic.Order;
import com.example.softmeth4.businesslogic.PizzaMaker;
import com.example.softmeth4.businesslogic.StoreOrders;
import com.example.softmeth4.enums.Size;
import com.example.softmeth4.pizzas.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ResourceBundle;

import static java.lang.Math.round;

public class SpecialtyPizzaController implements Initializable {
    public TextField sauceType;
    private Order order;
    private StoreOrders storeOrders;
    private Pizza pizza;
    private String pizzaType;
    private String size;
    private String hasExtraSauce;
    private String hasExtraCheese;
    private double extraToppingsPrice;


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

    public SpecialtyPizzaController(){
        order = HelloApplication.getOrder();
        storeOrders = HelloApplication.getStoreOrders();
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
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
                pizza = pizzaParse();
                String formattedValue = String.format("%.2f",pizza.price());
                price.setText(formattedValue);
            }
        });
        specialtyRadioButtonGroup.selectedToggleProperty().addListener((observable, oldValue, newValue) ->{
            if (chooseSpecialty.getValue() != null) {
                pizza = pizzaParse();
                String formattedValue = String.format("%.2f",pizza.price());
                price.setText(formattedValue);
            }
        });
        extraSauce.setOnAction(event -> {
            pizza = pizzaParse();
            updatePizzaPrice();
        });
        extraCheese.setOnAction(event -> {
            pizza = pizzaParse();
            updatePizzaPrice();
        });
        addToOrder.setOnAction(new addToOrderHandler());
    }
    private Pizza pizzaParse(){
        Toggle selectedToggle = specialtyRadioButtonGroup.getSelectedToggle();
        RadioButton selectedRadioButton = (RadioButton) selectedToggle;
        String size = selectedRadioButton.getText();
        String pizzaType = chooseSpecialty.getValue();
        return PizzaMaker.createPizza(pizzaType +" " + size + " false false");
    }

    //need to fix magic numbers
    private void updatePizzaPrice() {
        if (pizza != null) {
            pizza = pizzaParse();
            //base price without toppings
            double basePrice = pizza.price();
            //resets to 0
            extraToppingsPrice = 0.0;
            if (extraSauce.isSelected()){
                extraToppingsPrice += 1.0;
            }

            if (extraCheese.isSelected()){
                extraToppingsPrice += 1.0;
            }

            double totalPrice = basePrice + extraToppingsPrice;
            String formattedValue = String.format("%.2f", totalPrice);
            price.setText(formattedValue);
        }
    }


    public class addToOrderHandler implements EventHandler<ActionEvent>{
        @Override
        public void handle(ActionEvent actionEvent) {
            addToOrder();
        }
        private void addToOrder(){
            if (chooseSpecialty.getValue() != null){
                if(extraCheese.isSelected())
                    pizza.setExtraCheese(true);
                if (extraSauce.isSelected())
                    pizza.setExtraSauce(true);
                order.addPizza(pizza);
                pizza = null;
                showSuccessPopup();
            } else
                showFailurePopup();
        }
        private void showSuccessPopup() {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Pizza Order Successful");
            alert.setHeaderText(null);
            alert.setContentText("Your pizza order has been added successfully!");
            alert.initOwner(chooseSpecialty.getScene().getWindow());
            alert.showAndWait();
        }
        private void showFailurePopup() {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Pizza Order Unsuccessful");
            alert.setHeaderText(null);
            alert.setContentText("You did not select a pizza!");
            alert.initOwner(chooseSpecialty.getScene().getWindow());
            alert.showAndWait();
        }
    }
}
