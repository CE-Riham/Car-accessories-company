package database.updating;

import helpers.DataValidation;
import helpers.Generator;
import model.products.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class ProductUpdater {
    private String status;
    private Connection connection;
    private Map<String, String> queries = new HashMap<>();
    private String[] allFields;

    public ProductUpdater(Connection connection) {
        this.connection = connection;
        String tmp = "UPDATE products SET ";
        allFields = new String[]{"productName", "category", "price", "numberOfOrders",
                "image", "longDescription", "shortDescription", "availability"};
        for (String field : allFields) {
            queries.put(field, tmp + field + " = ? ");
        }
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
                StringBuilder queryBuilder = new StringBuilder();
                queryBuilder.append("UPDATE products SET productName = ?, category = ?, price = ?, ")
                        .append("numberOfOrders = ?, image = ?, longDescription = ?, ")
                        .append("shortDescription = ?, availability = ?").append(" ").append(condition);

                try (PreparedStatement preparedStmt = Generator.productToPS(connection.prepareStatement(queryBuilder.toString()), product)) {
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
        if (!queries.containsKey(fieldName)) return false;
        String query = queries.get(fieldName);
        StringBuilder queryBuilder = new StringBuilder();
        queryBuilder.append(query).append(condition);
        try (PreparedStatement preparedStmt = connection.prepareStatement(queryBuilder.toString())) {
            preparedStmt.setString(1, newValue);
            preparedStmt.executeUpdate();
            setStatus("Product " + fieldName + " was updated successfully");
            return true;
        } catch (SQLException e) {
            setStatus("Couldn't update product " + fieldName);
            return false;
        }
    }

    public boolean updateProductSingleIntegerField(String fieldName, int newValue, String condition) {
        if (!queries.containsKey(fieldName)) return false;
        String query = queries.get(fieldName);
        StringBuilder queryBuilder = new StringBuilder();
        queryBuilder.append(query).append(condition);
        try (PreparedStatement preparedStmt = connection.prepareStatement(queryBuilder.toString())) {
            preparedStmt.setInt(1, newValue);
            preparedStmt.executeUpdate();
            setStatus("Product " + fieldName + " was updated successfully");
            return true;
        } catch (SQLException e) {
            setStatus("Couldn't update product " + fieldName);
            return false;
        }
    }

    public boolean updateProductName(String newProductName, String condition) {
        return updateProductSingleStringField(allFields[0], newProductName, condition);
    }

    public boolean updateProductCategory(String newCategory, String condition) {
        return updateProductSingleStringField(allFields[1], newCategory, condition);
    }


    public boolean updateProductNumberOfOrders(int newNumberOfOrders, String condition) {
        return updateProductSingleIntegerField(allFields[3], newNumberOfOrders, condition);
    }

    public boolean updateProductImage(String newImagePath, String condition) {
        return updateProductSingleStringField(allFields[4], newImagePath, condition);
    }

    public boolean updateProductLongDescription(String newLongDescription, String condition) {
        return updateProductSingleStringField(allFields[5], newLongDescription, condition);
    }

    public boolean updateProductShortDescription(String newShortDescription, String condition) {
        return updateProductSingleStringField(allFields[6], newShortDescription, condition);
    }

    public boolean updateProductAvailability(int newAvailability, String condition) {
        return updateProductSingleIntegerField(allFields[7], newAvailability, condition);
    }

}
