package authentication;

import classes.Starter;
import classes.UserSession;
import helpers.Alerts;
import helpers.stage_helpers.AuthenticationStageHelper;
import javafx.event.ActionEvent;
import javafx.scene.control.ButtonType;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.logging.Level;

public class UserSessionManager {
    private static Map<String, String> userSessions = new HashMap<>();
    private static String status;
    public static String getStatus() {
        return status;
    }
    public static void setStatus(String status) {
        UserSessionManager.status = status;
    }

    private UserSessionManager() {
        throw new IllegalStateException("Utility class");
    }
    public static String createSession(String username) {
        String sessionId = generateSessionId();
        userSessions.put(sessionId, username);
        Starter.logger.log(Level.INFO, "Session created for user: {0}, Session ID: {1}", new Object[]{username, sessionId});
        return sessionId;
    }

    public static boolean isValidSession(String sessionId) {
        return userSessions.containsKey(sessionId);
    }

    private static String generateSessionId() {
        String tmpID;
        do{
            tmpID = "SESSION_" + System.currentTimeMillis();
        }while (isValidSession(tmpID));
        return  tmpID; // Not a secure way, just for demonstration
    }

    public static void invalidateSession(String sessionId) {
        userSessions.remove(sessionId);
        setStatus("Session invalidated for Session ID:" + sessionId);
    }
    public static void logoutUser(){
        UserSessionManager.invalidateSession(UserSession.getSessionId());
        UserSession.setSessionId(null);
        setStatus("Logged out");

    }
}
