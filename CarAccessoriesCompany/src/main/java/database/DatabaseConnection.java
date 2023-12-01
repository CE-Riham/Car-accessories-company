package database;

import classes.Starter;

import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseConnection {
    private String databaseName;
    private String username;
    private String password;
    private String status;
    private int port;
    private Connection con;

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
    public String getStatus() { return status;}

    public Connection getCon() {
        return con;
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
    public void setStatus(String status) {
        this.status = status;
    }


    public void setCon() {
        String url = "jdbc:mysql://localhost:" + port + "/" + databaseName;
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            con= DriverManager.getConnection(url,username,password);
            setStatus("Connected to the database successfully");
        }catch(Exception e){
            Starter.logger.warning(e.getMessage());
            setStatus("Couldn't connect to the database");
        }
    }
}
