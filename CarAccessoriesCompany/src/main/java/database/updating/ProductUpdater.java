package database.updating;

import helpers.DataValidation;
import helpers.Generator;
import model.Product;
import java.util.Set;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException; 

public class ProductUpdater {
    private String status;
    private Connection connection;

    public ProductUpdater(Connection connection) {
        this.connection = connection;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


public boolean updateProductsAllFields(Product product, String condition) {
    String st = DataValidation.productValidationTest(product);
    if ("Valid".equals(st)) {
        try {
            // Use StringBuilder to construct the SQL query
            StringBuilder queryBuilder = new StringBuilder();
            queryBuilder.append("UPDATE products SET productName = ?, category = ?, price = ?, ")
                    .append("numberOfOrders = ?, image = ?, longDescription = ?, ")
                    .append("shortDescription = ?, availability = ?").append(" ").append(condition);

            // Use try-with-resources to manage the PreparedStatement
            try (PreparedStatement preparedStmt = connection.prepareStatement(queryBuilder.toString())) {
                // Set parameters for the base query
                preparedStmt.setString(1, product.getProductName());
                preparedStmt.setString(2, product.getProductCategory());
                preparedStmt.setDouble(3, product.getProductPrice());
                preparedStmt.setInt(4, product.getNumberOfOrders());
                preparedStmt.setString(5, product.getImagePath());
                preparedStmt.setString(6, product.getLongDescription());
                preparedStmt.setString(7, product.getShortDescription());
                preparedStmt.setInt(8, product.getAvailableAmount());

                // Execute the update
                preparedStmt.executeUpdate();

                setStatus("Product was updated successfully");
                return true;
            }
        } catch (SQLException e) {
            setStatus("Couldn't update product");
            return false;
        }
    } else {
        setStatus(st);
        return false;
    }
}




 public boolean updateProductSingleStringField(String fieldName, String newValue, String condition) {
        String query = "UPDATE products SET ? = ? ?";
        try (PreparedStatement preparedStmt = connection.prepareStatement(query)) {
            preparedStmt.setString(1, fieldName);
            preparedStmt.setString(2, newValue);
            preparedStmt.setString(3, condition);
            preparedStmt.executeUpdate();
            setStatus("Product " + fieldName + " was updated successfully");
            return true;
        } catch (SQLException e) {
            setStatus("Couldn't update product " + fieldName);
            return false;
        }
    }

    public boolean updateProductSingleIntegerField(String fieldName, int newValue, String condition) {
        String query = "UPDATE products SET ? = ? ?";
        try (PreparedStatement preparedStmt = connection.prepareStatement(query)) {
            preparedStmt.setString(1, fieldName);
            preparedStmt.setInt(2, newValue);
            preparedStmt.setString(3, condition);
            preparedStmt.executeUpdate();
            setStatus("Product " + fieldName + " was updated successfully");
            return true;
        } catch (SQLException e) {
            setStatus("Couldn't update product " + fieldName);
            return false;
        }
    }


   
    public boolean updateProductName(String newProductName, String condition) {
        return updateProductSingleStringField("productName", newProductName, condition);
    }

    public boolean updateProductCategory(String newCategory, String condition) {
        return updateProductSingleStringField("category", newCategory, condition);
    }

  public boolean updateProductPrice(Double newPrice, String condition) {
    StringBuilder queryBuilder = new StringBuilder("UPDATE products SET price = ?");
    queryBuilder.append(" ").append(condition);

    try (PreparedStatement preparedStmt = connection.prepareStatement(queryBuilder.toString())) {
        preparedStmt.setDouble(1, newPrice);
        preparedStmt.executeUpdate();
        setStatus("Product price was updated successfully");
        return true;
    } catch (SQLException e) {
        setStatus("Couldn't update product price");
        return false;
    }
}


    public boolean updateProductNumberOfOrders(int newNumberOfOrders, String condition) {
        return updateProductSingleIntegerField("numberOfOrders", newNumberOfOrders, condition);
    }

    public boolean updateProductImage(String newImagePath, String condition) {
        return updateProductSingleStringField("image", newImagePath, condition);
    }

    public boolean updateProductLongDescription(String newLongDescription, String condition) {
        return updateProductSingleStringField("longDescription", newLongDescription, condition);
    }

    public boolean updateProductShortDescription(String newShortDescription, String condition) {
        return updateProductSingleStringField("shortDescription", newShortDescription, condition);
    }

    public boolean updateProductAvailability(int newAvailability, String condition) {
        return updateProductSingleIntegerField("availability", newAvailability, condition);
    }

}
