package it.mandeep.libreria.network.risposta;

import it.mandeep.libreria.datastructures.Utente;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * La classe {@code Risposta} rappresenta il risultato dell'elaborazione di una {@link it.mandeep.libreria.network.richiesta.Richiesta}
 * e viene restituita al mittente.
 */
public class Risposta implements Serializable {

    private static final long serialVersionUID = -739025985924162931L;
    private int risultatoRisposta;
    private Utente utente;
    private String adress;
    private List<Utente> utenti;

    public Risposta(int risultatoRisposta) {
        this.risultatoRisposta = risultatoRisposta;
    }

    public int getRisultatoRisposta() {
        return risultatoRisposta;
    }

    public void setRisultatoRisposta(int risultatoRisposta) {
        this.risultatoRisposta = risultatoRisposta;
    }

    public Utente getUtente() {
        return utente;
    }

    public void setUtente(Utente utente) {
        this.utente = utente;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public List<Utente> getUtenti() {
        return utenti;
    }

    public void setUtenti(List<Utente> utenti) {
        this.utenti = utenti;
    }
}
