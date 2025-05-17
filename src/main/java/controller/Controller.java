package controller;

import model.User;

import java.util.ArrayList;

public class Controller {
    //temporary userlist waiting for db implementation
    private ArrayList<User> users = new ArrayList<>();
    public Controller() {}
    public void addUser( String username, String password) {
       users.add(new User(username, password));
    }
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

    public User getUser(String username) {
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                return user;
            }
        }
        return null;
    }

}
