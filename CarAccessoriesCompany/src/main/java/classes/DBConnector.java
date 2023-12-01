package classes;

import database.DatabaseConnection;

public class DBConnector {
    private static DatabaseConnection connector;

    private DBConnector() {
        throw new IllegalStateException("Utility class");
    }
    public static DatabaseConnection getConnector() {
        return connector;
    }

    public static void setConnector(DatabaseConnection connector) {
        DBConnector.connector = connector;
    }
}
