import it.mandeep.client.networking.RequestThread;
import it.mandeep.libreria.datastructures.Utente;
import it.mandeep.libreria.network.richiesta.ConcreteRichiestaBuilder;
import it.mandeep.libreria.network.richiesta.Richiesta;
import it.mandeep.libreria.network.richiesta.TipoRichiesta;
import it.mandeep.libreria.network.risposta.Risposta;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MainTest {

    Utente io = new Utente("Mandeep", "Singh", "SonoMandeep", "1234");
    ConcreteRichiestaBuilder richiestaBuilder = new ConcreteRichiestaBuilder();
    Richiesta richiesta;
    RequestThread requestThread;
    Risposta risposta;

    @Test
    void loginTest() {
        richiesta = richiestaBuilder.builTipoRichiesta(TipoRichiesta.LOGIN).buildMittente(io).build();

        // Invio di una richiesta al server
        requestThread = new RequestThread(richiesta);
        requestThread.start();
        try {
            requestThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // Ricezione di una risposta
        risposta = requestThread.getRisposta();
        assertEquals(risposta.getRisultatoRisposta(), 0);
    }

    /*@Test
    void sendMessageTest() {
        richiesta = richiestaBuilder.builTipoRichiesta(TipoRichiesta.LOGOUT).buildMittente(io).buildDestinatario(io).build();
        requestThread = new RequestThread(richiesta);
        requestThread.start();
        try {
            requestThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        risposta = requestThread.getRisposta();
        assertEquals(risposta.getRisultatoRisposta(), 0);
    }*/

    @Test
    void logoutTest() {
        richiesta = richiestaBuilder.builTipoRichiesta(TipoRichiesta.SEND_MESSAGE).buildMittente(io).build();
        requestThread = new RequestThread(richiesta);
        requestThread.start();
        try {
            requestThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        risposta = requestThread.getRisposta();
        assertEquals(risposta.getRisultatoRisposta(), 0);
    }

}
