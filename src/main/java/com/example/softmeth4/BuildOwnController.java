package com.example.softmeth4;

import com.example.softmeth4.businesslogic.Order;
import com.example.softmeth4.enums.Topping;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;

import java.net.URL;
import java.util.ResourceBundle;

public class BuildOwnController implements Initializable {

    private Order order;
    @FXML
    public Button addTopping;
    @FXML
    public Button removeTopping;
    @FXML
    private ListView<Topping> selectedToppings;
    @FXML
    private ListView<Topping> notSelectedToppings;
    public BuildOwnController(){
        order = HelloApplication.getOrder();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        notSelectedToppings.getItems().addAll(Topping.values());
        addTopping.setOnAction(event -> addToppings());
        removeTopping.setOnAction(event -> removeToppings());
    }
    private void addToppings() {
        // Get the selected item from notSelectedToppings
        Topping selectedTopping = notSelectedToppings.getSelectionModel().getSelectedItem();
        if (selectedToppings.getItems().size() >= 7){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Too many toppings!");
            alert.setHeaderText(null);
            alert.setContentText("You can only have a max of 7 toppings!");
            alert.initOwner(addTopping.getScene().getWindow());
            alert.showAndWait();
        }
        else if (selectedTopping != null) {
            notSelectedToppings.getItems().remove(selectedTopping);
            selectedToppings.getItems().add(selectedTopping);
        }
    }
    private void removeToppings(){
        Topping selectedTopping = selectedToppings.getSelectionModel().getSelectedItem();
        if (selectedTopping != null){
            selectedToppings.getItems().remove(selectedTopping);
            notSelectedToppings.getItems().add(selectedTopping);
        }
    }
}
