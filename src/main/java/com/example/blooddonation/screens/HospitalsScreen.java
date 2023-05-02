package com.example.blooddonation.screens;

import com.example.blooddonation.Database;
import com.example.blooddonation.models.Hospital;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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
        HBox.setMargin(returnButton, new Insets(5, 80, 0, 0));

        // Return onclick to home screen
        returnButton.setOnAction(e -> root.setCenter(new HomeScreen(root).getHomeContent()));

        top.getChildren().add(returnButton);

        // Main content
        /// Tableview
        TableView hospitalTable = new TableView<Hospital>();
        TableColumn idColumn = new TableColumn<Hospital, Integer>("ID");
        idColumn.setCellValueFactory(new PropertyValueFactory<Hospital, Integer>("id"));

        TableColumn nameColumn = new TableColumn<Hospital, String>("Name");
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

        // Add columns to table
        hospitalTable.getColumns().add(idColumn);
        hospitalTable.getColumns().add(nameColumn);

        // Get rid of empty column
        hospitalTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

//        hospitalTable.getItems().addAll();

        try{
            ResultSet rs = db.getHospitalData();

            while (rs.next()){
                Integer hospitalId = rs.getInt("id");
                String hospitalName = rs.getString("name");

                Hospital hospital = new Hospital(rs);
                System.out.println(hospital.getId());
                System.out.println(hospital.getName());
                hospitalTable.getItems().add(hospital);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        // Add elements/panes to borderpane
        contentPane.setTop(top);
        contentPane.setCenter(hospitalTable);

        // Bottom Right image
        Image image = new Image(getClass().getResource("/images/Render_1.png").toExternalForm());
        ImageView nurseImage = new ImageView(image);
        // resize image, while keeping aspect ratio
        nurseImage.setFitHeight(300);
        nurseImage.setPreserveRatio(true);

        hospitalScreen.getChildren().addAll(contentPane, nurseImage);
        StackPane.setAlignment(nurseImage, Pos.BOTTOM_RIGHT);
    }

    public StackPane getHospitalScreen() {
        return hospitalScreen;
    }
}
