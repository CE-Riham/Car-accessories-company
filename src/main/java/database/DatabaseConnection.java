package database;

import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseConnection {
    private String databaseName, username, password, status;
    private int port;
    private Connection con;

    public DatabaseConnection(){
        setPort(3307);
        setDatabaseName("mysql");
        setUsername("root");
        setPassword("Vqo@954719");
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
            System.out.println(e);
            setStatus("Couldn't connect to the database");
        }
    }
}
