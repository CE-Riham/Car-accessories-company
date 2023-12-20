package database.updating;

import classes.DBConnector;
import database.retrieving.RetrievingCategories;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class CategoryUpdater {
    private String status;
    private Connection connection;
    public CategoryUpdater(Connection connection){
        this.connection = connection;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }

    public boolean updateCategoryTest(String category){
        RetrievingCategories retrievingCategories = new RetrievingCategories(DBConnector.getConnector().getCon());
        if(retrievingCategories.selectACategory(category).size() != 0){
            setStatus("The category is already existed.");
            return false;
        }
        return true;
    }
    public boolean updateCategory(String newCategory, String oldCategory){
        try {
            //make sure that it's unique
            if(!updateCategoryTest(newCategory))
                return false;
            String query = "UPDATE categories SET category = ? where CAST(category AS BINARY) = ?";
            PreparedStatement preparedStmt = connection.prepareStatement(query);
            preparedStmt.setString (1, newCategory);
            preparedStmt.setString (2, oldCategory);
            preparedStmt.execute();
            setStatus("Category was updated successfully");
            return true;
        } catch (Exception e) {
            setStatus("Couldn't update category");
            return false;
        }
    }

}
