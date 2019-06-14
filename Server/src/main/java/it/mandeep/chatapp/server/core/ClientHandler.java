package it.mandeep.chatapp.server.core;

import it.mandeep.chatapp.server.elabotarion.RequestHandler;
import it.mandeep.libreria.datastructures.Utente;
import it.mandeep.libreria.network.richiesta.Richiesta;
import it.mandeep.libreria.network.risposta.Risposta;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Hashtable;
import java.util.Map;

/**
 * La classe {@code ClientHandler} si occupa di gestire le richieste che il server riceve.
 */
public class ClientHandler extends Thread {

    private Socket client;
    private String adress;
    private ObjectOutputStream out;
    private ObjectInputStream in;
    private Richiesta richiesta;
    private Risposta risposta;
    //public static Map<Utente, String> utentiOnline = new Hashtable<>();

    /**
     * Unico costruttore della classe, consentedi inizializzare il Socker del Client da gestire.
     * @param client oggetto client da gestire.
     */
    public ClientHandler(Socket client) {
        this.client = client;
        String completeAdress = String.valueOf(this.client.getRemoteSocketAddress());
        completeAdress = completeAdress.replace("/", "");
        String[] adressParts = completeAdress.split(":");
        adress = adressParts[0];
    }

    /**
     * Metodo run della classe Thread, gestisce l'effettiva richiesta ricevuta dal client.
     */
    @Override
    public void run() {

        // Inizializza gli stream di input ed output.
        try {

            out = new ObjectOutputStream(client.getOutputStream());
            in = new ObjectInputStream(client.getInputStream());

        } catch (IOException ex) {
            System.err.println("Errore durante l'inizializzazione degli stream.. " + ex.getMessage());
        }

        try {

            // Ricevo la richiesta
            richiesta = (Richiesta) in.readObject();

            risposta = RequestHandler.handle(richiesta, adress);

            // Invio la risposta
            out.writeObject(risposta);

        } catch (IOException ex) {
            System.err.println("Errore durante la lettura del messaggio.. " + ex.getMessage());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                client.close();
                out.close();
                in.close();
            } catch (IOException ex) {
                System.err.println("Errore durante la chiusura della connessione: " + ex.getMessage());
            }
        }
    }
}
