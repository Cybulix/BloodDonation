module com.example.blooddonation {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.blooddonation to javafx.fxml;
    exports com.example.blooddonation;
}