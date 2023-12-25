package database.retrieving;

import classes.Starter;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class RetrievingCategories {

    private Connection con;
    private String status;

    public RetrievingCategories(Connection con) {
        this.con = con;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<String> selectCategoriesWithCondition(String condition) {
        List<String> categories = new ArrayList<>();
        Statement st = null;
        try {
            String query = "SELECT * FROM categories " + condition;
            st = con.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs != null && rs.next())
                categories.add(rs.getString("category"));
            setStatus("Retrieving categories successfully");
            return categories;
        } catch (Exception e) {
            setStatus("Error while retrieving categories from database");
            return new ArrayList<>();
        } finally {
            try {
                if (st != null) st.close();
            } catch (Exception e) {
                Starter.logger.warning("Can't close statement");
            }
        }
    }

    public List<String> selectAllCategories() {
        return selectCategoriesWithCondition(";");
    }

    public List<String> selectACategory(String categoryName) {
        return selectCategoriesWithCondition("where CAST(category AS BINARY) category = '" + categoryName + "';");
    }
}