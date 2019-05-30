package it.mandeep.libreria.datastructures;

import java.io.Serializable;

public class Messaggio implements Serializable {

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
