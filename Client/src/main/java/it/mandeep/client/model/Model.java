package it.mandeep.client.model;

import it.mandeep.client.chat.Chat;
import it.mandeep.client.networking.MessageThread;
import it.mandeep.client.networking.RequestThread;
import it.mandeep.client.networking.RichiestaCallable;
import it.mandeep.libreria.datastructures.Utente;
import it.mandeep.libreria.network.richiesta.ConcreteRichiestaBuilder;
import it.mandeep.libreria.network.richiesta.Richiesta;
import it.mandeep.libreria.network.richiesta.RichiestaBuilder;
import it.mandeep.libreria.network.richiesta.TipoRichiesta;
import it.mandeep.libreria.network.risposta.Risposta;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Model {

    private Chat chatServer;
    private RequestThread requestThread;
    private RichiestaCallable richiestaCallable;
    private ExecutorService executorService = Executors.newSingleThreadExecutor();
    private Future<Risposta> future;
    private RichiestaBuilder richiestaBuilder = new ConcreteRichiestaBuilder();
    private Richiesta richiesta;
    private Risposta risposta;
    private Utente utente;
    private Utente destinatario;
    private List<Utente> utenti = new ArrayList<>();

    public void inizializza() {
        inizializzaServerInterno();
        try {
            utenti = getUtenti();
        } catch (ExecutionException | InterruptedException e) {
            System.err.println("Errore durante la lettura degli utenti.. " + e.getMessage());
        }
    }

    private void inizializzaServerInterno() {
        chatServer = new Chat();
        chatServer.setDaemon(true);
        chatServer.start();
    }

    /**
     * Metodo login, contatta il server per poter accedere al programma.
     * @param utente contiene i dati dell'utente che deve accedere.
     * @return {@link Risposta} restituita dal server.
     * @throws ExecutionException
     * @throws InterruptedException
     */
    public Risposta login(Utente utente) throws ExecutionException, InterruptedException {
        richiesta = richiestaBuilder.buildMittente(utente).buildTipoRichiesta(TipoRichiesta.LOGIN).build();
        richiestaCallable = new RichiestaCallable(richiesta);

        future = executorService.submit(richiestaCallable);

        risposta = future.get();

        utente = risposta.getUtente();
        return risposta;
    }

    public void inviaMessaggio(Utente mittente, Utente destinatario, MessageThread messageThread) {
        // TODO: Send message

        System.out.println("Messaggio inviato.");
    }

    public List<Utente> getUtenti() throws ExecutionException, InterruptedException {
        richiesta = richiestaBuilder.buildTipoRichiesta(TipoRichiesta.GET_ALL_USERS).build();
        richiestaCallable = new RichiestaCallable(richiesta);

        // long starTime = System.nanoTime();

        future = executorService.submit(richiestaCallable);
        risposta = future.get();

        // long endTime = System.nanoTime();
        // System.out.println(endTime - starTime);
        return risposta.getUtenti();
    }

    public boolean isOnline(Utente utente) throws ExecutionException, InterruptedException {

        richiesta = richiestaBuilder.buildTipoRichiesta(TipoRichiesta.IS_ONLINE).buildDestinatario(utente).build();
        richiestaCallable = new RichiestaCallable(richiesta);

        future = executorService.submit(richiestaCallable);

        risposta = future.get();
        destinatario = risposta.getUtente();
        return risposta.getRisultatoRisposta() == 0;
    }
}
