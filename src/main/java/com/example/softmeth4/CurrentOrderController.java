package com.example.softmeth4;

import com.example.softmeth4.businesslogic.Order;

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

public class CurrentOrderController implements Initializable {
    private Order order;
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
    private TextField salesTax;
    @FXML
    private TextField subtotal;
    public CurrentOrderController(){
        order = HelloApplication.getOrder();
    }

    public CurrentOrderController(Order order, ListView<String> currentOrderView){
        this.order = order;
        this.currentOrderView = currentOrderView;
        updateCurrentOrderView();
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        removePizza.setOnAction(event -> {
            removeSelectedPizza();
        });
        placeOrder.setOnAction(event -> {
            placeOrder();
        });
    }

    public void placeOrder(){
        if (!order.getPizzas().isEmpty()){
            showAddedPopup();
            updateCurrentOrderView();
        } else{
            showEmptyPopup();
        }
    }

    public void removeSelectedPizza(){
        int selectedIndex = currentOrderView.getSelectionModel().getSelectedIndex();
        if (selectedIndex != -1){
            Pizza selectedPizza = order.getPizzas().get(selectedIndex);
            order.removePizza(selectedPizza);
            updateCurrentOrderView();
            showRemovedPopup();
        }
    }

    private void updateCurrentOrderView(){
        List<String> pizzaSummaries = order.getPizzaDetails();
        ObservableList<String> observableList = FXCollections.observableArrayList(pizzaSummaries);
        currentOrderView.setItems(observableList);

        currentOrderNumber = String.valueOf(order.getOrderNumber());
        orderNumber.setText(currentOrderNumber);

        calculateSubtotal();
        calculateSalesTax();
        calculateOrderTotal();

    }

    private void calculateSubtotal() {
        subtotalValue = 0.0;
        for (Pizza pizza : order.getPizzas()) {
            subtotalValue += pizza.price();
        }
        subtotal.setText(String.format("%.2f", subtotalValue));
    }

    private void calculateSalesTax() {
        //6.625% sales tax in NJ
        double taxRate = 0.06625;
        salesTaxValue = subtotalValue * taxRate;
        salesTax.setText(String.format("%.2f", salesTaxValue));
    }

    private void calculateOrderTotal() {
        orderTotalValue = subtotalValue + salesTaxValue;
        orderTotal.setText(String.format("%.2f", orderTotalValue));
    }


    private void showRemovedPopup() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Pizza Order Removed Successfully");
        alert.setHeaderText(null);
        alert.setContentText("Order removed!");
        alert.initOwner(currentOrderView.getScene().getWindow());
        alert.showAndWait();
    }

    private void showAddedPopup() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Pizza Order Added Successfully");
        alert.setHeaderText(null);
        alert.setContentText("Order placed!");
        alert.initOwner(currentOrderView.getScene().getWindow());
        alert.showAndWait();
    }

    private void showEmptyPopup() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Pizza Order is Empty");
        alert.setHeaderText(null);
        alert.setContentText("No pizzas have been added to the order yet!");
        alert.initOwner(currentOrderView.getScene().getWindow());
        alert.showAndWait();
    }

}
