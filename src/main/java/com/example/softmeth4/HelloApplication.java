package com.example.softmeth4;

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

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) {
        primaryStage = stage;
        showScreen("main-menu.fxml", "Main Menu", 640, 480);
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
}