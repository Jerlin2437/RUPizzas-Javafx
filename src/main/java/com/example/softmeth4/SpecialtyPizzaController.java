package com.example.softmeth4;

import com.example.softmeth4.businesslogic.Order;
import com.example.softmeth4.businesslogic.PizzaMaker;
import com.example.softmeth4.pizzas.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.ResourceBundle;


/**
 * This controller class allows customers to select and order specialty pizzas on a JavaFX application
 * This class contains methods to update displayed pizza price, as well as various event handlers
 * to handle popups and other events when buttons/checkboxes/combo boxes are selected and/or interacted
 * with.
 *
 * @author Jason Lei, Jerlin Yuen
 */
public class SpecialtyPizzaController implements Initializable {
    private static final double MORESAUCECHEESE = 1.0;
    @FXML
    private TextField sauceType;
    private Order order;
    private Pizza pizza;
    private double extraToppingsPrice;
    private Map<String, String> pizzaImageMap;


    @FXML
    private ImageView pizzaImageView;

    @FXML
    private CheckBox extraCheese;
    @FXML
    private CheckBox extraSauce;

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

    /**
     * Default constructor, initializing an instance of a customer's pizza order
     */
    public SpecialtyPizzaController(){
        order = HelloApplication.getOrder();
    }

    /**
     * Initializes listeners and event handlers for various UI elements, including
     * the combo box selection changes and button clicks that occur in the interface.
     * (ex: updates displayed pizza price when the respective buttons are clicked)
     *
     * @param url - url
     * @param resourceBundle - resource bundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
        chooseSpecialty.getItems().addAll("Deluxe", "Supreme", "Meatzza", "Seafood", "Pepperoni");
        chooseSpecialty.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            toppings.getItems().clear();
            if (newValue != null) {
                updatePizzaImage(newValue);
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
                updatePizzaPrice();
            }
        });
        specialtyRadioButtonGroup.selectedToggleProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null && newValue.isSelected()) {
                updatePizzaPrice();
            }});
        extraSauce.setOnAction(event -> updatePizzaPrice());
        extraCheese.setOnAction(event -> updatePizzaPrice());

        pizzaImageMap = new HashMap<>();
        pizzaImageMap.put("Deluxe", "/deluxepizza.jpg");
        pizzaImageMap.put("Meatzza", "/MEATZZA.jpg");
        pizzaImageMap.put("Pepperoni", "/pepperonipizza.jpg");
        pizzaImageMap.put("Seafood", "/seafoodpizza.jpg");
        pizzaImageMap.put("Supreme", "/supremepizza.jpg");
        addToOrder.setOnAction(new addToOrderHandler());
    }

    /**
     * Parses UI elements and returns a new Pizza object based on user selections
     *
     * @return customized pizza based on user selections
     */
    private Pizza pizzaParse(){
        Toggle selectedToggle = specialtyRadioButtonGroup.getSelectedToggle();
        RadioButton selectedRadioButton = (RadioButton) selectedToggle;
        String size = selectedRadioButton.getText();
        String pizzaType = chooseSpecialty.getValue();
        return PizzaMaker.createPizza(pizzaType +" " + size + " false false");
    }


    //need to fix magic numbers
    /**
     * Updates the displayed pizza price based on whether extra sauce and/or extra cheese is selected
     */
    private void updatePizzaPrice() {
        if (pizza != null) {
            pizza = pizzaParse();
            //base price without toppings
            double basePrice = pizza.price();
            //resets to 0
            extraToppingsPrice = 0.0;
            if (extraSauce.isSelected()){
                extraToppingsPrice += MORESAUCECHEESE;
            }

            if (extraCheese.isSelected()){
                extraToppingsPrice += MORESAUCECHEESE;
            }

            double totalPrice = basePrice + extraToppingsPrice;
            String formattedValue = String.format("%.2f", totalPrice);
            price.setText(formattedValue);
        }
    }

    private void updatePizzaImage(String pizzaType) {
        String imagePath = pizzaImageMap.get(pizzaType);
        if (imagePath != null) {
            try {
                Image pizzaImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream(imagePath)));
                pizzaImageView.setImage(pizzaImage);
            } catch (Exception e) {
                // Handle the exception, e.g., display a default image or log a warning.
                e.printStackTrace();
            }
        }
    }

    /**
     * An inner class that handles the action event triggered by the buildAddToOrder button
     */
    public class addToOrderHandler implements EventHandler<ActionEvent>{
        /**
         * Event handler that calls the method addToOrder() when the action event
         * is triggered (button is pressed)
         *
         * @param actionEvent action event
         */
        @Override
        public void handle(ActionEvent actionEvent) {
            addToOrder();
        }
        /**
         * Adds the specified specialty pizza to the order, displaying a success popup if successful,
         * and a failure popup if it does not meet requirements (ex: pizza does not have at least 3 toppings)
         */
        private void addToOrder(){
            order = HelloApplication.getOrder();
            if (chooseSpecialty.getValue() != null){
                if(extraCheese.isSelected())
                    pizza.setExtraCheese(true);
                if (extraSauce.isSelected())
                    pizza.setExtraSauce(true);
                order.addPizza(pizza);
              //  pizza = null;
                showSuccessPopup();
            } else
                showFailurePopup();
        }

        /**
         * Displays a success alert/popup with a specific message
         */
        private void showSuccessPopup() {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Pizza Order Successful");
            alert.setHeaderText(null);
            alert.setContentText("Your pizza order has been added successfully!");
            alert.initOwner(chooseSpecialty.getScene().getWindow());
            alert.showAndWait();
        }

        /**
         * Displays a failure alert/popup with a specific message if the order does not meet
         * topping requirements
         */
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
