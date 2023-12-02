package authentication;

import classes.UserSession;
import database.retrieving.RetrievingUser;
import model.User;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class Login {
    private String status;
    private RetrievingUser usersRetriever;
    public Login(Connection connection) {
        usersRetriever = new RetrievingUser(connection);
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public boolean loginUser(String username, String password) throws SQLException {
        List<User> allUsers = usersRetriever.findUserByUsername(username.toLowerCase());
        if(allUsers != null && !allUsers.isEmpty() ) {
            User tmpUser = allUsers.get(0);
            if (tmpUser.getPassword().equals(password)) {
                setStatus("Valid username and password");
                UserSession.setCurrentUser(tmpUser);
                UserSession.setSessionId(UserSessionManager.createSession(username));
                return true;
            }
        }
        setStatus("Invalid username or password");
        return false;
    }
}
