package todo;

public class Todo {
    public String titolo;
    //per ora stringa. eventualmente tipo migliore per data
    //public String data_scadenza;
    public String URL_attivita;
    public String descrizione;
    public boolean completato;
    public boolean scaduto;
    //TODO aggiungere tipi giusti
    //public immagine;
    //public colore;

    //PROTOTIPI DI COSTRUTTORE
    //eventualmente andrebbero presi da input uno per volta tutti
    //i paramentri e messi a default quelli lasciati bianchi
    public Todo(){
        this.titolo = "";
        this.URL_attivita = "";
        this.descrizione = "";
        this.completato = false;
        this.scaduto = false;
        //TODO da implementare questi tipi:
        //this.data_scadenza = NULL;
        //this.immagine = NULL;
        //this.colore = (255, 255, 255);
    }
    public void changetitle(String newtitle)
    {
        this.titolo = newtitle;
    }
    public void changeUrlActivity(String newurl)
    {
        this.URL_attivita = newurl;
    }
    public void changeDescription(String newdescription)
    {
        this.descrizione = newdescription;
    }
    public void setCompletato()
    {
        this.completato = true;
    }
    public void setScaduto(/* eventualmente data*/)
    {
        this.scaduto = true;
        // TODO controlla la data e setta a true se è passata
    }
    //TODO aggiungere metodi per immagine e colore eventualmente
}
