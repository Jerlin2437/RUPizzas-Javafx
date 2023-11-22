package com.example.softmeth4;

import com.example.softmeth4.businesslogic.Order;
import com.example.softmeth4.businesslogic.StoreOrders;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.nio.Buffer;
import java.util.ResourceBundle;

public class StoreOrderController implements Initializable {
    @FXML
    public ComboBox<String> allOrders;
    @FXML
    public Button cancelOrder;
    @FXML
    private Button exportStoreOrders;
    @FXML
    private ListView<String> displayStoreOrders;
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
        exportStoreOrders.setOnAction(event -> exportOrdersToFile());
        allOrders.setOnAction(event -> displaySelectedOrder());
    }

    private void displaySelectedOrder(){
        //clears listview first of previous items/orders
        displayStoreOrders.getItems().clear();
        if (allOrders.getValue() != null){
            int orderNumber = Integer.parseInt(allOrders.getValue());
            Order selectedOrder = storeOrders.getOrderByNumber(orderNumber);
            if (selectedOrder != null){
                displayStoreOrders.getItems().add(selectedOrder.toFinalOrderDetailsString());
            }

        }
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

    private void exportOrdersToFile(){
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("store_orders.txt"))) {
            for (Order order : storeOrders.getOrders()) {
                writer.write(order.toFinalOrderDetailsString());
                writer.newLine();
            }
            showExportSuccessMessage();
        } catch (IOException e) {
            e.printStackTrace();
            showExportErrorMessage();
        }
    }

    private void showExportSuccessMessage(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Export Success");
        alert.setHeaderText(null);
        alert.setContentText("Store orders successfully exported to store_orders.txt.");
        alert.showAndWait();
    }

    private void showExportErrorMessage(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Export Error");
        alert.setHeaderText(null);
        alert.setContentText("Failed to export store orders. Please try again.");
        alert.showAndWait();
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
