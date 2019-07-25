package it.mandeep.client.chat;

import it.mandeep.client.networking.MessageThread;
import it.mandeep.client.networking.RequestThread;
import it.mandeep.libreria.datastructures.Messaggio;
import it.mandeep.libreria.datastructures.Utente;
import it.mandeep.libreria.network.richiesta.ConcreteRichiestaBuilder;
import it.mandeep.libreria.network.richiesta.Richiesta;
import it.mandeep.libreria.network.richiesta.RichiestaBuilder;
import it.mandeep.libreria.network.richiesta.TipoRichiesta;
import it.mandeep.libreria.network.risposta.Risposta;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SendMessageTest {

    // Invio di un messaggio:
    // 1. L'utente manda una richiesta al server per sapere l'inrizzo del destinatario
    // 2. Il server risponde
    // 3. L'utente si connette con l'indirizzo appena ricevuto ed invia il messaggio

    Utente mittente = new Utente("Mandeep", "Singh", "Utente", "1234");
    Utente destinatario = new Utente("Utente", "Prova", "Utente", "1234");
    Messaggio messaggio = new Messaggio("Hello world!");

    @Test
    void sendMessage() {
        RichiestaBuilder richiestaBuilder = new ConcreteRichiestaBuilder();
        Richiesta richiesta = richiestaBuilder
                .buildTipoRichiesta(TipoRichiesta.SEND_MESSAGE)
                .buildMittente(mittente)
                .buildDestinatario(destinatario)
                .buildMessaggio(messaggio)
                .build();

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
        System.out.println(risposta.getAdress());
        /*if (risposta.getRisultatoRisposta() == 0) {
            MessageThread messageThread = new MessageThread(richiesta, risposta.getAdress());
            messageThread.start();
            try {
                messageThread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            risposta = messageThread.getRisposta();
        }
         assertEquals(risposta.getRisultatoRisposta(), 0);*/
    }

}
