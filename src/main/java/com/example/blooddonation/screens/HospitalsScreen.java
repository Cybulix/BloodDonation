package com.example.blooddonation.screens;

import com.example.blooddonation.Application;
import com.example.blooddonation.Database;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;

public class HospitalsScreen {
    private StackPane hospitalScreen;

    public Database db;

    public HospitalsScreen(){
        // StackPane to place image on top of content
        hospitalScreen = new StackPane();
        // Layout for Content
        BorderPane contentPane = new BorderPane();

        // Top part of layout
        HBox top = new HBox();
        // Button to return to Home Screen
        Button returnButton = new Button("Return");
        returnButton.setOnAction(e -> new Application().returnHome());

        top.getChildren().add(returnButton);

        // Main content
        GridPane hospitalContent = new GridPane();

        Label lbl1 = new Label("Bravis 1");
        Label lbl2 = new Label("Bravis 2");
        Label lbl3 = new Label("Bravis 3");

        hospitalContent.add(lbl1, 0, 0);
        hospitalContent.add(lbl2, 0, 1);
        hospitalContent.add(lbl3, 0, 2);

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
