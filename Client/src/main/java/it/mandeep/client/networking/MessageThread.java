package it.mandeep.client.networking;

import it.mandeep.libreria.network.richiesta.Richiesta;
import it.mandeep.libreria.network.risposta.Risposta;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class MessageThread extends Thread {
    private Socket server = null;
    private ObjectOutputStream out = null;
    private ObjectInputStream in = null;
    private Risposta risposta = null;
    private Richiesta richiesta;
    private String adress;

    /**
     * Costruttore che inizializza la richieta.
     * @param richiesta {@code Richiesta} che sarà inviata.
     */
    public MessageThread(Richiesta richiesta, String adress) {
        this.richiesta = richiesta;
        this.adress = adress;
    }

    @Override
    public void run() {
        try {
            server = new Socket(adress, Adress.clientConnectionPort);

            out = new ObjectOutputStream(server.getOutputStream());
            in = new ObjectInputStream(server.getInputStream());

            out.writeObject(richiesta);
            risposta = (Risposta) in.readObject();
        } catch (IOException ex) {
            System.err.println("Errore durante la connessione al client destinatario.. " + ex.getMessage());
            // TODO: Finire la classe message Thread che serve per inviare un messaggio ad un client.
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
