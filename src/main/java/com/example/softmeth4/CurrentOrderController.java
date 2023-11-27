package com.example.softmeth4;

import com.example.softmeth4.businesslogic.Order;

import com.example.softmeth4.businesslogic.StoreOrders;
import com.example.softmeth4.enums.Topping;
import com.example.softmeth4.pizzas.Pizza;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

/**
 * This controller class allows customers to manage and display their current pizza order on a JavaFX application
 * This class contains methods to remove pizzas from an order and calculate total values, as well as various event handlers
 * to handle popups and other events when buttons/checkboxes/combo boxes are selected and/or interacted
 * with.
 *
 * @author Jason Lei, Jerlin Yuen
 */

public class CurrentOrderController implements Initializable {
    private static final double TAX_RATE = 0.06625;
    private static final int ILLEGAL_INDEX = -1;
    private Order order;
    private StoreOrders storeOrders;
    private String currentOrderNumber;
    private double subtotalValue;
    private double salesTaxValue;
    private double orderTotalValue;
    @FXML
    private ListView<String> currentOrderView;
    @FXML
    private TextField orderNumber;
    @FXML
    private TextField orderTotal;
    @FXML
    private Button placeOrder;
    @FXML
    private Button removePizza;
    @FXML
    private Button refreshOrder;
    @FXML
    private TextField salesTax;
    @FXML
    private TextField subtotal;

    /**
     * Default constructor, initializing an instance of a customer's pizza order
     * and the store's collection of orders
     */
    public CurrentOrderController(){
        order = HelloApplication.getOrder();
        storeOrders = HelloApplication.getStoreOrders();
    }

//    public CurrentOrderController(Order order, ListView<String> currentOrderView){
//        this.order = order;
//        this.currentOrderView = currentOrderView;
//        updateCurrentOrderView();
//    }
    /**
     * Initializes listeners and event handlers for various UI elements, including
     * the combo box selection changes and button clicks that occur in the interface.
     * (ex: removes pizzas from an order and places an order when the respective buttons are clicked)
     *
     * @param url - url
     * @param resourceBundle - resource bundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        updateCurrentOrderView();
        removePizza.setOnAction(event -> {
          //  order = HelloApplication.getOrder();
            removeSelectedPizza();
        });
        placeOrder.setOnAction(event -> {
            order = HelloApplication.getOrder();
            placeOrder();
        });
    }

    /**
     * Refreshes the UI to reflect the current state of the order, including pizza details,
     * order number, subtotal, sales tax, and order total.
     */
    @FXML
    public void refresh(){
        updateCurrentOrderView();
    }

//idk
    /**
     * Places the current order, adding it to the store's orders and resetting
     * the current order.
     */
    public void placeOrder(){
        if (!order.getPizzas().isEmpty() && !currentOrderView.getItems().isEmpty()){
            showAddedPopup();
            updateCurrentOrderView();
            storeOrders.addOrder(order);
            HelloApplication.setOrder(new Order());
            order = HelloApplication.getOrder();
            updateCurrentOrderView();
        } else{
            showEmptyPopup();
        }
    }

    /**
     * Removes the selected pizza from the order.
     */
    public void removeSelectedPizza(){
        int selectedIndex = currentOrderView.getSelectionModel().getSelectedIndex();
        if (selectedIndex != ILLEGAL_INDEX){
            Pizza selectedPizza = order.getPizzas().get(selectedIndex);
            order.removePizza(selectedPizza);
            updateCurrentOrderView();
            showRemovedPopup();
        }
    }

    /**
     * Updates the UI to reflect the current state of the order, including pizza details,
     * order number, subtotal, sales tax, and order total.
     */
    private void updateCurrentOrderView(){
        List<String> pizzaSummaries = order.getPizzaDetails();
        ObservableList<String> observableList = FXCollections.observableArrayList(pizzaSummaries);
        currentOrderView.setItems(observableList);

        currentOrderNumber = String.valueOf(StoreOrders.getNextOrderNum());
        orderNumber.setText(currentOrderNumber);

        calculateSubtotal();
        calculateSalesTax();
        calculateOrderTotal();

    }

    /**
     * Calculates the subtotal of the pizza order.
     */
    private void calculateSubtotal() {
        subtotalValue = 0.0;
        for (Pizza pizza : order.getPizzas()) {
            subtotalValue += pizza.price();
        }
        subtotal.setText(String.format("%.2f", subtotalValue));
    }

    /**
     * Calculates the sales tax of the pizza order.
     * The sales tax in NJ is 6.625%.
     */
    private void calculateSalesTax() {
        //6.625% sales tax in NJ
        double taxRate = TAX_RATE;
        salesTaxValue = subtotalValue * taxRate;
        salesTax.setText(String.format("%.2f", salesTaxValue));
    }

    /**
     * Calculates the total amount of the pizza order.
     */
    private void calculateOrderTotal() {
        orderTotalValue = subtotalValue + salesTaxValue;
        orderTotal.setText(String.format("%.2f", orderTotalValue));
    }

    /**
     * Displays alert popups for successfully removing a pizza from the current order
     */
    private void showRemovedPopup() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Pizza Order Removed Successfully");
        alert.setHeaderText(null);
        alert.setContentText("Order removed!");
        alert.initOwner(currentOrderView.getScene().getWindow());
        alert.showAndWait();
    }

    /**
     * Displays alert popups for successfully adding an order to store orders
     */
    private void showAddedPopup() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Pizza Order Added Successfully");
        alert.setHeaderText(null);
        alert.setContentText("Order placed!");
        alert.initOwner(currentOrderView.getScene().getWindow());
        alert.showAndWait();
    }

    /**
     * Displays alert popups for attempting to place an empty order
     */
    private void showEmptyPopup() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Pizza Order is Empty");
        alert.setHeaderText(null);
        alert.setContentText("No pizzas have been added to the order yet! If you have ordered already, please refresh.");
        alert.initOwner(currentOrderView.getScene().getWindow());
        alert.showAndWait();
    }

}
