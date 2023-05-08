package com.example.blooddonation.screens;

import com.example.blooddonation.Application;
import com.example.blooddonation.Database;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;

import java.sql.SQLException;

public class DonorsScreen {
    private StackPane donorsScreen;
    private BorderPane root;
    private Database db;

    public DonorsScreen(Application app, BorderPane rootBorderPane){
        // Get borderPane from Application class
        root = rootBorderPane;
        // StackPane to place image on top of content
        donorsScreen = new StackPane();
        // Layout for Content
        BorderPane contentPane = new BorderPane();

        // Connect to DB
        try{
            db = new Database();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        System.out.println(app.getSelectedWorkerId());
        // Top part of layout
        StackPane top = new StackPane();
        HBox topLeft = new HBox();
        HBox topRight = new HBox();
        topRight.setAlignment(Pos.TOP_RIGHT);
        // Donors Screen Label
        Label donorsLabel = new Label("Donors");
        donorsLabel.setStyle("-fx-font-family: 'Inter'; -fx-font-weight: bold; -fx-font-size: 20;");
        donorsLabel.setPadding(new Insets(10, 20, 10, 20));
        topLeft.getChildren().add(donorsLabel);

        // Button to return to Home Screen
        Button returnButton = new Button("Return");
        returnButton.setStyle("-fx-background-color: #FEE7ED; -fx-cursor: hand; -fx-border-width: 1px; -fx-border-color: black;");
        returnButton.setPadding(new Insets(10, 20, 10, 20));
        topRight.getChildren().add(returnButton);

        // Set margins
        HBox.setMargin(donorsLabel, new Insets(0, 0, 0, 40));
        HBox.setMargin(returnButton, new Insets(3, 80, 3, 0));

        // Return onlick to home screen
        returnButton.setOnAction(e -> root.setCenter(new HomeScreen(app, root).getHomeContent()));

        top.getChildren().addAll(topLeft, topRight);

        // Main content
        // TODO

        // Add elements/panes to borderpane
        contentPane.setTop(top);
//        contentPane.setCenter();

        // Bottom Right image
        ImageView nurseImage = app.getNurseImage();

        donorsScreen.getChildren().addAll(contentPane, nurseImage);
        StackPane.setAlignment(nurseImage, Pos.BOTTOM_RIGHT);
    }

    public StackPane getDonorsScreen(){
        return donorsScreen;
    }
}
