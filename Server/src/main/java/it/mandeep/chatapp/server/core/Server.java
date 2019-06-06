package it.mandeep.chatapp.server.core;


import java.io.IOException;
import java.net.ServerSocket;

/**
 * La classe {@code Server} si occupa di ricevere le connessioni dai client.
 */
public class Server {

    private ServerSocket server;

    /**
     * Il costruttore mette il server in ascolto e quando si connette un client la sua gestione viene delegata
     * ad un {@link ClientHandler}.
     */
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
