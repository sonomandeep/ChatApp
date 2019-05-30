package it.mandeep.libreria.network.risposta;

import it.mandeep.libreria.datastructures.Utente;

import java.io.Serializable;

public class Risposta implements Serializable {

    private RisultatoRisposta risultatoRisposta;
    private Utente utente;

    public Risposta(RisultatoRisposta risultatoRisposta) {
        this.risultatoRisposta = risultatoRisposta;
    }

    public RisultatoRisposta getRisultatoRisposta() {
        return risultatoRisposta;
    }

    public void setRisultatoRisposta(RisultatoRisposta risultatoRisposta) {
        this.risultatoRisposta = risultatoRisposta;
    }

    public Utente getUtente() {
        return utente;
    }

    public void setUtente(Utente utente) {
        this.utente = utente;
    }
}
