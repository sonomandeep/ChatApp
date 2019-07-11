package it.mandeep.libreria.datastructures;

import java.io.Serializable;

/**
 * La classe {@code Messaggio} rappresenta il messaggio che sarà inviato da un client ad un'altro.
 */
public class Messaggio implements Serializable {

    private static final long serialVersionUID = 4734674844795306048L;
    private String contenuto;

    public Messaggio(String contenuto) {
        this.contenuto = contenuto;
    }

    public String getContenuto() {
        return contenuto;
    }

    public void setContenuto(String contenuto) {
        this.contenuto = contenuto;
    }

    @Override
    public String toString() {
        return "Messaggio{" +
                "contenuto='" + contenuto + '\'' +
                '}';
    }
}
