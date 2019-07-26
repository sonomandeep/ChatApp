package it.mandeep.client.model;

import it.mandeep.client.chat.Chat;
import it.mandeep.client.networking.MessageThread;
import it.mandeep.client.networking.RequestThread;
import it.mandeep.libreria.datastructures.Utente;
import it.mandeep.libreria.network.richiesta.ConcreteRichiestaBuilder;
import it.mandeep.libreria.network.richiesta.Richiesta;
import it.mandeep.libreria.network.richiesta.RichiestaBuilder;
import it.mandeep.libreria.network.richiesta.TipoRichiesta;
import it.mandeep.libreria.network.risposta.Risposta;

import java.util.ArrayList;
import java.util.List;

public class Model {

    private Chat chatServer;
    private RequestThread requestThread;
    private RichiestaBuilder richiestaBuilder = new ConcreteRichiestaBuilder();
    private Richiesta richiesta;
    private Utente utente;
    private List<Utente> utenti = new ArrayList<>();

    public void inizializza() {
        inizializzaServerInterno();
    }

    private void inizializzaServerInterno() {
        chatServer = new Chat();
        chatServer.setDaemon(true);
        chatServer.start();
    }

    public Risposta login(Utente utente) {
        richiesta = richiestaBuilder.buildMittente(utente).buildTipoRichiesta(TipoRichiesta.LOGIN).build();
        requestThread = new RequestThread(richiesta);
        requestThread.setDaemon(true);
        requestThread.start();

        // TODO: visualizzare la risposta del thread sulla GUI
        try {
            requestThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(requestThread.getRisposta().getRisultatoRisposta());
        utente = requestThread.getRisposta().getUtente();
        return requestThread.getRisposta();
    }

    public void inviaMessaggio(Utente mittente, Utente destinatario, MessageThread messageThread) {
        // TODO: Send message

        System.out.println("Messaggio inviato.");
    }

    public List<Utente> getUtenti() {
        richiesta = richiestaBuilder.buildTipoRichiesta(TipoRichiesta.GET_ALL_USERS).build();
        requestThread = new RequestThread(richiesta);

        requestThread.setDaemon(true);
        requestThread.start();

        // TODO: Modificare coi callable
        return null;
    }

}
