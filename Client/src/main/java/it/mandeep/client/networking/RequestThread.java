package it.mandeep.client.networking;

import it.mandeep.libreria.network.richiesta.Richiesta;
import it.mandeep.libreria.network.risposta.Risposta;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * La classe {@code RequestThread} serve per potersi connettere al server o ad un altro client per inviare richieste.
 */
public class RequestThread extends Thread {

    private Socket server = null;
    private ObjectOutputStream out = null;
    private ObjectInputStream in = null;
    private Risposta risposta = null;
    private Richiesta richiesta;

    /**
     * Costruttore che inizializza la richieta.
     * @param richiesta {@code Richiesta} che sarà inviata.
     */
    public RequestThread(Richiesta richiesta) {
        this.richiesta = richiesta;
    }

    @Override
    public void run() {
        try {
            server = new Socket(Adress.IP, Adress.port);

            out = new ObjectOutputStream(server.getOutputStream());
            in = new ObjectInputStream(server.getInputStream());

            out.writeObject(richiesta);
            risposta = (Risposta) in.readObject();
        } catch (IOException ex) {
            System.err.println("Errore durante la connessione al server.. " + ex.getMessage());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                server.close();
                out.close();
                in.close();
            } catch (IOException ex) {
                System.err.println("Errore durante la chiusera della connessione: " + ex.getMessage());
            }
        }

    }

    public Risposta getRisposta() {
        return risposta;
    }
}