package it.mandeep.chatapp.server.elabotarion;

import it.mandeep.chatapp.server.core.ClientHandler;
import it.mandeep.libreria.network.richiesta.Richiesta;
import it.mandeep.libreria.network.risposta.Risposta;

public class RequestHandler {

    private static Risposta risposta = new Risposta(0);

    public static Risposta handle(Richiesta richiesta, String adress) {
        switch (richiesta.getTipoRichiesta()) {
            case LOGIN:
                ClientHandler.utentiOnline.put(richiesta.getMittente(), adress);
                risposta.setRisultatoRisposta(0);
                break;
            case SEND_MESSAGE:
                break;
                default:
                    risposta.setRisultatoRisposta(-999);
                    break;

        }
        return risposta;
    }
}
