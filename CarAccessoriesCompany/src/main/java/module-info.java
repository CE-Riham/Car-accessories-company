module com.example.caraccessoriescompany {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires com.oracle.database.jdbc;
    requires java.sql;
    requires AnimateFX;
    requires java.mail;


    opens controllers to javafx.fxml;
    exports controllers;
    exports classes;
    opens classes to javafx.fxml;
    exports helpers;
    opens helpers to javafx.fxml;
}