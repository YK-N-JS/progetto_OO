package daos;

import controller.Controller;
import databaseConnection.ConnessioneDatabase;
import model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO {
    private Connection connection;
    public UserDAO() {
        try {
            connection = ConnessioneDatabase.getInstance().getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insertUser(User user) {
        try{
           PreparedStatement inserisciUtente = connection.prepareStatement("Insert INTO \"user\"(username, \"Password\") VALUES (?,?)");
            inserisciUtente.setString(1, user.getUsername());
            inserisciUtente.setString(2, user.getPassword());
            inserisciUtente.executeQuery();

            connection.close();
    }
        catch (SQLException e) {
            e.printStackTrace();
          }
    }

    public User getUser(String username, String password) {
        try{
            PreparedStatement prendiUtente = connection.prepareStatement("SELECT * FROM \"user\" WHERE username = ? AND \"Password\" = ?");
            prendiUtente.setString(1, username);
            prendiUtente.setString(2, password);
            ResultSet rs = prendiUtente.executeQuery();

            connection.close();
            if(rs == null) {
                return null;
            }
            else return new User(rs.getString("username"), rs.getString("\"Password\""));
        }
        catch(SQLException e){
            e.printStackTrace();
            return null;
        }
    }

    public boolean checkUser(String userName) {
        try{
            PreparedStatement controllaUtente = connection.prepareStatement("Select * from \"user\" where username = ?");
            controllaUtente.setString(1, userName);
            ResultSet rs = controllaUtente.executeQuery();

            connection.close();
            if(rs == null) {
                return true;
            }
            else return false;
        }
        catch(SQLException e){
            e.printStackTrace();
            return false;
        }
    }

    public void dropUser(String userName) {
        try{
            PreparedStatement eliminaUtente = connection.prepareStatement("Delete from \"user\" where username = ?");
            eliminaUtente.setString(1, userName);
            eliminaUtente.executeQuery();

            connection.close();
        }
        catch(SQLException e){
            e.printStackTrace();
        }
    }

    public int login(String username, String password) {
        try{
            if(!checkUser(username))
            {
                return -2; // user does not exist
            }
            else {
             PreparedStatement query = connection.prepareStatement("Select * from \"user\" WHERE username = ? AND \"Password\" = ?");
             query.setString(1, username);
             query.setString(2, password);
             ResultSet rs = query.executeQuery();
             connection.close();
             if(rs == null) {
                 return -1; // wrong password
             }
             return 0; // login successful
            }
        }
        catch (SQLException e){
            e.printStackTrace();
            return -2;
        }
    }

}
