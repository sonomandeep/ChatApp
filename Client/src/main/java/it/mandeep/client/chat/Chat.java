package it.mandeep.client.chat;

import it.mandeep.client.networking.Adress;

import java.io.IOException;
import java.net.ServerSocket;

/**
 * La classe {@code Chat} serve per poter far connettere un altro client al client che possiede questa istanza,
 * attraverso questa classe vengono gestite le richieste inviate dall'altro client.
 */
public class Chat extends Thread {

    private ServerSocket server;

    /**
     * Unico costruttore che ha il compito di delegare alla classe {@link ChatHandler} l'elaborazione della richietsa.
     */
    public Chat() {

    }

    @Override
    public void run() {
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
                Thread.sleep(500);
            } catch (IOException ex) {
                System.err.println("Errore durante alla connessione al client.. " + ex.getMessage());
            } catch (InterruptedException e) {
                System.err.println("Errore durante la pausa del Thread.. " + e.getMessage());
            }
        }
    }
}
