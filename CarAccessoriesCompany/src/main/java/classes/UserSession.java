package classes;

import model.User;

public class UserSession{
    private static String sessionId;
    private static User currentUser;

    public static String getSessionId() {
        return sessionId;
    }

    public static void setSessionId(String sessionId) {
        UserSession.sessionId = sessionId;
    }

    public static User getCurrentUser() {
        User tmpUser = new User(currentUser);
        return tmpUser;
    }

    public static void setCurrentUser(User currentUser) {
        UserSession.currentUser = new User(currentUser);
    }

    @Override
    public String toString() {
        return "Classes.UserSession{" + currentUser.getUsername() + ": " +
                "sessionId='" + sessionId + '\'' +
                '}';
    }
}
