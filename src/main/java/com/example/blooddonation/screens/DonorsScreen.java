package com.example.blooddonation.screens;

import com.example.blooddonation.Application;
import com.example.blooddonation.Database;
import com.example.blooddonation.models.Donor;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.util.converter.DateStringConverter;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class DonorsScreen {
    private StackPane donorsScreen;
    private BorderPane root;
    private Database db;
    private TableView<Donor> donorTableView;

    private TextField firstNameInput;
    private TextField lastNameInput;
    private TextField phoneNumberInput;
    private TextField emailInput;
    private TextField birthDateInput;
    private TextField bsnInput;

    public DonorsScreen(Application app, BorderPane rootBorderPane){
        // Get borderPane from Application class
        root = rootBorderPane;
        // StackPane to place image on top of content
        donorsScreen = new StackPane();
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
        // Donors Screen Label
        Label donorsLabel = new Label("Donors");
        donorsLabel.setStyle("-fx-font-family: 'Inter'; -fx-font-weight: bold; -fx-font-size: 20;");
        donorsLabel.setPadding(new Insets(10, 20, 10, 20));
        topLeft.getChildren().add(donorsLabel);

        // Button to return to Home Screen
        Button returnButton = new Button("Return");
        returnButton.setStyle("-fx-background-color: #FEE7ED; -fx-cursor: hand; -fx-border-width: 1px; -fx-border-color: black;");
        returnButton.setPadding(new Insets(10, 20, 10, 20));
        topRight.getChildren().add(returnButton);

        // Set margins
        HBox.setMargin(donorsLabel, new Insets(0, 0, 0, 40));
        HBox.setMargin(returnButton, new Insets(3, 80, 3, 0));

        // Return onclick to home screen
        returnButton.setOnAction(e -> root.setCenter(new HomeScreen(app, root).getHomeContent()));

        top.getChildren().addAll(topLeft, topRight);

        // Main content
        // Tableview
        donorTableView = new TableView<Donor>();
        donorTableView.setEditable(true);
        // Table columns
        TableColumn<Donor, Integer> idColumn = new TableColumn<Donor, Integer>("ID");
        idColumn.setCellValueFactory(new PropertyValueFactory<Donor, Integer>("id"));

        TableColumn<Donor, String> firstNameColumn = new TableColumn<Donor, String>("First Name");
        firstNameColumn.setCellValueFactory(new PropertyValueFactory<Donor, String>("firstName"));
        firstNameColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        // Perform on cell edit
        firstNameColumn.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Donor, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Donor, String> donorStringCellEditEvent) {
                // Get cell values
                Donor donor = donorStringCellEditEvent.getRowValue();
                // Save new value
                donor.setFirstName(donorStringCellEditEvent.getNewValue());
                try {
                    db.updateDonorData(donor.getId(), "firstName", donorStringCellEditEvent.getNewValue());
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        TableColumn<Donor, String> lastNameColumn = new TableColumn<Donor, String>("Last Name");
        lastNameColumn.setCellValueFactory(new PropertyValueFactory<Donor, String>("lastName"));
        lastNameColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        lastNameColumn.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Donor, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Donor, String> donorStringCellEditEvent) {
                // Get cell values
                Donor donor = donorStringCellEditEvent.getRowValue();
                // Save new value
                donor.setLastName(donorStringCellEditEvent.getNewValue());
                try {
                    db.updateDonorData(donor.getId(), "lastName", donorStringCellEditEvent.getNewValue());
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        TableColumn<Donor, String> phoneNumberColumn = new TableColumn<Donor, String>("Phone Number");
        phoneNumberColumn.setCellValueFactory(new PropertyValueFactory<Donor, String>("phoneNumber"));
        phoneNumberColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        phoneNumberColumn.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Donor, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Donor, String> donorStringCellEditEvent) {
                // Get cell values
                Donor donor = donorStringCellEditEvent.getRowValue();
                // Save new value
                donor.setPhoneNumber(donorStringCellEditEvent.getNewValue());
                try {
                    db.updateDonorData(donor.getId(), "phoneNumber", donorStringCellEditEvent.getNewValue());
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        TableColumn<Donor, String> emailColumn = new TableColumn<Donor, String>("Email");
        emailColumn.setCellValueFactory(new PropertyValueFactory<Donor, String>("email"));
        emailColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        emailColumn.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Donor, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Donor, String> donorStringCellEditEvent) {
                // Get cell values
                Donor donor = donorStringCellEditEvent.getRowValue();
                // Save new value
                donor.setEmail(donorStringCellEditEvent.getNewValue());
                try {
                    db.updateDonorData(donor.getId(), "email", donorStringCellEditEvent.getNewValue());
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        TableColumn<Donor, java.util.Date> birthDateColumn = new TableColumn<Donor, java.util.Date>("Birth Date");
        birthDateColumn.setCellValueFactory(new PropertyValueFactory<Donor, Date>("birthDate"));
        birthDateColumn.setCellFactory(TextFieldTableCell.forTableColumn(new DateStringConverter()));
        birthDateColumn.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Donor, Date>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Donor, Date> donorDateCellEditEvent) {
                // Get cell values
                Donor donor = donorDateCellEditEvent.getRowValue();
                // Save new value
                // Create a java.util.Date object
                Date utilDate = donorDateCellEditEvent.getNewValue();
                // Convert java.util.Date to java.sql.Date
                long timeInMillis = utilDate.getTime();
                java.sql.Date sqlDate = new java.sql.Date(timeInMillis);
                donor.setBirthDate(sqlDate);
                try {
                    db.updateDonorData(donor.getId(), "birthDate", sqlDate.toString());
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        TableColumn<Donor, String> bsnColumn = new TableColumn<Donor, String>("BSN");
        bsnColumn.setCellValueFactory(new PropertyValueFactory<Donor, String>("bsn"));
        bsnColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        bsnColumn.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Donor, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Donor, String> donorStringCellEditEvent) {
                // Get cell values
                Donor donor = donorStringCellEditEvent.getRowValue();
                // Save new value
                donor.setBsn(donorStringCellEditEvent.getNewValue());
                try {
                    db.updateDonorData(donor.getId(), "bsn", donorStringCellEditEvent.getNewValue());
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        // Add columns to table
        donorTableView.getColumns().addAll(
                idColumn, firstNameColumn, lastNameColumn,
                phoneNumberColumn, emailColumn, birthDateColumn, bsnColumn);

        // Get rid of empty column
        donorTableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        try{
            // Get data from Database
            ResultSet rs = db.getDonorsData();
            while (rs.next()){
                // Create new object for each donor
                Donor donor = new Donor(rs);
                // Insert into table
                donorTableView.getItems().add(donor);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        // Bottom part
        //  Bottom layouts
        VBox bot = new VBox(5);
        HBox botUp = new HBox(10);
        HBox botDown = new HBox(10);

        botUp.setPadding(new Insets(10, 0, 0 ,10));
        VBox.setMargin(botDown, new Insets(0, 0, 5, 10));
//        botDown.setAlignment(Pos.CENTER);

        // Inputs
        firstNameInput = new TextField();
        firstNameInput.setPromptText("First Name");
        lastNameInput = new TextField();
        lastNameInput.setPromptText("Last Name");
        phoneNumberInput = new TextField();
        phoneNumberInput.setPromptText("Phone Number");
        emailInput = new TextField();
        emailInput.setPromptText("Email");
        birthDateInput = new TextField();
        birthDateInput.setPromptText("Format: YYYY-MM-DD");
        bsnInput = new TextField();
        bsnInput.setPromptText("BSN");

        Button addButton = new Button("Add");
        Button delButton = new Button("Delete");

        addButton.setPrefWidth(100);
        delButton.setPrefWidth(100);

        addButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                // Swap date to working formats for table.
                // Get birth Date from textInput
                String birthDateString = birthDateInput.getText();
                // Swap it do sql date
                java.sql.Date birthDate = java.sql.Date.valueOf(birthDateString);
                // Next ID
                Integer nextID;
                // Try to get nextID from DB
                try {
                    nextID = db.getNextDonorID();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }

                // Create new object with inserted data
                Donor donor = new Donor(nextID, firstNameInput.getText(), lastNameInput.getText(),
                        phoneNumberInput.getText(), emailInput.getText(), birthDate, bsnInput.getText());

                try{
                    db.createDonor(firstNameInput.getText(), lastNameInput.getText(), phoneNumberInput.getText(),
                            emailInput.getText(), birthDateString, bsnInput.getText());
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }

                donorTableView.getItems().add(donor);
                // Clear text fields
                clearInputs();
            }
        });

        delButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                // Get cell values
                // Table row ID
                Integer selectedTableIndex = donorTableView.getSelectionModel().getSelectedIndex();
                // DB item ID
                Integer donorID = donorTableView.getSelectionModel().getSelectedItem().getId();

                deleteDonor(selectedTableIndex, donorID);
            }
        });

        // Add elements do layouts
        botUp.getChildren().addAll(firstNameInput,lastNameInput,phoneNumberInput,emailInput,birthDateInput,bsnInput);
        botDown.getChildren().addAll(addButton, delButton);

        bot.getChildren().addAll(botUp, botDown);

        // Add elements/panes to borderpane
        contentPane.setTop(top);
        contentPane.setCenter(donorTableView);
        contentPane.setBottom(bot);

        // Bottom Right image
        ImageView nurseImage = app.getNurseImage();

        donorsScreen.getChildren().addAll(contentPane, nurseImage);
        StackPane.setAlignment(nurseImage, Pos.BOTTOM_RIGHT);
    }

    public StackPane getDonorsScreen(){
        return donorsScreen;
    }

    public void deleteDonor(int selectedTableId, Integer donorID){
        if (selectedTableId >= 0){
            donorTableView.getItems().remove(selectedTableId);
            try {
                db.deleteDonor(donorID);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            donorTableView.getSelectionModel().clearSelection();
        }
    }

    private void clearInputs(){
        firstNameInput.clear();
        lastNameInput.clear();
        phoneNumberInput.clear();
        emailInput.clear();
        birthDateInput.clear();
        bsnInput.clear();
        firstNameInput.requestFocus();
    }
}
