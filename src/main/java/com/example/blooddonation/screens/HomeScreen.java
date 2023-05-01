package com.example.blooddonation.screens;

import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class HomeScreen {
    private GridPane homeScreen;
    public HomeScreen() {
        // HomeScreen with navigation items to another parts of app.
        homeScreen = new GridPane();
        homeScreen.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        homeScreen.setHgap(100);

        // Nav items
        VBox nav1 = createNavItem("Donors");
        VBox nav2 = createNavItem("BloodBags");
        VBox nav3 = createNavItem("Hospitals");

        // Add them to GridPane
        homeScreen.add(nav1, 0, 0);
        homeScreen.add(nav2, 1, 0);
        homeScreen.add(nav3, 2, 0);
    }

    public VBox createNavItem(String title){
        VBox navItem = new VBox();
        navItem.setPrefSize(130, 240);

        Label navTitle = new Label(title);
        Label navBackground = new Label();
        navBackground.setStyle("-fx-background-color: lightgray");
        navBackground.setPrefSize(130, 180);

        navItem.getChildren().addAll(navBackground, navTitle);

        return navItem;
    }

    public GridPane getHomeScreen(){
        return homeScreen;
    }
}
