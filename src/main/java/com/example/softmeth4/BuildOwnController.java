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

public class BuildOwnController implements Initializable {

    private Order order;
    private StoreOrders storeOrders;
    private Pizza pizza;
    private int toppingCount;

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
    public Button addTopping;
    @FXML
    public Button removeTopping;
    @FXML
    private ListView<Topping> selectedToppings;
    @FXML
    private ListView<Topping> notSelectedToppings;

    @FXML
    private ToggleGroup sauceRadioButton;
    public BuildOwnController(){
        order = HelloApplication.getOrder();
        storeOrders = HelloApplication.getStoreOrders();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        sizeComboBox.valueProperty().addListener((observable, oldValue, newValue) ->{
            pizza = pizzaParse();
            updatePizzaPrice();
        });

        notSelectedToppings.getItems().addAll(Topping.values());
        addTopping.setOnAction(event -> addToppings());
        removeTopping.setOnAction(event -> removeToppings());
    }

    private void updatePizzaPrice(){
        if (pizza != null){
            if (toppingCount > 3){
                //base price without toppings
                double basePrice = pizza.price();
                //if more than 3 toppings, add $1.49 for each additional topping after 3
                double additionalToppingPrice = Math.max(0, toppingCount - 3) * 1.49;
                double totalPrice = basePrice + additionalToppingPrice;
                String formattedValue = String.format("%.2f", totalPrice);
                buildPrice.setText(formattedValue);
            } else{
                String formattedValue = String.format("%.2f", pizza.price());
                buildPrice.setText(formattedValue);
            }
        }
    }

    private Pizza pizzaParse(){
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
        return PizzaMaker.createPizza(pizzaType +" " + size + " false false " + sauce + " " + allToppings.toString().trim());
    }

    //need at least 3 toppings, add this requirement
    //for each additional topping after 3, add $1.49
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
            toppingCount++;
            updatePizzaPrice();
        }
    }
    private void removeToppings(){
        Topping selectedTopping = selectedToppings.getSelectionModel().getSelectedItem();
        if (selectedTopping != null){
            selectedToppings.getItems().remove(selectedTopping);
            notSelectedToppings.getItems().add(selectedTopping);
            toppingCount--;
            updatePizzaPrice();
        }
    }

//    public class addToOrderHandler implements EventHandler<ActionEvent> {
//        @Override
//        public void handle(ActionEvent actionEvent) {
//            addToOrder();
//        }
//        private void addToOrder(){
//            if (chooseSpecialty.getValue() != null){
//                if(extraCheese.isSelected())
//                    pizza.setExtraCheese(true);
//                if (extraSauce.isSelected())
//                    pizza.setExtraSauce(true);
//                order.addPizza(pizza);
//                pizza = null;
//                showSuccessPopup();
//            } else
//                showFailurePopup();
//        }
//        private void showSuccessPopup() {
//            Alert alert = new Alert(Alert.AlertType.INFORMATION);
//            alert.setTitle("Pizza Order Successful");
//            alert.setHeaderText(null);
//            alert.setContentText("Your pizza order has been added successfully!");
//            alert.initOwner(chooseSpecialty.getScene().getWindow());
//            alert.showAndWait();
//        }
//        private void showFailurePopup() {
//            Alert alert = new Alert(Alert.AlertType.INFORMATION);
//            alert.setTitle("Pizza Order Unsuccessful");
//            alert.setHeaderText(null);
//            alert.setContentText("You did not select a pizza!");
//            alert.initOwner(chooseSpecialty.getScene().getWindow());
//            alert.showAndWait();
//        }



}
