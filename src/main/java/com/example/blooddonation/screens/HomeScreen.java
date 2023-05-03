package com.example.blooddonation.screens;

import com.example.blooddonation.Application;
import com.example.blooddonation.Database;
import com.example.blooddonation.models.Worker;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
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
import java.util.ArrayList;
import java.util.List;

public class HomeScreen {
    private StackPane homeScreen;
    private HBox homeContent;
    private BorderPane root;
    public Database db;
    public HomeScreen(BorderPane rootBorderPane) {
        // Get borderPane from Application class/file
        root = rootBorderPane;

        // Workers List
        List<Worker> workers = new ArrayList<>();
        workers.add(new Worker(1, "John", "Doe", "123-456-7890", "john.doe@example.com"));
        workers.add(new Worker(2, "Jane", "Doe", "123-456-7891", "jane.doe@example.com"));
        // Worker selection box
        ComboBox<String> workerSelection = new ComboBox<>();
        workerSelection.setPromptText("Select current Worker");
        ObservableList<String> workersList = FXCollections.observableArrayList();
        for (Worker worker : workers){
            workersList.add(worker.getFullName());
        }
        // Add workers names to combobox
        workerSelection.setItems(workersList);


        // Positioning
        StackPane.setAlignment(workerSelection, Pos.TOP_RIGHT);
        StackPane.setMargin(workerSelection, new Insets(10, 40, 0 , 0));
        // Functionality
        workerSelection.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                System.out.println(workerSelection.valueProperty().get());
            }
        });

        // HomeScreen with navigation items to another parts of app.
        homeContent = new HBox(100);
//        homeScreen.setStyle("-fx-background-color: yellow");
        homeContent.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        homeContent.setAlignment(Pos.CENTER);
//        homeScreen.setHgap(100);

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

        homeScreen.getChildren().addAll(homeContent, nurseImage, workerSelection);
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
