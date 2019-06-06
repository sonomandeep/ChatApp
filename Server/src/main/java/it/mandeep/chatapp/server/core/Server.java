package it.mandeep.chatapp.server.core;

import it.mandeep.libreria.datastructures.Utente;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.Hashtable;
import java.util.Map;

public class Server {

    private ServerSocket server;

    public Server() {
        try {
            server = new ServerSocket(Adress.port);
            System.out.println(String.format("Server avviato con successo, in ascolto sulla porta: %d.", Adress.port));
        } catch (IOException ex) {
            System.err.println("Errore durante l'avvio del server.. " + ex.getMessage());
        }

        while (server != null) {
            try {
                new ClientHandler(server.accept()).start();
                System.out.println("Server connesso con successo.");
            } catch (IOException ex) {
                System.err.println("Errore durante alla connessione al client.. " + ex.getMessage());
            }
        }
    }
}
