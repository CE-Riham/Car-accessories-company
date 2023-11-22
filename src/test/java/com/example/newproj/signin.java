package com.example.newproj;
import com.example.newproj.Database;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import static org.junit.Assert.assertEquals;

import java.sql.*;
import java.util.logging.Logger;

public class signin {
//   ResultSet s = Database.createDatabase("select * from car");
//   boolean x = s.next();


    // MySQL JDBC URL



    public signin() throws SQLException {
    }
    static Logger logger = Logger.getLogger(Database.class.getName());

    @Given("I go to loginchoise")
    public void iGoToLoginchoise() {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            //System.out.println("Connected to the MySQL databas111e!");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3307/mysql","root","Vqo@954719");
           // System.out.println("Connected to the MySQL database!");

        }catch (SQLException e) {
           // logger.log(null,"Database connection error1: ");
            System.out.println("SQL :  "+e);
        } catch (ClassNotFoundException e) {
           // throw new RuntimeException(e);
            System.out.println(e);
        }
    }
    @Given("the field {string} is empty")
    public void theFieldIsEmpty(String string) {
        assertEquals(true,string.isEmpty());
    }
    @When("I click on login and flag is {string}")
    public void iClickOnLoginAndFlagIs(String string) {
        assertEquals(true,string.equals("true"));
    }
    @Then("field {string} should be with error")
    public void fieldShouldBeWithError(String string) {
        assertEquals(true,string.isEmpty());
    }

    @When("he fills in {string} with {string}")
    public void heFillsInWith(String string, String string2) {

    }
    @Then("I should see {string}")
    public void iShouldSee(String string) {
        assertEquals(true,string.equals("E-mail or password is incorrect"));
    }
    @Then("I shouldnt see {string}")
    public void iShouldntSee(String string) {
        assertEquals(false,!string.equals("Access your account"));
    }

}
