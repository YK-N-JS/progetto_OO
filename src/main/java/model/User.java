package model;

import java.util.ArrayList;


/**
 * The data type User.
 */
public class User {
    private ArrayList<Bacheca> bacheche = new ArrayList<>();
    private String username;
    private String password;
    final int numMaxBacheche = 10;


    /**
     * Instantiates a new User.
     *
     * @param username the username of the user
     * @param password the password of the user
     */
    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }


    /**
     * Gets username.
     *
     * @return the username
     */
    public String getUsername() {
        return username;
    }


    /**
     * Sets username.
     *
     * @param username the username
     */
    public void setUsername(String username) {
        this.username = username;
    }


    /**
     * Gets password.
     *
     * @return the password
     */
    public String getPassword() {
        return password;
    }


    /**
     * Sets password.
     *
     * @param password the password
     */
    public void setPassword(String password) {
        this.password = password;
    }


    /**
     * Given as parameter the index of a user's bacheca, it returns said bacheca as an element.
     *
     * @param index the index of the bacheca inside the array list "bacheche"
     * @return the desired bacheca object
     */
    public Bacheca getBacheca(int index) {
        return bacheche.get(index);
    }


    /**
     * Gets bacheche.
     *
     * @return the bacheche
     */
    public ArrayList<Bacheca> getBacheche() {
        return bacheche;
    }


    /**
     * takes an array list of bacheche and inserts it into the user object.
     *
     * @param bacheche the bacheche
     */
    public void setBacheche(ArrayList<Bacheca> bacheche) {
        this.bacheche = bacheche;
    }


    /**
     * Adds a bacheca if the number of bacheche is less than num_max_bacheche and returns true
     * otherwise it does nothing and returns false.
     *
     * @param bacheca the bacheca
     * @return boolean success value
     */
    public boolean addBacheca(Bacheca bacheca) {
        if(bacheche.size()<numMaxBacheche ) {
            this.bacheche.add(bacheca);
            return true;
        }
        else return false;
    }


    /**
     * Takes a bacheca as parameter and deletes it from its array list.
     *
     * @param bacheca the bacheca that will be deleted
     */
    public void removeBacheca(Bacheca bacheca) {
        this.bacheche.remove(bacheca);
    }
}
