package database.deleting;

import classes.DBConnector;
import classes.Starter;
import database.updating.ProductUpdater;
import helpers.Alerts;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class CategoryDeleter {
    private String status;
    private Connection connection;
    public CategoryDeleter(Connection connection){
        this.connection = connection;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }

    public boolean deleteCategory(String category){
        if(category.equals("OTHERS")){
            setStatus("Couldn't delete category");
            Alerts.errorAlert("Deleting category", null, "You can't delete \"OTHERS\" category!");
            return false;
        }
        ProductUpdater productUpdater = new ProductUpdater(DBConnector.getConnector().getCon());
        boolean flag = productUpdater.updateProductCategory("OTHERS", "where category = '" + category + "';");
        if(!flag || !deleteCategoryFromTable(category)){
            setStatus("Couldn't delete category");
            Alerts.errorAlert("Deleting category", null, "Error while deleting category");
            return false;
        }
        setStatus("Category was deleted successfully");
        return true;
    }

    private boolean deleteCategoryFromTable(String category){
        PreparedStatement preparedStmt = null;
        try {
            String query = "delete from categories where category = '" + category+ "';";
            preparedStmt = connection.prepareStatement(query);
            preparedStmt.execute();
            return true;
        } catch (Exception e) {
            return false;
        }finally{
            if(preparedStmt!=null){
                try{
                    preparedStmt.close();
                }catch (Exception e){
                    Starter.logger.warning("Error while closing the statement.");
                }
            }
        }
    }
}
