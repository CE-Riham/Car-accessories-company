package helpers.common;

import cards.UserCard;
import classes.DBConnector;
import classes.Starter;
import database.retrieving.RetrievingInstallers;
import javafx.collections.FXCollections;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.HBox;
import model.Installer;

import java.util.List;

public class InstallersController {
    private InstallersController() {
        Starter.logger.info("Hi from Installers controller");
    }

    // -------------------------------------------------------------------------------------------------------------------- //
    // -------------------------------------------- section1: Fill combo Boxes -------------------------------------------- //
    // -------------------------------------------------------------------------------------------------------------------- //
    private static void fillSearchByCombo(ComboBox<String> searchByCombo) {
        List<String> allFields = List.of("installerUsername", "price less than or equal", "works on day", "works at hour");
        searchByCombo.setItems(FXCollections.observableArrayList(allFields));
        searchByCombo.setValue(allFields.get(0));
    }

    private static void fillSortTypeCombo(ComboBox<String> sortTypeCombo) {
        List<String> allFields = List.of("ASC", "DSC");
        sortTypeCombo.setItems(FXCollections.observableArrayList(allFields));
        sortTypeCombo.setValue(allFields.get(0));
    }

    private static void fillSortByCombo(ComboBox<String> sortByCombo) {
        List<String> allFields = List.of("price per hour", "start hour", "end hour");
        sortByCombo.setItems(FXCollections.observableArrayList(allFields));
        sortByCombo.setValue(allFields.get(0));
    }

    public static void fillAllComboBoxes(ComboBox<String> searchByCombo, ComboBox<String> sortTypeCombo, ComboBox<String> sortByCombo) {
        InstallersController.fillSearchByCombo(searchByCombo);
        InstallersController.fillSortTypeCombo(sortTypeCombo);
        InstallersController.fillSortByCombo(sortByCombo);
    }

    // -------------------------------------------------------------------------------------------------------------------- //
    // ---------------------------------------- section2: Display toViewInstallers ---------------------------------------- //
    // -------------------------------------------------------------------------------------------------------------------- //
    public static void fillFilteredData(List<Installer> toViewInstallers, HBox row1, HBox row2, int pageNumber) {
        fillRow(toViewInstallers, row1, pageNumber * 12);
        fillRow(toViewInstallers, row2, pageNumber * 12 + 6);
    }

    private static void fillRow(List<Installer> toViewInstallers, HBox row, int index) {
        row.getChildren().clear();
        for (int i = index; i < (index + 6) && i < toViewInstallers.size(); i++) {
            row.getChildren().add(new UserCard(toViewInstallers.get(i)).getCard());
        }
    }

    private static void disableButton(Button button, boolean flag) {
        button.setDisable(flag);
    }

    public static void disableInstallersButtons(List<Installer> toViewInstallers, Button prevButton, Button nextButton, int pageNumber) {
        disableButton(prevButton, (pageNumber == 0));
        disableButton(nextButton, ((pageNumber * 12 + 12) >= toViewInstallers.size()));
    }

    // -------------------------------------------------------------------------------------------------------------------- //
    // --------------------------------------------- section4: helper methods --------------------------------------------- //
    // -------------------------------------------------------------------------------------------------------------------- //
    public static List<Installer> getAllInstallersFromDB() {
        RetrievingInstallers installersRetriever = new RetrievingInstallers(DBConnector.getConnector().getCon());
        return installersRetriever.selectAllInstallers();
    }

}
