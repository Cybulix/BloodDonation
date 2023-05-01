package com.example.blooddonation;

import com.example.blooddonation.screens.HomeScreen;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
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
        top.setStyle("-fx-background-color: #E80000");
        // App name Label
        Label appName = new Label("Blood Application");
        appName.setPadding(new Insets(5, 30, 5, 30));
        appName.setStyle("-fx-font-family: 'Inter'; -fx-font-weight: bold; -fx-font-size: 30;");
        // Add elements to top of borderpane
        top.getChildren().add(appName);
        root.setTop(top);

        // Mid-Part of border pane
        Image image = new Image(getClass().getResource("/images/Render_1.png").toExternalForm());
        ImageView nurseImage = new ImageView(image);
        // resize image, while keeping aspect ratio
        nurseImage.setFitHeight(300);
        nurseImage.setPreserveRatio(true);
        StackPane midPane = new StackPane();
        midPane.setAlignment(Pos.BOTTOM_RIGHT);
        midPane.setStyle("-fx-background-color: transparent;");
        midPane.getChildren().addAll(new HomeScreen().getHomeScreen(), nurseImage);

        // Get first starting screen
        root.setCenter(midPane);

        // Bottom of Screen
        HBox bottomPane = new HBox();
        bottomPane.setPrefWidth(Double.MAX_VALUE);
        bottomPane.setAlignment(Pos.CENTER_RIGHT);
        bottomPane.setStyle("-fx-background-color: #E80000");
        Label copyRight = new Label("\u00A9Cybul 2023");
        copyRight.setPadding(new Insets(5, 30, 5, 0));
        bottomPane.getChildren().add(copyRight);
        root.setBottom(bottomPane);

//        root.setBottom(midPane);

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