package todo;

import java.util.ArrayList;

public class Utenti {
    public static ArrayList<Utente> u;
    public Utenti() {}
    public Utenti(ArrayList<Utente> u){
        this.u = u;
    }
    public void addUtente(Utente u){
        this.u.add(u);
    }
}
