package com.example.blooddonation.screens;

import com.example.blooddonation.Database;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;

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
        GridPane hospitalContent = new GridPane();

        Label lbl1 = new Label("Bravis 1");
        Label lbl2 = new Label("Bravis 2");
        Label lbl3 = new Label("Bravis 3");

        hospitalContent.add(lbl1, 0, 0);
        hospitalContent.add(lbl2, 0, 1);
        hospitalContent.add(lbl3, 0, 2);

        /// Tableview



        // Add layouts to borderpane
        contentPane.setTop(top);
        contentPane.setCenter(hospitalContent);

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
