package controllers.admin_controllers.installer;

import controllers.admin_controllers.AdminNavBarActions;
import helpers.common.InstallersController;
import helpers.comparators.InstallerComparator;
import helpers.filters.InstallerFilter;
import helpers.stage_helpers.AdminStageHelper;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import model.installation.Installer;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class AdminInstallersController extends AdminNavBarActions implements Initializable {
    // -------------------------------------------------------------------------------------------------------------------- //
    // -------------------------------------------- section1: Class attributes -------------------------------------------- //
    // -------------------------------------------------------------------------------------------------------------------- //
    private List<Installer> allInstallers;
    private List<Installer> toViewInstallers;
    private int pageNumber = 0;
    @FXML
    private Button installersButton;
    @FXML
    private Button prevButton;
    @FXML
    private Button nextButton;
    @FXML
    private HBox row1;
    @FXML
    private HBox row2;
    @FXML
    private ComboBox<String> searchByCombo;
    @FXML
    private TextField searchTextField;
    @FXML
    private ComboBox<String> sortByCombo;
    @FXML
    private ComboBox<String> sortTypeCombo;


    // -------------------------------------------------------------------------------------------------------------------- //
    // ------------------------------------------ section2: Page button actions ------------------------------------------- //
    // -------------------------------------------------------------------------------------------------------------------- //
    @FXML
    void filterInstallers(Event event) {
        handleFilterInstallers();
    }

    @FXML
    void onAddInstallerClick(ActionEvent event) {
        //TODO
        AdminStageHelper.showAddProducts(event);
    }

    @FXML
    void onPrevButtonClick(ActionEvent event) {
        pageNumber--;
        InstallersController.fillFilteredData(toViewInstallers, row1, row2, pageNumber);
        InstallersController.disableInstallersButtons(toViewInstallers, prevButton, nextButton, pageNumber);
    }

    @FXML
    void onNextButtonClick(ActionEvent event) {
        pageNumber++;
        InstallersController.fillFilteredData(toViewInstallers, row1, row2, pageNumber);
        InstallersController.disableInstallersButtons(toViewInstallers, prevButton, nextButton, pageNumber);
    }

    // -------------------------------------------------------------------------------------------------------------------- //
    // ------------------------------------------ section3: Initialising actions ------------------------------------------ //
    // -------------------------------------------------------------------------------------------------------------------- //
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        activateMenuButton(installersButton);
        allInstallers = InstallersController.getAllInstallersFromDB();
        toViewInstallers = new ArrayList<>(allInstallers);
        InstallersController.fillAllComboBoxes(searchByCombo, sortTypeCombo, sortByCombo);
        InstallersController.fillFilteredData(toViewInstallers, row1, row2, pageNumber);
        InstallersController.disableInstallersButtons(toViewInstallers, prevButton, nextButton, pageNumber);
    }


    // -------------------------------------------------------------------------------------------------------------------- //
    // --------------------------------------------- section4: helper methods --------------------------------------------- //
    // -------------------------------------------------------------------------------------------------------------------- //
    private void sortInstallers() {
        boolean sortingType = sortTypeCombo.getValue().equals("ASC");
        String sortBy = sortByCombo.getValue();
        toViewInstallers = InstallerComparator.sortInstallers(sortBy, sortingType, new ArrayList<>(toViewInstallers));
    }

    private void filterInstallers() {
        toViewInstallers = InstallerFilter.filterInstallersBy(searchByCombo.getValue(), searchTextField.getText(), allInstallers);
    }

    // -------------------------------------------------------------------------------------------------------------------- //
    // ------------------------------------------------ section7: handlers ------------------------------------------------ //
    // -------------------------------------------------------------------------------------------------------------------- //
    private void handleFilterInstallers() {
        filterInstallers();
        sortInstallers();
        InstallersController.fillFilteredData(toViewInstallers, row1, row2, pageNumber);
    }
}