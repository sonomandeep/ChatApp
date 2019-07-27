package it.mandeep.chatapp.server.elabotarion;

import it.mandeep.chatapp.server.core.Main;
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
     *
     * @param richiesta è la richhiesta che deve essere gestita
     * @param adress    è l'indirizzo del client che invia la richiesta, server per far collegare direttamente i client
     *                  connessi
     * @return Viene restituita una {@link Risposta} che sarà rimandata al client che ha effettuato la richiesta
     */
    public static Risposta handle(Richiesta richiesta, String adress) {

        Risposta risposta = new Risposta(0);
        Utente mittente;

        switch (richiesta.getTipoRichiesta()) {
            case SIGNUP:
                for (Utente u : Main.utenti) {
                    if (u.getUsername().equals(richiesta.getMittente().getUsername())) {
                        risposta.setRisultatoRisposta(1);
                        return risposta;
                    }
                }
                Main.utenteDao.add(richiesta.getMittente());
                Main.utenti = Main.utenteDao.readAll();
                System.out.println(String.format("L'utente %s è stato inserito.", richiesta.getMittente().getUsername()));
                risposta.setRisultatoRisposta(0);
                return risposta;

            /*
             * Una volta controllate le credenziali viene inviata una risposta al server, se il login avviene con
             * successo il risultato della risposta è 0, se la password non corrisponde allo username il risultato è 1,
             * in tutti gli altri casi il risultato è 2
             */
            case LOGIN:
                // Scorro la lista di tutti gli utenti presenti nel database
                for (Utente u : Main.utenti) {
                    // Controllo se lo username corrisponde a quello del mittente della richiesta
                    if (u.getUsername().equals(richiesta.getMittente().getUsername())) {
                        // Controllo se la password corrisponde a quello del mittente della richiesta
                        if (u.getPassword().equals(richiesta.getMittente().getPassword())) {
                            mittente = richiesta.getMittente();
                            mittente.setAdress(adress);
                            Main.utentiOnline.add(mittente);
                            System.out.println(String.format("%s ha effettuato il login.", richiesta.getMittente().getUsername()));
                            risposta.setRisultatoRisposta(0);
                            risposta.setUtente(mittente);
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
                if (!Main.utenteDao.delete(richiesta.getMittente()))
                    risposta.setRisultatoRisposta(0);
                else
                    risposta.setRisultatoRisposta(1);
                Main.utenti = Main.utenteDao.readAll();
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

            case GET_ALL_USERS:
                risposta.setUtenti(Main.utenti);
                risposta.setRisultatoRisposta(0);
                return risposta;

            case IS_ONLINE:
                for (Utente u : Main.utentiOnline) {
                    if (u.getUsername().equals(richiesta.getDestinatario().getUsername())) {
                        risposta.setUtente(u);
                        risposta.setRisultatoRisposta(0);
                        return risposta;
                    }
                }
                risposta.setRisultatoRisposta(1);
                return risposta;

            default:
                risposta.setRisultatoRisposta(-999);
                return risposta;

        }
        return risposta;
    }
}
