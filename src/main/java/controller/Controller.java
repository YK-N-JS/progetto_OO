package controller;

import daos.BachecaDAO;
import daos.TodoDAO;
import daos.UserDAO;
import databaseConnection.ConnessioneDatabase;
import model.Bacheca;
import model.Icon;
import model.Todo;
import model.User;

import javax.swing.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
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
    private Icon icone = new Icon();


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

    public void loadBachecheUser(User user) {
        ArrayList<Bacheca> bachecheUser = new ArrayList<>();
        bachecheUser = bachecaDAO.getAllBacheca(user.getUsername());
        user.setBacheche(bachecheUser);
    }

    public boolean addBacheca(User user, Bacheca bacheca) {
        if(user.addBacheca(bacheca))
        {
           bachecaDAO.createBacheca(bacheca, user.getUsername());
           return true;
        } else {
            return false;
        }
    }

    public void editBacheca(Bacheca bacheca) {
        bachecaDAO.editBacheca(bacheca);
    }

    public boolean deleteBacheca(Bacheca bacheca, User user) {
        if (bachecaDAO.deleteBacheca(bacheca)){
            user.removeBacheca(bacheca);
        return true;
        } else {
            return false;
        }
    }

    public ArrayList<Todo> getTodoAlphabetical(Bacheca bacheca) {
        bacheca.setTodoInBacheca(todoDAO.getTodoInBachecaAlphabetical(bacheca));
        return todoDAO.getTodoInBachecaAlphabetical(bacheca);
    }

    public ArrayList<Todo> getTodoByDate(Bacheca bacheca){
        bacheca.setTodoInBacheca(todoDAO.getTodoInBachecaByDate(bacheca));
        return todoDAO.getTodoInBachecaByDate(bacheca);
    }

    public void addTodo(Bacheca bacheca, Todo todo, String username)
    {
        todoDAO.addTodo(bacheca, todo, username);
        bacheca.addTodo(todo);
    }

    public void removeTodo(Todo todo, Bacheca bacheca)
    {
        todoDAO.removeTodo(todo, bacheca);
        bacheca.removeATodo(todo);
    }

    public void editTodo(Todo todo){
        todoDAO.editTodo(todo);
    }

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

    public void spostaTodo(int todoid, int origin, int destination)
    {
        todoDAO.spostaTodo(todoid, origin, destination);
    }

    public ImageIcon getIcon(Todo todo)
    {
        return icone.getIcon(todo);
    }

    public ImageIcon getIconByNumber(int iconId)
    {
        return icone.getIconBYNumber(iconId);
    }



}
