package todo;

public class Utente {
    public String nome_utente;
    private String password;
    public Utente(String nome_utente, String password, String password_repeat) {
        //controlla se l'utente già esiste nella lista di utenti inizializzati
        if (checkname(nome_utente)) {
            //TODO aggiungi un modo per chiedere di cambiare utente da input
        }
        this.nome_utente = nome_utente;
        if (!password.equals(password_repeat))
        {
            System.out.println("le password non matchano");
            //TODO aggiungi modo per reinserire password_repeat
        }
        this.password = password;
        //aggiunge il nuovo utente alla lista di utenti
        Utenti.u.add(this);
    }
    public void changepasswd(String oldpass, String newpass)
    {
        if(oldpass.equals(this.password))
        {
            this.password = newpass;
        } else {
            System.out.println("vecchia password errata");
            //TODO aggiungi un modo di richiedere la vecchia password
        }
    }

    public boolean checkname(String nome)
            //restituisce true se il nome esiste tra gli utenti, false se non esiste
    {
        for (Utente u : Utenti.u)
        {
            if (u.nome_utente.equals(nome_utente))
            {
                System.out.println("utente già esistente");
                // probabilmente da aggiungere qui la funzione che richiede il nuovo nome utente se quello vecchio esiste e richiama checkname()
                return true;
            }
        }
        return false;
    }

    public void changeusernm(String newuser, String password)
    {
        if(password.equals(this.password))
        {
            this.nome_utente = newuser;
        } else {
            System.out.println("vecchia password errata");
            //TODO aggiungi un modo di richiedere la vecchia password
        }
    }
    //decidere come funzioneranno le bacheche
    public void creaToDo(Bacheca b)
    {
        Todo e = new Todo();
        b.td.add(e);
    }
}
