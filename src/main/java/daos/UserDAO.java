package daos;

import databaseConnection.ConnessioneDatabase;
import model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


/**
 * The class UserDAO
 */
public class UserDAO {
    private Connection connection;
    public UserDAO() {
        try {
            connection = ConnessioneDatabase.getInstance().getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    /**
     * Takes an object User as input and inserts it into the database.
     *
     * @param user The user
     */
    public void insertUser(User user){
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


    /**
     * Takes the strings username and password as input and checks whether an user with given data is present in the database.
     *
     * @param username The username
     * @param password The password
     * @return The user as an object if the process was successful, otherwise returns null
     */
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


    /**
     * Takes the string userName as input and checks whether a user with that userrname is present in the database.
     * Returns true if no match was found.
     *
     * @param userName The username
     * @return The success value
     */
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


    /**
     * Method used for autentification purposes. Checks whether the credentials taken as input match a user in the database.
     *
     * @param username The given username
     * @param password The given password
     * @return An integer indicating the login attempt's outcome
     */
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