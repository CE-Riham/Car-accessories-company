package helpers.stage_helpers;

import classes.Starter;
import helpers.common.StageHelper;
import javafx.event.ActionEvent;

public class CustomerStageHelper extends StageHelper {
    public static void showAdminProducts(ActionEvent event) {
        try {
            showPage(event, "/FXMLFiles/user_pages/userHomePage.fxml", 1615, 965);
        } catch (Exception e) {
            Starter.logger.warning("Can't open user home page");
        }
    }
}
