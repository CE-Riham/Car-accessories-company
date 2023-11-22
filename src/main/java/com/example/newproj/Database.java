package com.example.newproj;
import oracle.jdbc.pool.OracleDataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Logger;
import java.sql.DriverManager;

public class Database {
    private Database(){
    }
    static Logger logger = Logger.getLogger(Database.class.getName());
    public static ResultSet createDatabase(String string) {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
//            OracleDataSource ods = new OracleDataSource();
//            ods.setURL("jdbc:oracle:thin:@localhost:1963:xe");
//            ods.setUser("SN");
//            ods.setPassword("123456");
//            Connection con = ods.getConnection();
//            Statement stmt = con.createStatement();
           Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3307/mysql","root","Vqo@954719");
           // System.out.println("Connected to the MySQL database!");
            statement = connection.createStatement();
             return statement.executeQuery(string);
        } catch (SQLException e) {
            logger.log(null,"Database connection error: ");
            System.out.println("In Database");

        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public static void insertIntoDatabase(String string) {

        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
//            OracleDataSource ods = new OracleDataSource();
//            ods.setURL("jdbc:oracle:thin:@localhost:1521:xe");
//            ods.setUser("SN");
//            ods.setPassword("123456");
//            Connection con = ods.getConnection();
//            Statement stmt = con.createStatement();
//            stmt.executeUpdate(string);

            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3307/mysql","root","Vqo@954719");
            statement = connection.createStatement();
            statement.executeUpdate(string);
        } catch (SQLException | ClassNotFoundException e) {
            logger.log(null,"Database connection error: ");
        }
    }
}
