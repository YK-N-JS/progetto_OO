/* package todo;

import java.util.Scanner;

public class Utente {
    public String nome_utente;
    private String password;
    public Utente(String nome_utente, String password, String password_repeat) {
        //controlla se l'utente già esiste nella lista di utenti inizializzati
        if (checkname(nome_utente)) {
            //TODO aggiungi un modo per chiedere di cambiare utente da input
        }
        this.nome_utente = nome_utente;
        while (!password.equals(password_repeat))
        {
            //TODO da interfaccia grafica, blocca tutto finché le password non matchano
            System.out.println("le password non matchano, riprova");
            Scanner s = new Scanner(System.in);
            password_repeat = s.nextLine();
        }
        this.password = password;
        //TODO aggiunge il nuovo utente alla lista di utenti al database
    }
    public void changepasswd(String oldpass, String newpass)
    {
        while(!oldpass.equals(this.password))
        {
            System.out.println("vecchia password errata");
            Scanner s = new Scanner(System.in);
            oldpass = s.nextLine();
        }
        this.password = newpass;
    }

     public boolean checkname(String nome)
            //TODO interroga il database per vedere se esiste già il nome utente
    {
            if (esiste già)
            {
                System.out.println("utente già esistente, try again");
            }
    }

    public void changeusernm(String newuser, String password)
    {
        while(!password.equals(this.password))
        {
            System.out.println("vecchia password errata");
            Scanner s = new Scanner(System.in);
            password = s.nextLine();
        }
        this.nome_utente = newuser;
    }

    public void creabacheca(String titolo) {
        Bacheca b= new Bacheca(titolo);
        b.proprietario = this;
    }
}
 */