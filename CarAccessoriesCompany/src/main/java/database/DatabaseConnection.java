package database;

import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseConnection {
    private String databaseName, username, password;
    private int port;
    private Connection con;

    private boolean status;

    public DatabaseConnection(){
        setPort(3306);
        setDatabaseName("caraccessoriescompany");
        setUsername("root");
        setPassword("12345678password");
        setCon();
    }
    public DatabaseConnection(int port, String databaseName, String username, String password){
        setPort(port);
        setDatabaseName(databaseName);
        setUsername(username);
        setPassword(password);
        setCon();
    }
    public void setDatabaseName(String databaseName) {
        this.databaseName = databaseName;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public void setCon() {
        String url = "jdbc:mysql://localhost:" + port + "/" + databaseName;
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            con= DriverManager.getConnection(url,username,password);
            setStatus(true);
        }catch(Exception e){
            System.out.println(e);
            setStatus(false);
        }
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Connection getCon() {
        return con;
    }
}
