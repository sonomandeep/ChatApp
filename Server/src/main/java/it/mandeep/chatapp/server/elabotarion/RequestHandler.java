package it.mandeep.chatapp.server.elabotarion;

import it.mandeep.chatapp.server.core.Main;
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

    /**
     * Il metodo {@code handle} è l'unico metodo della classe {@link RequestHandler}, elabora una risposta
     * per la richiesta che deve gestire.
     *
     * @param richiesta è la richhiesta che deve essere gestita
     * @param adress    è l'indirizzo del client che invia la richiesta, server per far collegare direttamente i client
     *                  connessi
     * @return Viene restituita una {@link Risposta} che sarà rimandata al client che ha effettuato la richiesta
     */
    public static Risposta handle(Richiesta richiesta, String adress) {

        Risposta risposta = new Risposta(0);
        utenteDao = new UtenteDao();
        Main.utenti = utenteDao.readAll();

        switch (richiesta.getTipoRichiesta()) {
            case SIGNUP:
                for (Utente u : Main.utenti) {
                    if (u.getUsername().equals(richiesta.getMittente().getUsername())) {
                        risposta.setRisultatoRisposta(1);
                        return risposta;
                    }
                }
                utenteDao.add(richiesta.getMittente());
                Main.utenti = utenteDao.readAll();
                System.out.println(String.format("L'utente %s è stato inserito.", richiesta.getMittente().getUsername()));
                risposta.setRisultatoRisposta(0);
                return risposta;

            /*
             * Una volta controllate le credenziali viene inviata una risposta al server, se il login avviene con
             * successo il risultato della risposta è 0, se la password non corrisponde allo username il risultato è 1,
             * in tutti gli altri casi il risultato è 2
             */
            case LOGIN:
                for (Utente u : Main.utenti) {
                    if (u.getUsername().equals(richiesta.getMittente().getUsername())) {
                        if (u.getPassword().equals(richiesta.getMittente().getPassword())) {
                            // TODO: provare ad aggiungere l'indirizzo dal client stesso quando viene inviata la richiesta
                            richiesta.getMittente().setAdress(adress);
                            System.out.println(adress);
                            Main.utentiOnline.add(richiesta.getMittente());
                            System.out.println(String.format("%s ha effettuato il login.", richiesta.getMittente().getUsername()));
                            risposta.setRisultatoRisposta(0);
                            return risposta;
                        } else {
                            // Password non corrisponde allo username
                            risposta.setRisultatoRisposta(1);
                            return risposta;
                        }
                    }
                }

                risposta.setRisultatoRisposta(2);
                return risposta;

            case UPDATE_USER:
                break;

            case DELETE_USER:
                if (!utenteDao.delete(richiesta.getMittente()))
                    risposta.setRisultatoRisposta(0);
                else
                    risposta.setRisultatoRisposta(1);
                Main.utenti = utenteDao.readAll();
                System.out.println(String.format("L'utente %s è stato eliminato.", richiesta.getMittente().getUsername()));
                return risposta;

            /*
             * Per permettere al client di inviare un messaggio il server deve solo permettere la connessione tra i due
             * client perciò, una volta ricevuta la richiesta dal client, il server cerca tra gli utenti online
             * il destinatario del messaggio, se presente aggiunge il suo ip alla risposta e la invia al client mittente
             * con risultato 0, in tutti gli altri casi invia una risposta con risultato risposta 1.
             */
            case SEND_MESSAGE:
                //Cerco l'inrizzo del client che si è cercato e lo aggiungo alla risposta
                Utente destinatario = richiesta.getDestinatario();

                System.out.println(Main.utentiOnline.size());

                for (Utente u : Main.utentiOnline) {
                    if (u.getUsername().equals(destinatario.getUsername())) {
                        risposta.setAdress(u.getAdress());
                        risposta.setRisultatoRisposta(0);
                        return risposta;
                    }
                }
                risposta.setRisultatoRisposta(1);
                return risposta;

            case LOGOUT:
                Main.utentiOnline.remove(richiesta.getMittente());
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
