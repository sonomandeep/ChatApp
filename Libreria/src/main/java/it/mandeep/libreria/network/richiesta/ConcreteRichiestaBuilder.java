package it.mandeep.libreria.network.richiesta;

import it.mandeep.libreria.datastructures.Messaggio;
import it.mandeep.libreria.datastructures.Utente;

public class ConcreteRichiestaBuilder implements RichiestaBuilder {

    private Richiesta richiesta;

    public RichiestaBuilder buildMessaggio(Messaggio messaggio) {
        richiesta.setMessaggio(messaggio);
        return this;
    }

    public RichiestaBuilder buildMittente(Utente mittente) {
        richiesta.setMittente(mittente);
        return this;
    }

    public RichiestaBuilder buildDestinatario(Utente destinatario) {
        richiesta.setDestinatario(destinatario);
        return this;
    }

    public Richiesta build(TipoRichiesta tipoRichiesta) {
        return richiesta;
    }
}
