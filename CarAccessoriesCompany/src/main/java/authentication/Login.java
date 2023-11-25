package authentication;

import Classes.Starter;
import Classes.UserSession;
import database.RetrievingData;
import model.User;

import java.sql.Connection;
import java.util.List;

public class Login {
    private String status;
    private RetrievingData usersRetriever;
    public Login(Connection connection) {
        usersRetriever = new RetrievingData(connection);
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public boolean loginUser(String username, String password){
        username = username.toLowerCase();

        List<User> allUsers = usersRetriever.selectUsers("username = '" + username + "'");
        if(allUsers != null && allUsers.size() != 0 ) {
            User tmpUser = allUsers.get(0);
            if (tmpUser.getPassword().equals(password)) {
                setStatus("Valid username and password");
                Starter.userSession = new UserSession(tmpUser);
                Starter.userSession.setSessionId(Starter.sessionManager.createSession(username));
                return true;
            }
        }
        setStatus("Invalid username or password");
        return false;
    }


}
