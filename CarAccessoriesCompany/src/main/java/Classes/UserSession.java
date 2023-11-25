package Classes;

import javafx.scene.Parent;
import model.User;

public class UserSession extends User {
    private String sessionId;

    public UserSession(User user){
        setUsername(user.getUsername());
        setFirstName(user.getFirstName());
        setLastName(user.getLastName());
        setEmail(user.getEmail());
        setPassword(user.getPassword());
        setPhoneNumber(user.getPhoneNumber());
        setAddress(user.getAddress());
        setImagePath(user.getImagePath());
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    @Override
    public String toString() {
        return "tmp.UserSession{" + getUsername() + ": " +
                "sessionId='" + sessionId + '\'' +
                '}';
    }
}