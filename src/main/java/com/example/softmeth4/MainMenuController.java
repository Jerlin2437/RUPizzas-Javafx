package com.example.softmeth4;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class MainMenuController {
    @FXML
    private Button buildPizza;
    @FXML
    private Button currentOrder;
    @FXML
    private Button specialtyPizza;
    @FXML
    private Button storeOrders;

    @FXML
    private void handleSpecialtyPizza(ActionEvent event){
        HelloApplication.openSpecialty();
    }
    @FXML
    private void handleBuildOwn(ActionEvent event){
        HelloApplication.openBuildOwn();
    }
    @FXML
    private void handleCurrentOrder(ActionEvent event){
        HelloApplication.openCurrentOrder();
    }
    @FXML
    private void handleStoreOrder(ActionEvent event){
        HelloApplication.openStoreOrder();
    }
}
