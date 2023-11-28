package Classes;

import model.User;

public class UserSession extends User{
    private static String sessionId;

    public UserSession(User user) {
        super(user);
    }

    public static String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    @Override
    public String toString() {
        return "Classes.UserSession{" + getUsername() + ": " +
                "sessionId='" + sessionId + '\'' +
                '}';
    }
}
