package it.mandeep.chatapp.server.core;

import it.mandeep.libreria.datastructures.Utente;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static List<Utente> utenti;
    public static List<Utente> utentiOnline = new ArrayList<>();

    public static void main(String[] args) {
        new Server();
    }
}
