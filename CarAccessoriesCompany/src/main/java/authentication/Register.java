package authentication;

import Classes.Starter;
import Classes.UserSession;
import database.InsertingData;
import database.RetrievingData;
import helpers.DataValidation;
import model.User;

import java.sql.Connection;
import java.util.List;

public class Register {
    private String status;
    private InsertingData userInserter;
    private RetrievingData userRetriever;

    public Register(Connection connection){
        userInserter = new InsertingData(connection);
        userRetriever = new RetrievingData(connection);
    }
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void registerUserTest(User user){
        String st = DataValidation.userValidationTest(user);
        if(st.equals("Valid")){
            List<User> allUsers = userRetriever.selectUsers("username = '" + user.getUsername() + "'");
            if(allUsers == null || allUsers.size() == 0 )
                    setStatus("User was registered successfully");
            else
                setStatus("Username is already taken");
        }
        else
            setStatus(st);
    }
    public boolean registerUser(User user){
        registerUserTest(user);
        if(getStatus().equals("User was registered successfully")){
            if(userInserter.insertUser(user)) {
                setStatus("User was registered successfully");
                return true;
            }
            setStatus("Couldn't register user");

        }
        return false;
    }
}
