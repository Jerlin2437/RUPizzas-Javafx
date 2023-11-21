package com.example.softmeth4;

import com.example.softmeth4.businesslogic.Order;
import com.example.softmeth4.businesslogic.StoreOrders;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;

import java.net.URL;
import java.util.ResourceBundle;

public class StoreOrderController implements Initializable {
    public ComboBox<String> allOrders;
    public Button cancelOrder;
    private Order order;
    private StoreOrders storeOrders;
    public StoreOrderController(){
        order = HelloApplication.getOrder();
        storeOrders = HelloApplication.getStoreOrders();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        for (int x = 0; x < storeOrders.getTotalOrders(); x++){
            allOrders.getItems().add(String.valueOf(storeOrders.getOrders().get(x).getOrderNumber()));
        }
        cancelOrder.setOnAction(event -> removeOrder());
    }
    private void removeOrder() {
        if (allOrders.getValue() != null) {
            int orderId = Integer.parseInt(allOrders.getValue());
            if (storeOrders.removeOrder(orderId)) {
                showSuccessMessage();
                allOrders.getItems().remove(allOrders.getValue());
            } else {
                showErrorMessage();
            }
        } else {
            showWarningMessage();
        }
    }

    private void showSuccessMessage() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Success");
        alert.setHeaderText(null);
        alert.setContentText("Order successfully removed.");
        alert.showAndWait();
    }

    private void showErrorMessage() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText("Failed to remove the order. Please try again.");
        alert.showAndWait();
    }

    private void showWarningMessage() {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Warning");
        alert.setHeaderText(null);
        alert.setContentText("Please select an order to remove.");
        alert.showAndWait();
    }
}
