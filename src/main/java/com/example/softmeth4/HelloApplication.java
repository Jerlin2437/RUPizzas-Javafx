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
    public void start(Stage primaryStage) {
        try {
            storeOrders = new StoreOrders();
            order = new Order();
            HelloApplication.primaryStage = primaryStage;
            FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource("main-menu.fxml"));
            Scene scene = new Scene(loader.load(), 640, 480);

            MainMenuController controller = loader.getController();
            controller.setHelloApplication(this);

            primaryStage.setTitle("J&J Pizzeria <Main Menu>");
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void showScreen(String fxmlFile, String title, int width, int height) {
        try {
            //can't use this code bc it doesn't let you reopen windows if they already been opened
//            if (!loaders.containsKey(fxmlFile)) {
//                FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource(fxmlFile));
//                loaders.put(fxmlFile, loader);
//            }
            FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource(fxmlFile));
            loaders.put(fxmlFile, loader);

            Scene scene = new Scene(loaders.get(fxmlFile).load(), width, height);
            Stage newStage = new Stage();
            newStage.setTitle(title);
            newStage.setScene(scene);

            //maybe need to make way to prevent opening alot of the same windows
            newStage.setOnCloseRequest(event -> {
                event.consume(); // consumes the event to prevent default close behavior
                newStage.hide(); // hides stage visibility instead of closing
            });

            newStage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static StoreOrders getStoreOrders(){
        return storeOrders;
    }
    public static Order getOrder(){
        return order;
    }
    public static void openSpecialty(){
        showScreen("specialty-pizza.fxml", "Order specialty pizzas!", 640, 500);
    }
    public static void openBuildOwn(){
        showScreen("build-own.fxml", "Build your own pizza!", 640, 500);
    }
    public static void openCurrentOrder(){
        showScreen("current-order.fxml", "Order Detail", 640, 500);
    }
    public static void openStoreOrder(){
        showScreen("store-order.fxml", "Store Orders", 640, 500);
    }
}