module com.example.newproj {
    requires javafx.controls;
    requires javafx.fxml;
    requires io.cucumber.junit;
    requires junit;
    requires com.oracle.database.jdbc;
    requires java.sql;


    opens com.example.newproj to javafx.fxml;
    exports com.example.newproj;
}