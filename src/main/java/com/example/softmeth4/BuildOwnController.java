package com.example.softmeth4;

import com.example.softmeth4.businesslogic.Order;
import com.example.softmeth4.businesslogic.PizzaMaker;
import com.example.softmeth4.businesslogic.StoreOrders;
import com.example.softmeth4.enums.Topping;
import com.example.softmeth4.pizzas.Pizza;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ResourceBundle;


/**
 * This controller class allows customers to build their own pizza orders on a JavaFX application
 * This class contains methods to add and remove toppings, as well as various event handlers
 * to handle popups and other events when buttons/checkboxes/combo boxes are selected and/or interacted
 * with.
 *
 * @author Jason Lei, Jerlin Yuen
 */

public class BuildOwnController implements Initializable {

    private static final int MIN_TOPPING = 3;
    private static final int MAX_TOPPING = 7;
    private static final double TOPPING_PRICE = 1.49;
    private static final double MORESAUCECHEESE = 1.0;
    private Order order;
    private Pizza pizza;
    private int toppingCount;
    private double additionalToppingPrice;

    @FXML
    private CheckBox buildExtraCheese;
    @FXML
    private CheckBox buildExtraSauce;
    @FXML
    private ComboBox<String> sizeComboBox;
    @FXML
    private TextField buildPrice;
    @FXML
    private Button buildAddToOrder;
    @FXML
    private Button addTopping;
    @FXML
    private Button removeTopping;
    @FXML
    private ListView<Topping> selectedToppings;
    @FXML
    private ListView<Topping> notSelectedToppings;

    @FXML
    private ToggleGroup sauceRadioButton;

    /**
     * Default constructor, initializing an instance of a customer's pizza order
     */
    public BuildOwnController() {
        order = HelloApplication.getOrder();
    }

    /**
     * Initializes listeners and event handlers for various UI elements, including
     * the combo box selection changes and button clicks that occur in the interface.
     * (ex: adds and remove toppings when the respective buttons are clicked)
     *
     * @param url            - url
     * @param resourceBundle - resource bundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        sizeComboBox.valueProperty().addListener((observable, oldValue, newValue) -> {
            pizza = pizzaParse();
            updatePizzaPrice();
        });
        notSelectedToppings.getItems().addAll(Topping.values());
        addTopping.setOnAction(event -> {
            addToppings();
            pizza = pizzaParse();
            updatePizzaPrice();
        });
        removeTopping.setOnAction(event -> {
            removeToppings();
            pizza = pizzaParse();
            updatePizzaPrice();
        });
        buildExtraSauce.setOnAction(event -> {
            pizza = pizzaParse();
            updatePizzaPrice();
        });
        buildExtraCheese.setOnAction(event -> {
            pizza = pizzaParse();
            updatePizzaPrice();
        });
        buildAddToOrder.setOnAction(new buildAddToOrderHandler());

    }

    /**
     * Updates the displayed pizza price based on selected toppings, size, and other options.
     */
    private void updatePizzaPrice() {
        if (pizza != null) {
            if (toppingCount >= MIN_TOPPING) {
                pizza = pizzaParse();
                //base price without toppings
                double basePrice = pizza.price();
                //if more than 3 toppings, add $1.49 for each additional topping after 3
                additionalToppingPrice = Math.max(0, toppingCount - MAX_TOPPING) * TOPPING_PRICE;

                if (buildExtraSauce.isSelected()) {
                    additionalToppingPrice += MORESAUCECHEESE;
                }

                if (buildExtraCheese.isSelected()) {
                    additionalToppingPrice += MORESAUCECHEESE;
                }

                double totalPrice = basePrice + additionalToppingPrice;
                String formattedValue = String.format("%.2f", totalPrice);
                buildPrice.setText(formattedValue);
            } else {
                double totalPrice = pizza.price() + additionalToppingPrice;
                String formattedValue = String.format("%.2f", totalPrice);
                buildPrice.setText(formattedValue);
            }
        }
    }

    /**
     * Parses UI elements and returns a new Pizza object based on user selections
     *
     * @return customized pizza based on user selections
     */
    private Pizza pizzaParse() {
        Toggle selectedToggle = sauceRadioButton.getSelectedToggle();
        RadioButton selectedRadioButton = (RadioButton) selectedToggle;
        String sauce = selectedRadioButton.getText();
        String size = sizeComboBox.getValue();
        String pizzaType = "BYO";

        ObservableList<Topping> selectedToppingsList = selectedToppings.getItems();
        StringBuilder allToppings = new StringBuilder();

        for (Topping topping : selectedToppingsList) {
            allToppings.append(topping.toString()).append(" ");
        }

        //size, extraSauce, extraCheese, sauce,toppings
        return PizzaMaker.createPizza(pizzaType + " " + size + " false false " + sauce + " " + allToppings.toString().trim());
    }

    /**
     * Selects and adds toppings to a new ListView of selected toppings
     * Handles errors and displays pop-up alerts for specific cases
     * Updates the displayed pizza price after
     */
    private void addToppings() {
        // Get the selected item from notSelectedToppings
        Topping selectedTopping = notSelectedToppings.getSelectionModel().getSelectedItem();
        if (selectedToppings.getItems().size() >= MAX_TOPPING) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Too many toppings!");
            alert.setHeaderText(null);
            alert.setContentText("You can only have a max of 7 toppings!");
            alert.initOwner(addTopping.getScene().getWindow());
            alert.showAndWait();
        } else if (selectedTopping != null) {
            notSelectedToppings.getItems().remove(selectedTopping);
            selectedToppings.getItems().add(selectedTopping);
            toppingCount++;
            updatePizzaPrice();
        }
    }

    /**
     * Selects and removes toppings from a ListView of selected toppings.
     * Updates the displayed pizza price after
     */
    private void removeToppings() {
        Topping selectedTopping = selectedToppings.getSelectionModel().getSelectedItem();
        if (selectedTopping != null) {
            selectedToppings.getItems().remove(selectedTopping);
            notSelectedToppings.getItems().add(selectedTopping);
            toppingCount--;
            updatePizzaPrice();
        }
    }

    /**
     * An inner class that handles the action event triggered by the buildAddToOrder button
     */
    public class buildAddToOrderHandler implements EventHandler<ActionEvent> {
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
         * Adds the BYO pizza to the order, displaying a success popup if successful,
         * and a failure popup if it does not meet requirements (ex: pizza does not have at least 3 toppings)
         */
        private void addToOrder() {
            order = HelloApplication.getOrder();
            if (sizeComboBox.getValue() != null) {
                if (toppingCount >= MIN_TOPPING) {
                    if (buildExtraCheese.isSelected())
                        pizza.setExtraCheese(true);
                    if (buildExtraSauce.isSelected())
                        pizza.setExtraSauce(true);
                    order.addPizza(pizza);
                    //   pizza = null;
                    showSuccessPopup();
                } else {
                    showFailurePopup1();
                }
            } else
                showFailurePopup2();
        }

        /**
         * Displays a success alert/popup with a specific message
         */
        private void showSuccessPopup() {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Pizza Order Successful");
            alert.setHeaderText(null);
            alert.setContentText("Your pizza order has been added successfully!");
            alert.initOwner(sizeComboBox.getScene().getWindow());
            alert.showAndWait();
        }


        /**
         * Displays a failure alert/popup with a specific message if the order does not meet
         * topping requirements
         */
        private void showFailurePopup1() {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Pizza Order Unsuccessful");
            alert.setContentText("You need at least 3 toppings.");
            alert.initOwner(sizeComboBox.getScene().getWindow());
            alert.showAndWait();
        }


        /**
         * Displays a failure alert/popup with a specific message
         */
        private void showFailurePopup2() {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Pizza Order Unsuccessful");
            alert.initOwner(sizeComboBox.getScene().getWindow());
            alert.showAndWait();
        }


    }
}
