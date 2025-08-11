package controller;

import daos.BachecaDAO;
import daos.TodoDAO;
import daos.UserDAO;
import databaseConnection.ConnessioneDatabase;
import model.Bacheca;
import model.Todo;
import model.User;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 * The type Controller.
 */
public class Controller {
    private UserDAO userDAO = new UserDAO();
    private BachecaDAO bachecaDAO = new BachecaDAO();
    private TodoDAO todoDAO = new TodoDAO();
    //TODO LA CONNESSIONE NON VA, CAPISCI PERCHÉ
    private ConnessioneDatabase connection;

    {
        try {
            connection = ConnessioneDatabase.getInstance();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Instantiates a new Controller.
     */
    public Controller() {}

    /**
     * Add user to the list of users.
     *
     * @param username the username
     * @param password the password
     */
    public void addUser( String username, String password) {
        User user = new User(username, password);
       userDAO.insertUser(user);
    }

    /**
     * Checks if username already exists in user list.
     *
     * @param username the username
     * @return the boolean
     */
    public boolean checkNewUser(String username) {
        return userDAO.checkUser(username);
    }

    /**
     * Returns an int based on the status of the login (0 for success, -1 for wrong password, -2 for username not in Users).
     *
     * @param username the username
     * @param password the password
     * @return the int
     */
    public int login(String username, String password) {
        return userDAO.login(username, password);
    }

    /**
     * Gets user.
     *
     * @param username the username
     * @param password the password
     * @return the user
     */
    public User getUser(String username, String password) {
        return userDAO.getUser(username, password);
    }

    public void shareTodo(int todoID, String username) {
        todoDAO.shareTodo(todoID, username);
    }

}
