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
        //funziona ma si arrabbia perché la query non restituisce nulla... bah, java smh...
        try{
           PreparedStatement inserisciUtente = connection.prepareStatement("Insert INTO \"user\"(username, \"Password\") VALUES (?,?)");
            inserisciUtente.setString(1, user.getUsername());
            inserisciUtente.setString(2, user.getPassword());
            inserisciUtente.executeUpdate();

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

            if(rs.next() == false) {
                return null;
            }
            else return new User(username, password);
        }
        catch(SQLException e){
            e.printStackTrace();
            return null;
        }
    }

    public boolean checkUser(String userName) {
        try{
            PreparedStatement controllaUtente = connection.prepareStatement("Select username from \"user\" where username = ?");
            controllaUtente.setString(1, userName);
            ResultSet rs = controllaUtente.executeQuery();

            return !rs.next();
            //false se trova qualcuno, true se non trova nessuno
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

        }
        catch(SQLException e){
            e.printStackTrace();
        }
    }

    public int login(String username, String password) {
        try{
            if(checkUser(username)) //se non trova nessuno
            {
                return -2; // user does not exist
            }
            else {
             PreparedStatement query = connection.prepareStatement("Select * from \"user\" WHERE username = ? AND \"Password\" = ?");
             query.setString(1, username);
             query.setString(2, password);
             ResultSet rs = query.executeQuery();

             if(rs.next() == false) { //se la query è vuota ma c'è qualcuno con quel nome, la password è sbagliata
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
