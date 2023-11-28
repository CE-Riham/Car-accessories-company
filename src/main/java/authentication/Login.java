package authentication;

import Classes.Starter;
import Classes.UserSession;
import database.RetrievingData;
import javafx.scene.Scene;
import model.User;

import java.sql.*;
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
                int userId = -1;
                String sql = "SELECT id FROM users WHERE username = ?";
                try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3307/mysql", "root", "Vqo@954719");
                     PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

                    preparedStatement.setString(1, username);

                    try (ResultSet resultSet = preparedStatement.executeQuery()) {
                        if (resultSet.next()) {
                            userId = resultSet.getInt("id");
                        }
                    }
                } catch (SQLException e) {
                    e.printStackTrace(); // Handle the exception appropriately
                }
                Starter.userSession.setSessionId(String.valueOf(userId));

                return true;
            }
        }
        setStatus("Invalid username or password");
        return false;
    }

}
