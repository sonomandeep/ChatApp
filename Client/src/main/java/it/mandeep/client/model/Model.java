package it.mandeep.client.model;

import it.mandeep.client.chat.Chat;
import it.mandeep.client.networking.MessaggioCallable;
import it.mandeep.client.networking.RichiestaCallable;
import it.mandeep.libreria.datastructures.Messaggio;
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

/**
 * Classe Model, gestisce tutta la logica dietro al programma.
 */
public class Model {

    private Chat chatServer;
    private RichiestaCallable richiestaCallable;
    private MessaggioCallable messaggioCallable;
    private ExecutorService executorService = Executors.newSingleThreadExecutor();
    private Future<Risposta> future;
    private RichiestaBuilder richiestaBuilder = new ConcreteRichiestaBuilder();
    private Richiesta richiesta;
    private Risposta risposta;
    private Messaggio messaggio;
    private Utente utente;
    private Utente destinatario;
    private List<Utente> utenti = new ArrayList<>();

    /**
     * Metodo inizializza, si occupa di inizializzare tutte le risorse necessarie al programma.
     */
    public void inizializza() {
        inizializzaServerInterno();
    }

    /**
     * Metodo inizializzaServerInterno, si occupa di avviare il servono interno per gestire i messaggi.
     */
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

    /**
     * Metodo inviaMessaggio, si occupa della gestione dell'invio dei messaggi.
     * @param contenuto rappresenta il contenuto del mesaggio da inviare.
     * @return 0 se va tutto a buon fine, altrimenti 1
     * @throws ExecutionException
     * @throws InterruptedException
     */
    public int inviaMessaggio(String contenuto) throws ExecutionException, InterruptedException {
        messaggio = new Messaggio(contenuto);
        messaggio.setMittente(utente);
        messaggio.setDestinatario(destinatario);

        messaggioCallable = new MessaggioCallable(messaggio);
        future = executorService.submit(messaggioCallable);

        risposta = future.get();
        System.out.println("Messaggio inviato.");

        return risposta.getRisultatoRisposta();
    }

    public List<Utente> getUtenti() throws ExecutionException, InterruptedException {
        richiesta = richiestaBuilder.buildTipoRichiesta(TipoRichiesta.GET_ALL_USERS).buildMittente(utente).build();
        richiestaCallable = new RichiestaCallable(richiesta);

        future = executorService.submit(richiestaCallable);
        risposta = future.get();

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
