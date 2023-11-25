module com.example.caraccessoriescompany {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires com.oracle.database.jdbc;
    requires java.sql;


    opens Controllers to javafx.fxml;
    exports Controllers;
    exports Classes;
    opens Classes to javafx.fxml;
    exports helpers;
    opens helpers to javafx.fxml;
}