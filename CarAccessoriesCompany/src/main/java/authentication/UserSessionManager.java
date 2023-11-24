package authentication;

import java.util.HashMap;
import java.util.Map;

public class UserSessionManager {
    private static Map<String, String> userSessions = new HashMap<>();

    // Method to create a session for a user upon successful login
    public static String createSession(String username) {
        String sessionId = generateSessionId();
        userSessions.put(sessionId, username);
        System.out.println("Session created for user: " + username + ", Session ID: " + sessionId);
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
        System.out.println("Session invalidated for Session ID: " + sessionId);
    }

    // For demonstration: displaying all active sessions
    public static void displayActiveSessions() {
        System.out.println("Active Sessions:");
        for (Map.Entry<String, String> entry : userSessions.entrySet()) {
            System.out.println("Session ID: " + entry.getKey() + ", Username: " + entry.getValue());
        }
    }

}
