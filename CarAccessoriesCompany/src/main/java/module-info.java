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
    exports controllers.admin_controllers;
    opens controllers.admin_controllers to javafx.fxml;
    exports helpers.stage_helpers;
    opens helpers.stage_helpers to javafx.fxml;
    exports controllers.admin_controllers.products;
    opens controllers.admin_controllers.products to javafx.fxml;
    exports controllers.admin_controllers.customers;
    opens controllers.admin_controllers.customers to javafx.fxml;
    exports controllers.admin_controllers.admins;
    opens controllers.admin_controllers.admins to javafx.fxml;
    exports controllers.admin_controllers.categories;
    opens controllers.admin_controllers.categories to javafx.fxml;
    exports controllers.admin_controllers.users;
    opens controllers.admin_controllers.users to javafx.fxml;
}