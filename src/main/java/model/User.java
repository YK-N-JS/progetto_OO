package model;

import java.util.ArrayList;

/**
 * The data type User.
 */
public class User {
    private ArrayList<Bacheca> bacheche = new ArrayList<>();
    private String username;
    private String password;
    private int num_max_bacheche = 10;

    /**
     * Instantiates a new User.and creates default bacheche and their description
     *
     * @param username the username of the user
     * @param password the password of the user
     */
    public User(String username, String password) {
        this.username = username;
        this.password = password;

        this.addBacheca(new Bacheca());
        bacheche.get(0).setTitle("Default");
        bacheche.get(0).setDescription("Your default Bachec");

        this.addBacheca(new Bacheca());
        bacheche.get(1).setTitle("Università");
        bacheche.get(1).setDescription("University's Bachec");

        this.addBacheca(new Bacheca());
        bacheche.get(2).setTitle("Lavoro");
        bacheche.get(2).setDescription("Lavoro's Bachec");

        this.addBacheca(new Bacheca());
        bacheche.get(3).setTitle("Tempo libero");
        bacheche.get(3).setDescription("Tempo libero's Bachec");
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
     * Given as parameter the index of a user's bacheca, it returns said bacheca as an element
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
     * Sets bacheche.
     *
     * @param bacheche the bacheche
     */
    public void setBacheche(ArrayList<Bacheca> bacheche) {
        this.bacheche = bacheche;
    }

    /**
     * Adds a bacheca if the number of bacheche is less than num_max_bacheche and returns true
     * otherwise it does nothing and returns false
     *
     * @param bacheca the bacheca
     * @return boolean success
     */
    public boolean addBacheca(Bacheca bacheca) {
        if(bacheche.size()<num_max_bacheche ) {
            this.bacheche.add(bacheca);
            return true;
        }
        else return false;
    }

    /**
     * Takes a bacheca as parameter and deletes it from its array list
     *
     * @param bacheca the bacheca that will be deleted
     */
    public void removeBacheca(Bacheca bacheca) {
        this.bacheche.remove(bacheca);
    }
}
