package it.mandeep.client.networking;

import it.mandeep.libreria.network.richiesta.Richiesta;
import it.mandeep.libreria.network.risposta.Risposta;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.concurrent.Callable;

public class RichiestaCallable implements Callable<Risposta> {

    private Socket server;
    private ObjectOutputStream out;
    private ObjectInputStream in;
    private Risposta risposta;
    private Richiesta richiesta;

    public RichiestaCallable(Richiesta richiesta) {
        this.richiesta = richiesta;
    }

    @Override
    public Risposta call() throws Exception {
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
        return risposta;
    }
}
