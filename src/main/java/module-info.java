module com.example.blooddonation {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.blooddonation to javafx.fxml;
    opens com.example.blooddonation.models to javafx.base;
    exports com.example.blooddonation;
}