package com.example.softmeth4;

import com.example.softmeth4.businesslogic.Order;
import com.example.softmeth4.businesslogic.StoreOrders;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class HelloApplication extends Application {
    private static final Map<String, FXMLLoader> loaders = new HashMap<>();
    private static Stage primaryStage;
    private static StoreOrders storeOrders;
    private static Order order;
    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) {
        storeOrders = new StoreOrders();
        order = new Order();
        primaryStage = stage;
        showScreen("main-menu.fxml", "J&J Pizzeria <Main Menu>", 640, 480);
    }

    public static void showScreen(String fxmlFile, String title, int width, int height) {
        try {
            if (!loaders.containsKey(fxmlFile)) {
                FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource(fxmlFile));
                loaders.put(fxmlFile, loader);
            }

            Scene scene = new Scene(loaders.get(fxmlFile).load(), width, height);
            primaryStage.setTitle(title);
            primaryStage.setScene(scene);
            primaryStage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static StoreOrders getStoreOrders() {
        return storeOrders;
    }
    public static Order getOrder(){
        return order;
    }
    public static void openSpecialty(){
        showScreen("specialty-pizza.fxml", "Order specialty pizzas!", 640, 480);
    }
    public static void openBuildOwn(){
        showScreen("build-own.fxml", "Build your own pizza!", 640, 480);
    }
    public static void openCurrentOrder(){
        showScreen("current-order.fxml", "Order Detail", 640, 480);
    }
    public static void openStoreOrder(){
        showScreen("store-order.fxml", "Store Orders", 640, 480);
    }

}