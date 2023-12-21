package database.deleting;

import classes.DBConnector;
import classes.Starter;
import database.updating.ProductUpdater;
import helpers.Alerts;
import model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class UserDeleter {
    private String status;
    private Connection connection;
    public UserDeleter(Connection connection){
        this.connection = connection;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }

    public boolean deleteUserFromUsersTableByUsername(User user){
        if(!deleteUserFromTypeTableUsingUsername(user)){
            setStatus("Couldn't delete user.");
            return false;
        }
        PreparedStatement preparedStmt = null;
        try {
            String query = "delete from users where username = '" + user.getUsername() + "';";
            preparedStmt = connection.prepareStatement(query);
            preparedStmt.execute();
            setStatus("User was deleted successfully.");
            return true;
        } catch (Exception e) {
            setStatus("Couldn't delete user.");
            return false;
        }finally{
            if(preparedStmt!=null){
                try{
                    preparedStmt.close();
                }catch (Exception e){
                    Starter.logger.warning("error while closing the statement.");
                }
            }
        }
    }

    private boolean deleteUserFromTypeTableUsingUsername(User user){
        PreparedStatement preparedStmt = null;
        try {
            String type = "admins";
            if(user.getUserType().equals("customer"))
                type = "customers";
            else if(user.getUserType().equals("installer"))
                type = "installers";
            String query = "delete from " + type + " where username = '" + user.getUsername() + "';";
            preparedStmt = connection.prepareStatement(query);
            preparedStmt.execute();
            return true;
        } catch (Exception e) {
            return false;
        }finally{
            if(preparedStmt!=null){
                try{
                    preparedStmt.close();
                }catch (Exception e){
                    Starter.logger.warning("error while closing the statement.");
                }
            }
        }
    }
}
