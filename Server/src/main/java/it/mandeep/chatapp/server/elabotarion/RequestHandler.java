package it.mandeep.chatapp.server.elabotarion;

import it.mandeep.chatapp.server.core.ClientHandler;
import it.mandeep.libreria.datastructures.Utente;
import it.mandeep.libreria.network.richiesta.Richiesta;
import it.mandeep.libreria.network.risposta.Risposta;

/**
 * La classe {@code RequestHandler} gestisce le richieste che arrivano dal client e elabora una risposta adeguata.
 */
public class RequestHandler {

    /**
     * Il metodo {@code handle} è l'unico metodo della classe {@link RequestHandler}, elabora una risposta
     * per la richiesta che deve gestire.
     * @param richiesta è la richhiesta che deve essere gestita
     * @param adress è l'indirizzo del client che invia la richiesta, server per far collegare direttamente i client
     *               connessi
     * @return Viene restituita una {@link Risposta} che sarà rimandata al client che ha effettuato la richiesta
     */
    public static Risposta handle(Richiesta richiesta, String adress) {

        Risposta risposta = new Risposta(0);

        switch (richiesta.getTipoRichiesta()) {
            case SIGNUP:
                //TODO: signup
                break;
            case LOGIN:
                //TODO: login
                ClientHandler.utentiOnline.put(richiesta.getMittente(), adress);
                System.out.println(String.format("%s ha effettuato il login.", richiesta.getMittente().getUsername()));
                risposta.setRisultatoRisposta(0);
                break;
            case SEND_MESSAGE:
                //TODO: send message
                //Cerco l'inrizzo del client che si è cercato e lo aggiungo alla risposta
                Utente destinatario = richiesta.getDestinatario();
                if (ClientHandler.utentiOnline.containsKey(destinatario)) {
                    risposta.setAdress(ClientHandler.utentiOnline.get(destinatario));
                    risposta.setRisultatoRisposta(0);
                    System.out.println(String.format("%s ha inviato un messaggio a %s.", richiesta.getMittente(), richiesta.getDestinatario()));
                }
                else
                    risposta.setRisultatoRisposta(1);
                break;
            case LOGOUT:
                //TODO: logout
                ClientHandler.utentiOnline.remove(richiesta.getMittente());
                System.out.println(String.format("%s ha effettuato il logout.", richiesta.getMittente().getUsername()));
                risposta.setRisultatoRisposta(0);
                break;
                default:
                    risposta.setRisultatoRisposta(-999);
                    break;

        }
        return risposta;
    }
}
