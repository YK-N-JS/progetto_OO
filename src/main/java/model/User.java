package model;

import java.util.ArrayList;

/**
 * The type User.
 */
public class User {
    private ArrayList<Bacheca> bacheche = new ArrayList<>();
    private String username;
    private String password;

    /**
     * Instantiates a new User.
     *
     * @param username the username of the user
     * @param password the password
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
     * Gets bacheche.
     *
     * @return the bacheche
     */
    public ArrayList<Bacheca> getBacheche() {
        return bacheche;
    }

    /**
     * Sets bacheche.
     *
     * @param bacheche the bacheche
     */
    public void setBacheche(ArrayList<Bacheca> bacheche) {
        this.bacheche = bacheche;
    }

    /**
     * Add bacheca.
     *
     * @param bacheca the bacheca
     */
    public void addBacheca(Bacheca bacheca) {
        this.bacheche.add(bacheca);
    }
}
