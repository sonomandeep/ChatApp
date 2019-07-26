package it.mandeep.chatapp.server.core;

import it.mandeep.chatapp.server.database.dao.UtenteDao;
import it.mandeep.libreria.datastructures.Utente;

import java.util.ArrayList;
import java.util.List;

public class Main {

    // TODO: spostare questi oggetti se possibile nella classe RequestHandler.
    public static UtenteDao utenteDao = new UtenteDao();
    public static List<Utente> utenti = utenteDao.readAll();
    public static List<Utente> utentiOnline = new ArrayList<>();

    public static void main(String[] args) {
        new Server();
    }
}
