package it.mandeep.client.chat;

import it.mandeep.client.networking.Adress;

import java.io.IOException;
import java.net.ServerSocket;

public class Chat {

    private ServerSocket server;

    public Chat() {
        try {
            server = new ServerSocket(Adress.port);
            System.out.println("Chat avviata con successo.");
        } catch (IOException ex) {
            System.err.println("Errore durante l'avvio della chat.. " + ex.getMessage());
        }

        while (server != null) {
            try {
                new ChatHandler(server.accept()).start();
                System.out.println("Server connesso con successo.");
            } catch (IOException ex) {
                System.err.println("Errore durante alla connessione al client.. " + ex.getMessage());
            }
        }
    }
}
