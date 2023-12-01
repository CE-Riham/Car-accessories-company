package database;

import helpers.DataValidation;
import helpers.Generator;
import model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;

public class UpdatingData {
    private String status;
    private Connection connection;
    public UpdatingData(Connection connection){
        this.connection = connection;
    }
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public boolean updateUser(User user, String condition){
        String st = DataValidation.userValidationTest(user);
        if(st.equals("Valid")){
            try {
                String query = "UPDATE users SET firstName = ?, lastName = ?, username = ?, " +
                        "phone = ?, email = ?, userPassword = ?, image = ?, userType = ? " + condition;
                PreparedStatement preparedStmt = connection.prepareStatement(query);
                preparedStmt = Generator.userToPS(preparedStmt, user);
                preparedStmt.execute();
                setStatus("User was updated successfully");
                return true;
            } catch (Exception e) {
                setStatus("Couldn't update user");
                return false;
            }
        }
        else
            setStatus(st);
        return false;
    }
}