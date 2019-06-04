package it.mandeep.libreria.network.risposta;

import it.mandeep.libreria.datastructures.Utente;
import it.mandeep.libreria.network.Adress;

import java.io.Serializable;

public class Risposta implements Serializable {

    private int risultatoRisposta;
    private Utente utente;
    private Adress adress;

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

    public Adress getAdress() {
        return adress;
    }

    public void setAdress(Adress adress) {
        this.adress = adress;
    }
}
