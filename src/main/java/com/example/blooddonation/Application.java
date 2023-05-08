package com.example.blooddonation;

import com.example.blooddonation.screens.HomeScreen;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.io.IOException;

public class Application extends javafx.application.Application {
    public BorderPane root;
    public StackPane midPane;
    private Integer selectedWorkerId = -1;

    @Override
    public void start(Stage stage) throws IOException {
        // New border pane for content
        root = new BorderPane();

        // Top part of border pane
        HBox top = new HBox();
        top.setPrefWidth(Double.MAX_VALUE);
        top.setStyle("-fx-background-color: #E80000");
        // App name Label
        Label appName = new Label("Blood Application");
        appName.setPadding(new Insets(5, 30, 5, 30));
        appName.setStyle("-fx-font-family: 'Inter'; -fx-font-weight: bold; -fx-font-size: 30;");
        // Add elements to top of borderpane
        top.getChildren().add(appName);
        root.setTop(top);

        // Get first starting screen
        root.setCenter(new HomeScreen(this, root).getHomeContent());

        // Bottom of Screen
        HBox bottomPane = new HBox();
        bottomPane.setPrefWidth(Double.MAX_VALUE);
        bottomPane.setAlignment(Pos.CENTER_RIGHT);
        bottomPane.setStyle("-fx-background-color: #E80000");
        // Copyright text
        Label copyRight = new Label("\u00A9Cybul 2023");
        copyRight.setPadding(new Insets(5, 30, 5, 0));
        bottomPane.getChildren().add(copyRight);
        root.setBottom(bottomPane);

        Scene scene = new Scene(root, 1200, 700);
        stage.setTitle("");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

    public ImageView getNurseImage(){
        // Bottom Right image
        Image image = new Image(getClass().getResource("/images/Render_1.png").toExternalForm());
        ImageView nurseImage = new ImageView(image);
        // resize image, while keeping aspect ratio
        nurseImage.setFitHeight(300);
        nurseImage.setPreserveRatio(true);

        return nurseImage;
    }

    public int getSelectedWorkerId(){
        return selectedWorkerId;
    }

    public void setSelectedWorkerId(Integer newSelectedWorkerId){
        this.selectedWorkerId = newSelectedWorkerId;
    }
}