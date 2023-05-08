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

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class HomeScreen {
    private StackPane homeScreen;
    private HBox homeContent;
    private BorderPane root;
    public Database db;
    private final Application app;
    public HomeScreen(Application rootApp, BorderPane rootBorderPane) {
        // Get borderPane from Application class/file
        root = rootBorderPane;

        app = rootApp;

        // Workers List
        List<Worker> workers = new ArrayList<>();
        // Connect with DB, and get workers data
        try{
            db = new Database();
            ResultSet rs = db.getWorkersData();
            while (rs.next()){
                // Create new object
                Worker worker = new Worker(rs);
                // Insert worker object into combobox
                workers.add(worker);
            }
        } catch (SQLException e){
            throw new RuntimeException(e);
        }

        // Worker selection box
        ComboBox<Worker> workerSelection = new ComboBox<>();
        workerSelection.setPromptText("Select current Worker");
        ObservableList<Worker> workersList = FXCollections.observableArrayList(workers);
        // Add workers names to combobox
        workerSelection.setItems(workersList);


        // Positioning
        StackPane.setAlignment(workerSelection, Pos.TOP_RIGHT);
        StackPane.setMargin(workerSelection, new Insets(10, 40, 0 , 0));
        // Set an event handler for the ComboBox to handle when a worker is selected
        workerSelection.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                // Get the selected item from the ComboBox
                Worker selectedWorker = workerSelection.getSelectionModel().getSelectedItem();
                // Loop through the list of workers to find the worker whose name matches the selected item
                if (selectedWorker != null) {
                    // Get the ID of the selected worker
                    int selectedId = selectedWorker.getId();
                    app.setSelectedWorkerId(selectedId);
                }
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
                root.setCenter(new HospitalsScreen(app, root).getHospitalScreen());
            }
        };

        navItem.addEventHandler(MouseEvent.MOUSE_CLICKED, eventHandler);

        return navItem;
    }

    public StackPane getHomeContent(){
        return homeScreen;
    }
}
