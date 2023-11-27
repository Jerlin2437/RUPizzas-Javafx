package com.example.softmeth4;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

/**
 * This controller class allows customers to access different windows to place and view pizza orders
 * and is a controller for a JavaFX application's main menu.
 *
 * @author Jason Lei, Jerlin Yuen
 */
public class MainMenuController {

    @FXML
    private Button buildPizza;
    @FXML
    private Button currentOrder;
    @FXML
    private Button specialtyPizza;
    @FXML
    private Button storeOrdersBut;
    @FXML
    private Button storeOrders;
    private HelloApplication helloApplication;

    /**
     * Setter method (mutator)
     *
     * @param helloApplication instance of the class
     */
    public void setHelloApplication(HelloApplication helloApplication) {
        this.helloApplication = helloApplication;
    }

    /**
     * Event handler that calls the method openSpecialty() when the action event
     * is triggered (button is pressed)
     * Opens specialty pizza view window
     *
     * @param event action event
     */
    @FXML
    private void handleSpecialtyPizza(ActionEvent event) {
        HelloApplication.openSpecialty();
    }

    /**
     * Event handler that calls the method openBuildOwn() when the action event
     * is triggered (button is pressed)
     * Opens "build your own" pizza view window
     *
     * @param event action event
     */
    @FXML
    private void handleBuildOwn(ActionEvent event) {
        HelloApplication.openBuildOwn();
    }

    /**
     * Event handler that calls the method openCurrentOrder() when the action event
     * is triggered (button is pressed)
     * Opens current order view window
     *
     * @param event action event
     */
    @FXML
    private void handleCurrentOrder(ActionEvent event) {
        HelloApplication.openCurrentOrder();
    }

    /**
     * Event handler that calls the method openStoreOrder() when the action event
     * is triggered (button is pressed)
     * Opens store order view window
     *
     * @param event action event
     */
    @FXML
    private void handleStoreOrder(ActionEvent event) {
        HelloApplication.openStoreOrder();
    }
}

