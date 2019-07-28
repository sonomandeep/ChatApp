package it.mandeep.libreria.network.richiesta;

import it.mandeep.libreria.datastructures.Messaggio;
import it.mandeep.libreria.datastructures.Utente;

import java.io.Serializable;

/**
 * La classe {@code Richiesta} rappresenta il pacchetto che viene inviato
 * da un socket ad un altro, contiene informazioni sul tipo di richietsa,
 * sul come deve essere elaborata la risposta.
 */
public class Richiesta implements Serializable {

    private static final long serialVersionUID = 5422119085386529419L;
    private TipoRichiesta tipoRichiesta;
    private Utente mittente = null;
    private Utente destinatario = null;

    public TipoRichiesta getTipoRichiesta() {
        return tipoRichiesta;
    }

    public void setTipoRichiesta(TipoRichiesta tipoRichiesta) {
        this.tipoRichiesta = tipoRichiesta;
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
        return "Richiesta{" +
                "tipoRichiesta=" + tipoRichiesta +
                ", mittente=" + mittente +
                ", destinatario=" + destinatario +
                '}';
    }
}
