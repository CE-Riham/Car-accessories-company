package authentication;

import Classes.Starter;
import Classes.UserSession;
import database.RetrievingData;
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
            System.out.println(password);
            if (tmpUser.getPassword().equals(password)) {
                System.out.println("YES111111111111111");
                setStatus("Valid username and password");
                Starter.userSession = new UserSession(tmpUser);
                System.out.println("YES22222222222222");
                int userId = -1;
                String sql = "SELECT id FROM users_new WHERE username = ?";
                System.out.println("YES");
                try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3307/mysql", "root", "Vqo@954719");
                     PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

                    preparedStatement.setString(1, username);
                    System.out.println("YES");
                    try (ResultSet resultSet = preparedStatement.executeQuery()) {
                        if (resultSet.next()) {
                            userId = resultSet.getInt("id");
                            System.out.println("YES11");
                        }
                    }
                } catch (SQLException e) {
                    e.printStackTrace(); // Handle the exception appropriately
                }
                System.out.println("hYES");
                Starter.userSession.setSessionId(String.valueOf(userId));
                System.out.println(";YES");
                return true;




//                Starter.userSession.setSessionId(Starter.sessionManager.createSession(username));
//                return true;
            }
        }
        setStatus("Invalid username or password");
        return false;
    }


}
