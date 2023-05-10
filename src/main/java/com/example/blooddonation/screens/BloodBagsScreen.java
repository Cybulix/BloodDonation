package com.example.blooddonation.screens;

import com.example.blooddonation.Application;
import com.example.blooddonation.Database;
import com.example.blooddonation.models.BloodBag;
import com.example.blooddonation.models.Donor;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BloodBagsScreen {
    private StackPane bloodScreen;
    private BorderPane root;
    private Database db;
    private TableView<BloodBag> bloodTableView;

    public BloodBagsScreen(Application app, BorderPane rootBorderPane){
        // Get borderPane from Application class
        root = rootBorderPane;
        // StackPane to place image on top of content
        bloodScreen = new StackPane();
        // Layout for Content
        BorderPane contentPane = new BorderPane();

        // Connect to DB
        try{
            db = new Database();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        // Top part of layout
        StackPane top = new StackPane();
        HBox topLeft = new HBox();
        HBox topRight = new HBox();
        topRight.setAlignment(Pos.TOP_RIGHT);
        // Blood Screen Label
        Label bloodLabel = new Label("Blood Bags");
        bloodLabel.setStyle("-fx-font-family: 'Inter'; -fx-font-weight: bold; -fx-font-size: 20;");
        bloodLabel.setPadding(new Insets(10, 20, 10, 20));
        topLeft.getChildren().add(bloodLabel);

        // Button to return to Home Screen
        Button returnButton = new Button("Return");
        returnButton.setStyle("-fx-background-color: #FEE7ED; -fx-cursor: hand; -fx-border-width: 1px; -fx-border-color: black;");
        returnButton.setPadding(new Insets(10, 20, 10, 20));
        topRight.getChildren().add(returnButton);

        // Set margins
        HBox.setMargin(bloodLabel, new Insets(0, 0, 0, 40));
        HBox.setMargin(returnButton, new Insets(3, 80, 3, 0));

        // Return onclick to home screen
        returnButton.setOnAction(e -> root.setCenter(new HomeScreen(app, root).getHomeContent()));

        top.getChildren().addAll(topLeft, topRight);

        // Main content
        // TODO
        bloodTableView = new TableView<BloodBag>();
        bloodTableView.setEditable(true);
        // Table columns
        TableColumn<BloodBag, Integer> idColumn = new TableColumn<BloodBag, Integer>("ID");
        idColumn.setCellValueFactory(new PropertyValueFactory<BloodBag, Integer>("id"));

        TableColumn<BloodBag, String> bloodTypeColumn = new TableColumn<BloodBag, String>("Blood Type");
        bloodTypeColumn.setCellValueFactory(new PropertyValueFactory<BloodBag, String>("bloodType"));

        TableColumn<BloodBag, Integer> amountColumn = new TableColumn<BloodBag, Integer>("Amount");
        amountColumn.setCellValueFactory(new PropertyValueFactory<BloodBag, Integer>("amount"));

        TableColumn<BloodBag, Date> dateColumn = new TableColumn<BloodBag, Date>("Date");
        dateColumn.setCellValueFactory(new PropertyValueFactory<BloodBag, Date>("date"));

        TableColumn<BloodBag, Integer> donorIDColumn = new TableColumn<BloodBag, Integer>("Donor ID");
        donorIDColumn.setCellValueFactory(new PropertyValueFactory<BloodBag, Integer>("donorID"));

        // Add columns to table
        bloodTableView.getColumns().addAll(idColumn, bloodTypeColumn, amountColumn, dateColumn, donorIDColumn);

        // Get rid of empty column
        bloodTableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        try{
            // Get data from Database
            ResultSet rs = db.getBloodBagsData();
            while (rs.next()){
                // Create new object for each blood bag
                BloodBag bloodBag = new BloodBag(rs);
                // Insert into table
                bloodTableView.getItems().add(bloodBag);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


        // Add elements/panes to borderpane
        contentPane.setTop(top);
        contentPane.setCenter(bloodTableView);

        // Bottom Right image
        ImageView nurseImage = app.getNurseImage();

        bloodScreen.getChildren().addAll(contentPane, nurseImage);
        StackPane.setAlignment(nurseImage, Pos.BOTTOM_RIGHT);

    }

    public StackPane getBloodScreen(){ return bloodScreen; }
}
