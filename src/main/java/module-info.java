module com.example.blooddonation {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.blooddonation to javafx.fxml;
    exports com.example.blooddonation;
}