package com.example.blooddonation.screens;

import com.example.blooddonation.Application;
import com.example.blooddonation.Database;
import com.example.blooddonation.models.Hospital;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;

import java.sql.ResultSet;
import java.sql.SQLException;

public class HospitalsScreen {
    private StackPane hospitalScreen;
    private BorderPane root;
    public Database db;

    public HospitalsScreen(BorderPane rootBorderPane){
        // Get borderPane from Application class
        root = rootBorderPane;
        // StackPane to place image on top of content
        hospitalScreen = new StackPane();
        // Layout for Content
        BorderPane contentPane = new BorderPane();

        // Connect to DB
        try{
            db = new Database();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        // Top part of layout
        HBox top = new HBox();
        top.setAlignment(Pos.TOP_RIGHT);
        // Button to return to Home Screen
        Button returnButton = new Button("Return");
        returnButton.setStyle("-fx-background-color: #FEE7ED; -fx-cursor: hand; -fx-border-width: 1px; -fx-border-color: black;");
        returnButton.setPadding(new Insets(10, 20, 10, 20));
        HBox.setMargin(returnButton, new Insets(3, 80, 3, 0));

        // Return onclick to home screen
        returnButton.setOnAction(e -> root.setCenter(new HomeScreen(root).getHomeContent()));

        top.getChildren().add(returnButton);

        // Main content
        /// Tableview
        TableView<Hospital> hospitalTable = new TableView<Hospital>();
        // Table columns
        TableColumn<Hospital, Integer> idColumn = new TableColumn<Hospital, Integer>("ID");
        idColumn.setCellValueFactory(new PropertyValueFactory<Hospital, Integer>("id"));

        TableColumn<Hospital, String> nameColumn = new TableColumn<Hospital, String>("Name");
        nameColumn.setCellValueFactory(new PropertyValueFactory<Hospital, String>("name"));

        TableColumn<Hospital, String> cityColumn = new TableColumn<Hospital, String>("City");
        cityColumn.setCellValueFactory(new PropertyValueFactory<Hospital, String>("city"));

        TableColumn<Hospital, String> adresColumn = new TableColumn<Hospital, String>("Adres");
        adresColumn.setCellValueFactory(new PropertyValueFactory<Hospital, String>("adres"));

        TableColumn<Hospital, String> postalCodeColumn = new TableColumn<Hospital, String>("PostalCode");
        postalCodeColumn.setCellValueFactory(new PropertyValueFactory<Hospital, String>("postalCode"));

        TableColumn<Hospital, String> phoneNumberColumn = new TableColumn<Hospital, String>("PhoneNumber");
        phoneNumberColumn.setCellValueFactory(new PropertyValueFactory<Hospital, String>("phoneNumber"));

        TableColumn<Hospital, String> emailColumn = new TableColumn<Hospital, String>("Email");
        emailColumn.setCellValueFactory(new PropertyValueFactory<Hospital, String>("email"));

        // Add columns to table
        hospitalTable.getColumns().addAll(idColumn, nameColumn, cityColumn, adresColumn, postalCodeColumn, phoneNumberColumn, emailColumn);

        // Get rid of empty column
        hospitalTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);


        try{
            // Get data from Database
            ResultSet rs = db.getHospitalData();
            while (rs.next()){
                // Create new object for each hospital
                Hospital hospital = new Hospital(rs);
                // Insert into table
                hospitalTable.getItems().add(hospital);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        // Add elements/panes to borderpane
        contentPane.setTop(top);
        contentPane.setCenter(hospitalTable);

        //Bottom Right image
        ImageView nurseImage = new Application().getNurseImage();

        hospitalScreen.getChildren().addAll(contentPane, nurseImage);
        StackPane.setAlignment(nurseImage, Pos.BOTTOM_RIGHT);
    }

    public StackPane getHospitalScreen() {
        return hospitalScreen;
    }
}
