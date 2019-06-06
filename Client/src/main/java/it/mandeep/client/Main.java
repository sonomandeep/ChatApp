package it.mandeep.client;

import it.mandeep.client.networking.RequestThread;
import it.mandeep.libreria.datastructures.Utente;
import it.mandeep.libreria.network.richiesta.ConcreteRichiestaBuilder;
import it.mandeep.libreria.network.richiesta.Richiesta;
import it.mandeep.libreria.network.richiesta.TipoRichiesta;
import it.mandeep.libreria.network.risposta.Risposta;

public class Main {

    public static void main(String[] args) {

        Utente io = new Utente("Mandeep", "Singh", "SonoMandeep", "1234");
        ConcreteRichiestaBuilder richiestaBuilder = new ConcreteRichiestaBuilder();
        Richiesta richiesta = richiestaBuilder.builTipoRichiesta(TipoRichiesta.LOGIN).buildMittente(io).build();

        // Invio di una richiesta al server
        RequestThread requestThread = new RequestThread(richiesta);
        requestThread.start();
        try {
            requestThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // Ricezione di una risposta
        Risposta risposta = requestThread.getRisposta();
        System.out.println(risposta.getRisultatoRisposta());

    }

}
