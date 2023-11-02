module com.example.caraccessoriescompany {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens company to javafx.fxml;
    exports company;
}