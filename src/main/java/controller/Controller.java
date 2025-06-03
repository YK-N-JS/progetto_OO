package controller;

import model.Bacheca;
import model.Todo;
import model.User;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 * The type Controller.
 */
public class Controller {
    //temporary userlist waiting for db implementation
    private ArrayList<User> users = new ArrayList<>();

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
       users.add(new User(username, password));
    }

    /**
     * Checks if username already exists in user list.
     *
     * @param username the username
     * @return the boolean
     */
    public boolean checkNewUser(String username) {
        if (users.isEmpty()) {
            return true;
        }
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Returns an int based on the status of the login (0 for success, -1 for wrong password, -2 for username not in Users).
     *
     * @param username the username
     * @param password the password
     * @return the int
     */
    public int login(String username, String password) {
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                if (!user.getUsername().isEmpty()) {
                    if (user.getPassword().equals(password)) {
                        return 0; //login success
                    } else {
                        return -1; // wrong password
                    }
                }
            }
        }
        return -2; //user does not exist
    }

    /**
     * Gets user.
     *
     * @param username the username
     * @return the user
     */
    public User getUser(String username) {
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                return user;
            }
        }
        return null;
    }

    /**
     * Checks if user has expired {@link Todo}
     *
     * @param user whose todos need to be checked
     */
    public void reload_bacheche(User user) {
        for(Bacheca b: user.getBacheche())
        {
            for(Todo todo: b.getTodoInBacheca())
            {
                if (LocalDate.now().isAfter(todo.getComplete_by_date()) && !todo.getStatus().equals("completed"))
                {
                    todo.setStatus("expired");
                }
            }
        }
    }

}
