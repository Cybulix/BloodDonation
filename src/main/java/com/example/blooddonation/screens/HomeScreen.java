package com.example.blooddonation.screens;

import com.example.blooddonation.Application;
import com.example.blooddonation.Database;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;

import java.sql.SQLException;

public class HomeScreen {
    private StackPane homeScreen;
    private HBox homeContent;
    private BorderPane root;
    public Database db;
    public HomeScreen(BorderPane rootBorderPane) {
        // Get borderPane from Application class/file
        root = rootBorderPane;
        // HomeScreen with navigation items to another parts of app.
        // Combobox to select active worker
        Label selectionLabel = new Label("Select current Worker:");
        selectionLabel.setAlignment(Pos.CENTER);
        ComboBox workerSelection = new ComboBox<>();
        ObservableList<String> testList = workerSelection.getItems();
        testList.addAll("Worker 1", "Worker 2", "Worker 3", "Worker 4");

        // Wrap label and combobox together
        HBox workerPane = new HBox(5);
        workerPane.getChildren().addAll(selectionLabel, workerSelection);
        // Adjust Some positioning
        workerPane.setAlignment(Pos.TOP_RIGHT);
        workerPane.setPadding(new Insets(10, 25, 0, 0));
        selectionLabel.setPadding(new Insets(5, 0, 0, 0));
        StackPane.setAlignment(workerPane, Pos.TOP_RIGHT);

        homeContent = new HBox(100);
        homeContent.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        homeContent.setAlignment(Pos.CENTER);

        // Nav items
        VBox nav1 = createNavItem("Donors");
        VBox nav2 = createNavItem("Blood Bags");
        VBox nav3 = createNavItem("Hospitals");

        // Add them to HBox
        homeContent.getChildren().addAll(nav1, nav2, nav3);

        // Bottom Right image
        ImageView nurseImage = new Application().getNurseImage();

        homeScreen = new StackPane();
        homeScreen.setAlignment(Pos.BOTTOM_RIGHT);
        homeScreen.setStyle("-fx-background-color: transparent;");

        homeScreen.getChildren().addAll(homeContent, nurseImage, workerPane);
        // Test DB
        try{
            db = new Database();
        } catch (SQLException e){
            e.printStackTrace();
        }
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

        EventHandler<MouseEvent> eventHandler = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                root.setCenter(new HospitalsScreen(root).getHospitalScreen());
            }
        };

        navItem.addEventHandler(MouseEvent.MOUSE_CLICKED, eventHandler);

        return navItem;
    }

    public StackPane getHomeContent(){
        return homeScreen;
    }
}
