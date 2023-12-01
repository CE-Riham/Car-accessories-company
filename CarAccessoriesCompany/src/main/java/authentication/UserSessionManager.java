package authentication;

import classes.Starter;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;

public class UserSessionManager {
    private static Map<String, String> userSessions = new HashMap<>();

    private UserSessionManager() {
        throw new IllegalStateException("Utility class");
    }
    // Method to create a session for a user upon successful login
    public static String createSession(String username) {
        String sessionId = generateSessionId();
        userSessions.put(sessionId, username);
        Starter.logger.log(Level.INFO, "Session created for user: {0}, Session ID: {1}", new Object[]{username, sessionId});
        return sessionId;
    }

    // Method to check if a session exists for a given session ID
    public static boolean isValidSession(String sessionId) {
        return userSessions.containsKey(sessionId);
    }

    // Method to retrieve username from a session ID
    public static String getUsernameFromSession(String sessionId) {
        return userSessions.get(sessionId);
    }

    // Simulated session ID generation method
    private static String generateSessionId() {
        String tmpID;
        do{
            tmpID = "SESSION_" + System.currentTimeMillis();
        }while (isValidSession(tmpID));
        return  tmpID; // Not a secure way, just for demonstration
    }

    // Method to invalidate a session (logout)
    public static void invalidateSession(String sessionId) {
        userSessions.remove(sessionId);
        Starter.logger.log(Level.INFO, "Session invalidated for Session ID: {0}", sessionId);
    }

}
