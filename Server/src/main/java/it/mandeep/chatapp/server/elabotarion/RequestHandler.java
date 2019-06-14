package it.mandeep.chatapp.server.elabotarion;

import it.mandeep.chatapp.server.core.ClientHandler;
import it.mandeep.chatapp.server.core.Server;
import it.mandeep.chatapp.server.database.dao.UtenteDao;
import it.mandeep.libreria.datastructures.Utente;
import it.mandeep.libreria.network.richiesta.Richiesta;
import it.mandeep.libreria.network.risposta.Risposta;

import java.util.ArrayList;
import java.util.List;

/**
 * La classe {@code RequestHandler} gestisce le richieste che arrivano dal client e elabora una risposta adeguata.
 */
public class RequestHandler {

    private static UtenteDao utenteDao;
    static List<Utente> utenti;
    static List<Utente> utentiOnline;

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
        utenteDao = new UtenteDao();
        utenti = utenteDao.readAll();
        utentiOnline = new ArrayList<>();

        switch (richiesta.getTipoRichiesta()) {
            case SIGNUP:
                for (Utente u : RequestHandler.utenti) {
                    if (u.getUsername().equals(richiesta.getMittente().getUsername())) {
                        risposta.setRisultatoRisposta(1);
                        return risposta;
                    }
                }
                // RequestHandler.utenti.add(richiesta.getMittente());
                utenteDao.add(richiesta.getMittente());
                utenti = utenteDao.readAll();
                System.out.println(String.format("L'utente %s è stato inserito.", richiesta.getMittente().getUsername()));
                risposta.setRisultatoRisposta(0);
                return risposta;

            case LOGIN:
                richiesta.getMittente().setAdress(adress);
                RequestHandler.utentiOnline.add(richiesta.getMittente());
                System.out.println(String.format("%s ha effettuato il login.", richiesta.getMittente().getUsername()));
                risposta.setRisultatoRisposta(0);
                break;

            case SEND_MESSAGE:
                //Cerco l'inrizzo del client che si è cercato e lo aggiungo alla risposta
                Utente destinatario = richiesta.getDestinatario();

                for (Utente u : RequestHandler.utenti) {
                    if (u.getUsername().equals(destinatario.getUsername())) {
                        risposta.setAdress(u.getAdress());
                        risposta.setRisultatoRisposta(0);
                        return risposta;
                    }
                }
                risposta.setRisultatoRisposta(1);
                return risposta;

            case LOGOUT:
                RequestHandler.utentiOnline.remove(richiesta.getMittente());
                System.out.println(String.format("%s ha effettuato il logout.", richiesta.getMittente().getUsername()));
                risposta.setRisultatoRisposta(0);
                return risposta;

                default:
                    risposta.setRisultatoRisposta(-999);
                    return risposta;

        }
        return risposta;
    }
}
