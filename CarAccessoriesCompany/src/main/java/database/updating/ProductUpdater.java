package database.updating;

import classes.Starter;
import helpers.DataValidation;
import helpers.Generator;
import model.Product;
import model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class ProductUpdater {
    private String status;
    private Connection connection;
    public ProductUpdater(Connection connection){
        this.connection = connection;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }

    public boolean updateProductTest(Product product, String condition){
        Starter.logger.info(condition);
        String st = DataValidation.productValidationTest(product);
        if(st.equals("Valid"))
            setStatus("Product was updated successfully");
        else
            setStatus(st);
        return false;
    }
    public boolean updateProductsAllFields(Product product, String condition){
        String st = DataValidation.productValidationTest(product);
        if(st.equals("Valid")){
            try {
                String query = "UPDATE products SET productName = ?, category = ?, price = ?, " +
                        "numberOfOrders = ?, image = ?, longDescription = ?, shortDescription = ?, availability = ? " + condition;
                PreparedStatement preparedStmt = connection.prepareStatement(query);
                preparedStmt = Generator.productToPS(preparedStmt, product);
                preparedStmt.execute();
                setStatus("Product was updated successfully");
                return true;
            } catch (Exception e) {
                setStatus("Couldn't update product");
                return false;
            }
        }
        else
            setStatus(st);
        return false;
    }
    public boolean updateProductName(String newProductName, String condition){
        try {
            String query = "UPDATE products SET productName = ? " + condition;
            PreparedStatement preparedStmt = connection.prepareStatement(query);
            preparedStmt.setString (1, newProductName);
            preparedStmt.execute();
            setStatus("Product name was updated successfully");
            return true;
        } catch (Exception e) {
            setStatus("Couldn't update product name");
            return false;
        }
    }
    public boolean updateProductCategory(String newCategory, String condition){
        try {
            String query = "UPDATE products SET category = ? " + condition;
            PreparedStatement preparedStmt = connection.prepareStatement(query);
            preparedStmt.setString (1, newCategory);
            preparedStmt.execute();
            setStatus("Product category was updated successfully");
            return true;
        } catch (Exception e) {
            setStatus("Couldn't update product category");
            return false;
        }
    }
    public boolean updateProductPrice(Double newPrice, String condition){
        try {
            String query = "UPDATE products SET price = ? " + condition;
            PreparedStatement preparedStmt = connection.prepareStatement(query);
            preparedStmt.setDouble (1, newPrice);
            preparedStmt.execute();
            setStatus("Product price was updated successfully");
            return true;
        } catch (Exception e) {
            setStatus("Couldn't update product price");
            return false;
        }
    }
    public boolean updateProductNumberOfOrders(String newNumberOfOrders, String condition){
        try {
            String query = "UPDATE products SET numberOfOrders = ? " + condition;
            PreparedStatement preparedStmt = connection.prepareStatement(query);
            preparedStmt.setString (1, newNumberOfOrders);
            preparedStmt.execute();
            setStatus("Product newNumberOfOrders was updated successfully");
            return true;
        } catch (Exception e) {
            setStatus("Couldn't update product newNumberOfOrders");
            return false;
        }
    }

    public boolean updateProductImage(String newImagePath, String condition){
        try {
            String query = "UPDATE products SET image = ? " + condition;
            PreparedStatement preparedStmt = connection.prepareStatement(query);
            preparedStmt.setString (1, newImagePath);
            preparedStmt.execute();
            setStatus("Product image was updated successfully");
            return true;
        } catch (Exception e) {
            setStatus("Couldn't update product image");
            return false;
        }
    }

    public boolean updateProductLongDescription(String newLongDescription, String condition){
        try {
            String query = "UPDATE products SET longDescription = ? " + condition;
            PreparedStatement preparedStmt = connection.prepareStatement(query);
            preparedStmt.setString (1, newLongDescription);
            preparedStmt.execute();
            setStatus("Product long description was updated newLongDescription");
            return true;
        } catch (Exception e) {
            setStatus("Couldn't update product long description");
            return false;
        }
    }

    public boolean updateProductShortDescription(String newShortDescription, String condition){
        try {
            String query = "UPDATE products SET shortDescription = ? " + condition;
            PreparedStatement preparedStmt = connection.prepareStatement(query);
            preparedStmt.setString (1, newShortDescription);
            preparedStmt.execute();
            setStatus("Product short description was updated newLongDescription");
            return true;
        } catch (Exception e) {
            setStatus("Couldn't update product short description");
            return false;
        }
    }

    public boolean updateProductAvailability(int newAvailability, String condition){
        try {
            String query = "UPDATE products SET availability = ? " + condition;
            PreparedStatement preparedStmt = connection.prepareStatement(query);
            preparedStmt.setInt (1, newAvailability);
            preparedStmt.execute();
            setStatus("Product availability was updated newLongDescription");
            return true;
        } catch (Exception e) {
            setStatus("Couldn't update product availability");
            return false;
        }
    }

}
