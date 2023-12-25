package authentication;

import database.inserting.InsertingData;
import database.retrieving.RetrievingUser;
import helpers.DataValidation;
import model.User;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class Register {
    private String status;
    private InsertingData userInserter;
    private RetrievingUser userRetriever;

    public Register(Connection connection){
        userInserter = new InsertingData(connection);
        userRetriever = new RetrievingUser(connection);
    }
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public boolean registerUser(User user) throws SQLException {
        String st = DataValidation.userValidationTest(user);
        setStatus(st);
        if(getStatus().equals("Valid")){
            List<User> allUsers = userRetriever.findUserByUsername(user.getUsername());
            if(allUsers == null || allUsers.isEmpty()) {
                if(userInserter.insertUser(user)){
                    if(user.getUserType().equals("customer"))
                        userInserter.insertCustomer(user.getUsername(), 0);
                    else if(user.getUserType().equals("installer"))
                        userInserter.insertInstaller(user.getUsername(), 0, 0);
                    setStatus("User was registered successfully");
                    return true;
                }
            }
            else
                setStatus("Username is already taken");
        }
        return false;
    }
}