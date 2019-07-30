package it.mandeep.client.networking;

import it.mandeep.libreria.datastructures.Messaggio;
import it.mandeep.libreria.network.risposta.Risposta;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.concurrent.Callable;

// TODO: Cercare di unire le classi MessaggioCallable e RichiestaCallable utilizzando i generics

public class MessaggioCallable implements Callable<Risposta> {

    private Socket server;
    private ObjectOutputStream out;
    private ObjectInputStream in;
    private Risposta risposta;
    private Messaggio messaggio;

    public MessaggioCallable(Messaggio messaggio) {
        this.messaggio = messaggio;
    }

    @Override
    public Risposta call() throws Exception {
        try {
            server = new Socket(messaggio.getDestinatario().getAdress(), Adress.clientConnectionPort);

            out = new ObjectOutputStream(server.getOutputStream());
            in = new ObjectInputStream(server.getInputStream());

            out.writeObject(messaggio);
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
        return risposta;
    }
}
