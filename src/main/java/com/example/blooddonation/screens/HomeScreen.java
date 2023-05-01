package com.example.blooddonation.screens;

import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class HomeScreen {
    private HBox homeScreen;
    public HomeScreen() {
        // HomeScreen with navigation items to another parts of app.
        homeScreen = new HBox(100);
//        homeScreen.setStyle("-fx-background-color: yellow");
        homeScreen.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        homeScreen.setAlignment(Pos.CENTER);
//        homeScreen.setHgap(100);

        // Nav items
        VBox nav1 = createNavItem("Donors");
        VBox nav2 = createNavItem("BloodBags");
        VBox nav3 = createNavItem("Hospitals");

        // Add them to GridPane
        homeScreen.getChildren().addAll(nav1, nav2, nav3);
    }

    public VBox createNavItem(String title){
        // New item
        VBox navItem = new VBox();
        navItem.setAlignment(Pos.CENTER);

        // Label with text and background
        Label navBackground = new Label(title);
        // Styling
        navBackground.setStyle("-fx-cursor: hand; -fx-font-family: 'Inter'; -fx-font-weight: bold; -fx-font-size: 24; -fx-background-color: #E80000;");
        navBackground.setAlignment(Pos.CENTER);
        // Size
        navBackground.setPrefSize(200, 150);
        navItem.getChildren().addAll(navBackground);

        return navItem;
    }

    public HBox getHomeScreen(){
        return homeScreen;
    }
}
