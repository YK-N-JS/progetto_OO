package model;

import java.util.ArrayList;

public class User {
    private ArrayList<Bacheca> bacheche = new ArrayList<>();
    private String username;
    private String password;
    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public ArrayList<Bacheca> getBacheche() {
        return bacheche;
    }

    public void setBacheche(ArrayList<Bacheca> bacheche) {
        this.bacheche = bacheche;
    }
    public void addBacheca(Bacheca bacheca) {
        this.bacheche.add(bacheca);
    }
}
