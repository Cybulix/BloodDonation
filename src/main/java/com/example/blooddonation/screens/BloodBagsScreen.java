package com.example.blooddonation.screens;

import com.example.blooddonation.Application;
import com.example.blooddonation.Database;
import com.example.blooddonation.models.BloodBag;
import com.example.blooddonation.models.Donor;
import com.example.blooddonation.models.Hospital;
import com.example.blooddonation.models.Worker;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import javafx.util.converter.IntegerStringConverter;
import org.w3c.dom.Text;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class BloodBagsScreen {
    private StackPane bloodScreen;
    private BorderPane root;
    private Database db;
    private TableView<BloodBag> bloodTableView;

    private TextField bloodTypeInput;
    private TextField amountInput;
    private ComboBox donorSelection;
    private ComboBox hospitalSelection;
    private String currentWorkerName;
    private Integer selectedHospitalID;
    private String selectedHospitalName;
    private Integer selectedDonorID;


    public BloodBagsScreen(Application app, BorderPane rootBorderPane){
        // Get borderPane from Application class
        root = rootBorderPane;
        // StackPane to place image on top of content
        bloodScreen = new StackPane();
        // Layout for Content
        BorderPane contentPane = new BorderPane();

        // Donors List
        List<Donor> donors = new ArrayList<>();
        // Workers List
        List<Worker> workers = new ArrayList<>();
        // Hospitals List
        List<Hospital> hospitals = new ArrayList<>();

        // Connect to DB
        try{
            db = new Database();

            // Get workers, donors & hospitals data
            ResultSet rsWorkers = db.getWorkersData();
            while (rsWorkers.next()){
                // Create new object
                Worker worker = new Worker(rsWorkers);
                // Insert worker object into list
                workers.add(worker);
            }
            ResultSet rsDonors = db.getDonorsData();
            while (rsDonors.next()){
                Donor donor = new Donor(rsDonors);
                donors.add(donor);
            }

            ResultSet rsHospitals = db.getHospitalData();
            while (rsHospitals.next()){
                Hospital hospital = new Hospital(rsHospitals);
                hospitals.add(hospital);
            }

            // Example for later use
            for(Worker worker : workers){
                if(worker.getId() == app.getSelectedWorkerId()){
                    currentWorkerName = worker.getFirstName() + " " + worker.getLastName();
                }
            }

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
        bloodTableView = new TableView<BloodBag>();
        bloodTableView.setEditable(true);
        // Table columns
        TableColumn<BloodBag, Integer> idColumn = new TableColumn<BloodBag, Integer>("ID");
        idColumn.setCellValueFactory(new PropertyValueFactory<BloodBag, Integer>("id"));

        TableColumn<BloodBag, String> bloodTypeColumn = new TableColumn<BloodBag, String>("Blood Type");
        bloodTypeColumn.setCellValueFactory(new PropertyValueFactory<BloodBag, String>("bloodType"));
        bloodTypeColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        // Perform on cell edit
        bloodTypeColumn.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<BloodBag, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<BloodBag, String> bloodBagStringCellEditEvent) {
                // Get cell values
                BloodBag bloodBag = bloodBagStringCellEditEvent.getRowValue();
                // Save new value
                bloodBag.setBloodType(bloodBagStringCellEditEvent.getNewValue());
                // also in DB
                try {
                    db.updateBloodBagData(bloodBag.getId(), "bloodType", bloodBagStringCellEditEvent.getNewValue());
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        TableColumn<BloodBag, Integer> amountColumn = new TableColumn<BloodBag, Integer>("Amount");
        amountColumn.setCellValueFactory(new PropertyValueFactory<BloodBag, Integer>("amount"));
        amountColumn.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));

        amountColumn.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<BloodBag, Integer>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<BloodBag, Integer> bloodBagIntegerCellEditEvent) {
                // Get cell values
                BloodBag bloodBag = bloodBagIntegerCellEditEvent.getRowValue();
                // Save new value
                bloodBag.setBloodType(String.valueOf(bloodBagIntegerCellEditEvent.getNewValue()));
                // also in DB
                try {
                    db.updateBloodBagData(bloodBag.getId(), "amount", String.valueOf(bloodBagIntegerCellEditEvent.getNewValue()));
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        TableColumn<BloodBag, Date> dateColumn = new TableColumn<BloodBag, Date>("Date");
        dateColumn.setCellValueFactory(new PropertyValueFactory<BloodBag, Date>("date"));

        TableColumn<BloodBag, Integer> donorIDColumn = new TableColumn<BloodBag, Integer>("Donor ID");
        donorIDColumn.setCellValueFactory(new PropertyValueFactory<BloodBag, Integer>("donorID"));

        TableColumn<BloodBag, String> workerNameColumn = new TableColumn<BloodBag, String>("Worker Name");
        workerNameColumn.setCellValueFactory(new PropertyValueFactory<BloodBag, String>("workerName"));

        TableColumn<BloodBag, String> hospitalNameColumn = new TableColumn<BloodBag, String>("Hospital Name");
        hospitalNameColumn.setCellValueFactory(new PropertyValueFactory<BloodBag, String>("hospitalName"));

        // Add columns to table
        bloodTableView.getColumns().addAll(idColumn, bloodTypeColumn, amountColumn, dateColumn,
                donorIDColumn, workerNameColumn, hospitalNameColumn);

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

        // Bottom part
        VBox bot = new VBox(5);
        HBox botUp = new HBox(10);
        HBox botDown = new HBox(10);

        botUp.setPadding(new Insets(10, 0, 0 ,10));
        VBox.setMargin(botDown, new Insets(0, 0, 5, 10));

        // Inputs
        bloodTypeInput = new TextField();
        bloodTypeInput.setPromptText("Blood Type");
        amountInput = new TextField();
        amountInput.setPromptText("Amount Blood");
        // Selections
        donorSelection = new ComboBox<>();
        donorSelection.setPromptText("Select donor");
        ObservableList<Donor> donorsList = FXCollections.observableArrayList(donors);
        // TODO to string override
        donorSelection.setItems(donorsList);

        hospitalSelection = new ComboBox<>();
        hospitalSelection.setPromptText("Select current hospital");
        ObservableList<Hospital> hospitalsList = FXCollections.observableArrayList(hospitals);
        // TODO to string override
        hospitalSelection.setItems(hospitalsList);

        donorSelection.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Donor selectedDonor = (Donor) donorSelection.getSelectionModel().getSelectedItem();
                if (selectedDonor != null){
                    selectedDonorID = selectedDonor.getId();
                }
            }
        });
        hospitalSelection.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Hospital selectedHospital = (Hospital) hospitalSelection.getSelectionModel().getSelectedItem();

                if (selectedHospital != null){
                    selectedHospitalID = selectedHospital.getId();
                    selectedHospitalName = selectedHospital.getName();
                }
            }
        });

        Button addButton = new Button("Add");
        Button delButton = new Button("Delete");
        addButton.setPrefWidth(100);
        delButton.setPrefWidth(100);

        addButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                // Get current Date
                LocalDate currentDate = LocalDate.now();
                Date currentSqlDate = Date.valueOf(currentDate);
                // Next ID
                Integer nextID;
                // Try to get nextID from DB
                try {
                    nextID = db.getNextBloodBagID();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }

                // Create new object with inserted data
                BloodBag bloodBag = new BloodBag(nextID, bloodTypeInput.getText(),
                        Integer.valueOf(amountInput.getText()), currentSqlDate, selectedDonorID, currentWorkerName , selectedHospitalName);

                bloodTableView.getItems().add(bloodBag);
            }
        });

        delButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                // Get cell values
                // Table row ID
                Integer selectedTableIndex = bloodTableView.getSelectionModel().getSelectedIndex();
                // DB item ID
                Integer bloodBagID = bloodTableView.getSelectionModel().getSelectedItem().getId();

                deleteDonor(selectedTableIndex, bloodBagID);
            }
        });

        // Add elements do layouts
        botUp.getChildren().addAll(bloodTypeInput, amountInput, donorSelection, hospitalSelection);
        botDown.getChildren().addAll(addButton, delButton);

        bot.getChildren().addAll(botUp, botDown);

        // Add elements/panes to borderpane
        contentPane.setTop(top);
        contentPane.setCenter(bloodTableView);
        contentPane.setBottom(bot);

        // Bottom Right image
        ImageView nurseImage = app.getNurseImage();

        bloodScreen.getChildren().addAll(contentPane, nurseImage);
        StackPane.setAlignment(nurseImage, Pos.BOTTOM_RIGHT);

    }

    public StackPane getBloodScreen(){ return bloodScreen; }

    public void deleteDonor(int selectedTableId, Integer donorID){
        if (selectedTableId >= 0){
            bloodTableView.getItems().remove(selectedTableId);
            try {
                db.deleteBloodBag(donorID);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            bloodTableView.getSelectionModel().clearSelection();
        }
    }
}
