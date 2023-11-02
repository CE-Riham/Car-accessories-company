module com.example.caraccessoriescompany {
    requires javafx.controls;
    requires javafx.fxml;


    opens company to javafx.fxml;
    exports company;
}