package it.mandeep.client.model;

import it.mandeep.client.chat.Chat;
import it.mandeep.client.networking.RequestThread;
import it.mandeep.libreria.datastructures.Utente;
import it.mandeep.libreria.network.richiesta.ConcreteRichiestaBuilder;
import it.mandeep.libreria.network.richiesta.Richiesta;
import it.mandeep.libreria.network.richiesta.RichiestaBuilder;
import it.mandeep.libreria.network.richiesta.TipoRichiesta;

public class Model {

    private Chat chatServer;
    private RequestThread requestThread;
    private RichiestaBuilder richiestaBuilder = new ConcreteRichiestaBuilder();
    private Richiesta richiesta;

    public void inizializza() {
        inizializzaServerInterno();
    }

    private void inizializzaServerInterno() {
        chatServer = new Chat();
        chatServer.setDaemon(true);
        chatServer.start();
    }

    public void login(Utente utente) {
        richiesta = richiestaBuilder.buildMittente(utente).buildTipoRichiesta(TipoRichiesta.LOGIN).build();
        requestThread = new RequestThread(richiesta);
        requestThread.setDaemon(true);
        requestThread.start();
    }

    public void inviaMessaggio() {
        // TODO: Send message
        System.out.println("Messaggio inviato.");
    }

}
