package com.example.blooddonation;

import com.example.blooddonation.screens.HomeScreen;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.io.IOException;

public class Application extends javafx.application.Application {
    @Override
    public void start(Stage stage) throws IOException {
        // New border pane for content
        BorderPane root = new BorderPane();

        // Top part of border pane
        HBox top = new HBox();
        top.setPrefWidth(Double.MAX_VALUE);
        // App name Label
        Label appName = new Label("Blood Application");
        appName.setPadding(new Insets(20, 30, 10, 30));
        appName.setStyle("-fx-font-weight: bold;");
        // Add elements to top of borderpane
        top.getChildren().add(appName);
        root.setTop(top);

        // Mid-Part of border pane
        root.setCenter(new HomeScreen().getHomeScreen());

        Scene scene = new Scene(root, 1200, 700);
        stage.setTitle("Blood Donation");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}