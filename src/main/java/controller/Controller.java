package controller;

import daos.BachecaDAO;
import daos.TodoDAO;
import daos.UserDAO;
import model.Bacheca;
import model.Icon;
import model.Todo;
import model.User;

import javax.swing.*;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 * The class Controller.
 */
public class Controller {
    private UserDAO userDAO = new UserDAO();
    private BachecaDAO bachecaDAO = new BachecaDAO();
    private TodoDAO todoDAO = new TodoDAO();
    private Icon icone = new Icon();


    /**
     * Instantiates a new Controller.
     */
    public Controller() {
        // default constructor
    }


    /**
     * Adds a user to the list of users taking its username and password as input.
     *
     * @param username the username
     * @param password the password
     */
    public void addUser( String username, String password) {
        User user = new User(username, password);
        userDAO.insertUser(user);
    }


    /**
     * Checks if the username in input already exists in the list of users.
     *
     * @param username The username
     * @return The boolean
     */
    public boolean checkNewUser(String username) {
        return userDAO.checkUser(username);
    }


    /**
     * Returns an int based on the status of the login (0 for success, -1 for wrong password, -2 for username not in Users).
     *
     * @param username The username
     * @param password The password
     * @return The int
     */
    public int login(String username, String password) {
        return userDAO.login(username, password);
    }


    /**
     * Takes a username and password and returns the corresponding user as an object.
     *
     * @param username The username
     * @param password The password
     * @return The corresponding user
     */
    public User getUser(String username, String password) {
        return userDAO.getUser(username, password);
    }


    /**
     * Takes a todo's identifier and a username as input, then shares the todo with the given user.
     *
     * @param todoID The todo's identifying number
     * @param username The user's username
     */
    public void shareTodo(int todoID, String username) {
        todoDAO.shareTodo(todoID, username);
    }


    /**
     * Takes a user as input and loads its bacheche into its corresponding array list.
     *
     * @param user the user
     */
    public void loadBachecheUser(User user) {
        ArrayList<Bacheca> bachecheUser = bachecaDAO.getAllBacheca(user.getUsername());
        user.setBacheche(bachecheUser);
    }


    /**
     * Takes a user and a new bacheca as input, then adds said bacheca to the user's bacheche array list and to the database.
     *
     * @param user  The user
     * @param bacheca The new bacheca
     * @return The success value (boolean)
     */
    public boolean addBacheca(User user, Bacheca bacheca) {
        if(user.addBacheca(bacheca))
        {
           bachecaDAO.createBacheca(bacheca, user.getUsername());
           return true;
        } else {
            return false;
        }
    }


    /**
     * Takes an updated bacheca as input and applies the changes made to the database.
     *
     * @param bacheca  the modified bacheca
     */
    public void editBacheca(Bacheca bacheca) {
        bachecaDAO.editBacheca(bacheca);
    }


    /**
     * Takes a bacheca and its owner as input, and removes the bacheca from the owner's bacheche array list.
     *
     * @param bacheca The bacheca to be deleted
     * @param user The bacheca's owner
     * @return The success value
     */
    public boolean deleteBacheca(Bacheca bacheca, User user) {
        if (bachecaDAO.deleteBacheca(bacheca)){
            user.removeBacheca(bacheca);
        return true;
        } else {
            return false;
        }
    }


    /**
     * Takes a bacheca as input and returns its assigned todos in alphabetical order.
     *
     * @param bacheca The given bacheca
     * @return  The array list of todos in alphabetical order
     */
    public ArrayList<Todo> getTodoAlphabetical(Bacheca bacheca) {
        bacheca.setTodoInBacheca(todoDAO.getTodoInBachecaAlphabetical(bacheca));
        return todoDAO.getTodoInBachecaAlphabetical(bacheca);
    }


    /**
     * Takes a bacheca as input and returns its assigned todos in order of expiration.
     *
     * @param bacheca The given bacheca
     * @return  The array list of todos in order of expiration
     */
    public ArrayList<Todo> getTodoByDate(Bacheca bacheca){
        bacheca.setTodoInBacheca(todoDAO.getTodoInBachecaByDate(bacheca));
        return todoDAO.getTodoInBachecaByDate(bacheca);
    }


    /**
     * Takes as input a bacheca, a new todo and the owner's username, then procedes to add the todo to the bacheca it was created in and to the database.
     *
     * @param bacheca The bacheca
     * @param todo The new todo
     * @param username The owner's username
     */
    public void addTodo(Bacheca bacheca, Todo todo, String username)
    {
        todoDAO.addTodo(bacheca, todo, username);
        bacheca.addTodo(todo);
    }


    /**
     * Takes as input a todo and the bacheca it belongs to, removes said todo from its bacheca.
     *
     * @param todo The todo to be removed
     * @param bacheca The bacheca
     */
    public void removeTodo(Todo todo, Bacheca bacheca)
    {
        todoDAO.removeTodo(todo, bacheca);
        bacheca.removeATodo(todo);
    }


    /**
     * Takes an edited todo as input and applies its changes to the database.
     *
     * @param todo The modified todo
     */
    public void editTodo(Todo todo){
        todoDAO.editTodo(todo);
    }


    /**
     * Takes as input a todo and a boolean and sets the todo's status to that value.
     *
     * @param todo The todo
     * @param status The boolean value
     */
    public void setCompleted(Todo todo, boolean status)
    {
            todo.setStatus(status);
            if(status){
                todo.setColor(5); //verde
            } else {
                if(todo.getComplete_by_date().isBefore(LocalDate.now())) {
                    todo.setColor(6);
                } else {
                    todo.setColor(0);
                }
            }
            todoDAO.editTodo(todo);
    }


    /**
     * Takes a todo's identifier as input and the identifier of two bachecas, then moves the todo from the first to the second one.
     *
     * @param todoid The todo's identifier
     * @param origin The starting bacheca's identifier
     * @param destination The desired destination's bacheca's identifier
     */
    public void spostaTodo(int todoid, int origin, int destination)
    {
        todoDAO.spostaTodo(todoid, origin, destination);
    }


    /**
     * Takes a todo as input and returns its assigned ImageIcon.
     *
     * @param todo The todo
     * @return The todo's icon
     */
    public ImageIcon getIcon(Todo todo)
    {
        return icone.getIcon(todo);
    }


    /**
     * Takes an integer as input, then returns the corresponding ImageIcon.
     *
     * @param iconId The integer
     * @return The corresponding ImageIcon
     */
    public ImageIcon getIconByNumber(int iconId)
    {
        return icone.getIconBYNumber(iconId);
    }
}
