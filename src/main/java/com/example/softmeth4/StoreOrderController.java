package com.example.softmeth4;

import com.example.softmeth4.businesslogic.Order;
import com.example.softmeth4.businesslogic.StoreOrders;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * This controller class that manages store orders for a JavaFX application.
 * This class contains methods and event handlers to handle viewing and cancelling orders
 * that have been placed, as well as methods to export an order to a text file.
 *
 * @author Jerlin Yuen, Jason Lei
 */
public class StoreOrderController implements Initializable {
    @FXML
    public ComboBox<String> allOrders;
    @FXML
    public Button cancelOrder;
    @FXML
    public TextField orderTotal;
    public Button refreshOrder;
    @FXML
    private Button exportStoreOrders;
    @FXML
    private ListView<String> displayStoreOrders;
    private Order order;
    private StoreOrders storeOrders;

    /**
     * Default constructor, initializing an instance of a customer's pizza order
     * and the store's collection of orders
     */
    public StoreOrderController(){
        order = HelloApplication.getOrder();
        storeOrders = HelloApplication.getStoreOrders();
    }

    /**
     * Initializes listeners and event handlers for various UI elements, including
     * the combo box selection changes and button clicks that occur in the interface.
     * (ex: cancels orders and exports orders to a text file when the respective buttons are clicked)
     *
     * @param url - url
     * @param resourceBundle - resource bundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        for (int x = 0; x < storeOrders.getTotalOrders(); x++){
            allOrders.getItems().add(String.valueOf(storeOrders.getOrders().get(x).getOrderNumber()));
        }
        cancelOrder.setOnAction(event -> removeOrder());
        exportStoreOrders.setOnAction(event -> exportOrdersToFile());
        allOrders.setOnAction(event -> displaySelectedOrder());
        refreshOrder.setOnAction(event -> refresh());
    }

    /**
     * Displays a selected order when an order number is selected from a combo box,
     * as well as its details.
     */
    private void displaySelectedOrder(){
        //clears listview first of previous items/orders

        displayStoreOrders.getItems().clear();
        if (allOrders.getValue() != null){
            int orderNumber = Integer.parseInt(allOrders.getValue());
            Order selectedOrder = storeOrders.getOrderByNumber(orderNumber);
            if (selectedOrder != null){
                displayStoreOrders.getItems().add(selectedOrder.toFinalOrderDetailsString());
                orderTotal.setText(String.format("%.2f", selectedOrder.getOrderTotalValue()));
            }
        }
    }

    /**
     * Displays a selected order from the list of store orders, handling errors and displaying
     * specific error or warning messages.
     */
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

    /**
     * Exports a selected order to a text file, displaying an error popup upon success and failure.
     */
    private void exportOrdersToFile(){
        if(allOrders.getItems().size() != storeOrders.getTotalOrders()){
            showExportErrorMessage();
            return;
        }
        boolean exportSuccess = storeOrders.export();
        if (exportSuccess){
            showExportSuccessMessage();
        } else{
            showExportErrorMessage();
        }
    }

    /**
     * Displays a specific success message if export to a text file succeeds
     */
    private void showExportSuccessMessage(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Export Success");
        alert.setHeaderText(null);
        alert.setContentText("Store orders successfully exported to store_orders.txt.");
        alert.showAndWait();
    }

    /**
     * Displays a specific error message if export to a text file fails
     */
    private void showExportErrorMessage(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Export Error");
        alert.setHeaderText(null);
        alert.setContentText("Failed to export store orders. You need to refresh. Please try again.");
        alert.showAndWait();
    }

    /**
     * Displays a specific success message if an order is successfully removed from store orders
     */
    private void showSuccessMessage() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Success");
        alert.setHeaderText(null);
        alert.setContentText("Order successfully removed.");
        alert.showAndWait();
    }

    /**
     * Displays a specific error message if order is not able to be removed from store orders
     */
    private void showErrorMessage() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText("Failed to remove the order. Please try again.");
        alert.showAndWait();
    }

    /**
     * Displays a specific warning message if an order is not selected
     */
    private void showWarningMessage() {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Warning");
        alert.setHeaderText(null);
        alert.setContentText("Please select an order to remove.");
        alert.showAndWait();
    }

    public void refresh() {
        allOrders.getItems().clear();
        for (int x = 0; x < storeOrders.getTotalOrders(); x++){
            allOrders.getItems().add(String.valueOf(storeOrders.getOrders().get(x).getOrderNumber()));
        }
    }
}
