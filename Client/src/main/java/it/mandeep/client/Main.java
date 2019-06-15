package it.mandeep.client;

import it.mandeep.client.chat.Chat;
import it.mandeep.client.networking.MessageThread;
import it.mandeep.client.networking.RequestThread;
import it.mandeep.libreria.datastructures.Messaggio;
import it.mandeep.libreria.datastructures.Utente;
import it.mandeep.libreria.network.richiesta.ConcreteRichiestaBuilder;
import it.mandeep.libreria.network.richiesta.Richiesta;
import it.mandeep.libreria.network.richiesta.RichiestaBuilder;
import it.mandeep.libreria.network.richiesta.TipoRichiesta;
import it.mandeep.libreria.network.risposta.Risposta;

public class Main {

    public static void main(String[] args) {
        Chat chat = new Chat();
        chat.start();

        Utente mittente = new Utente("Mandeep", "Singh", "Dio", "1234");
        Utente destinatario = new Utente("Utente", "Prova", "Utente", "1234");
        Messaggio messaggio = new Messaggio("Hello world!");

        // Creo la richiesta
        RichiestaBuilder richiestaBuilder = new ConcreteRichiestaBuilder();
        Richiesta richiesta = richiestaBuilder
                .buildTipoRichiesta(TipoRichiesta.SEND_MESSAGE)
                .buildMittente(mittente)
                .buildDestinatario(destinatario)
                .buildMessaggio(messaggio)
                .build();

        // Invio la richiesta al server aspettando l'indirizzo del client destinatario
        RequestThread requestThread = new RequestThread(richiesta);
        requestThread.start();
        try {
            // Aspetto che il thread della richiesta finisca
            requestThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // Ricevo la risposta dal server
        Risposta risposta = requestThread.getRisposta();
        if (risposta.getRisultatoRisposta() == 0) {
            MessageThread messageThread = new MessageThread(richiesta, risposta.getAdress());
            messageThread.start();
            try {
                messageThread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            risposta = messageThread.getRisposta();
        }
    }
}
