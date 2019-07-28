package it.mandeep.libreria.datastructures;

import java.io.Serializable;

/**
 * La classe {@code Messaggio} rappresenta il messaggio che sarà inviato da un client ad un'altro.
 */
public class Messaggio implements Serializable {

    private static final long serialVersionUID = 4734674844795306048L;
    private String contenuto;
    private Utente mittente;
    private Utente destinatario;

    public Messaggio(String contenuto) {
        this.contenuto = contenuto;
    }

    public String getContenuto() {
        return contenuto;
    }

    public void setContenuto(String contenuto) {
        this.contenuto = contenuto;
    }

    public Utente getMittente() {
        return mittente;
    }

    public void setMittente(Utente mittente) {
        this.mittente = mittente;
    }

    public Utente getDestinatario() {
        return destinatario;
    }

    public void setDestinatario(Utente destinatario) {
        this.destinatario = destinatario;
    }

    @Override
    public String toString() {
        return "Messaggio{" +
                "contenuto='" + contenuto + '\'' +
                ", mittente=" + mittente +
                ", destinatario=" + destinatario +
                '}';
    }
}
