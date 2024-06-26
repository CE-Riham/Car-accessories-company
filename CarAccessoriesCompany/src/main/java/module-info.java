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
    exports controllers.admin_controllers.orders;
    opens controllers.admin_controllers.orders to javafx.fxml;
    exports controllers.autintication_controllers;
    opens controllers.autintication_controllers to javafx.fxml;
    exports controllers.customer_controllers;
    opens controllers.customer_controllers to javafx.fxml;
    exports controllers.admin_controllers.installer;
    opens controllers.admin_controllers.installer to javafx.fxml;
}