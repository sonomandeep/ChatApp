package it.mandeep.libreria.network.richiesta;

import it.mandeep.libreria.datastructures.Messaggio;
import it.mandeep.libreria.datastructures.Utente;

/**
 * La classe {@code ConcreteRichiestaBuilder} si occupa di creare una {@link Richiesta} seguendo il Builder Pattern.
 */
public class ConcreteRichiestaBuilder implements RichiestaBuilder {

    private static final long serialVersionUID = 5483332788927841145L;
    private TipoRichiesta tipoRichiesta = null;
    private Messaggio messaggio = null;
    private Utente mittente = null;
    private Utente destinatario = null;

    @Override
    public RichiestaBuilder buildTipoRichiesta(TipoRichiesta tipoRichiesta) {
        this.tipoRichiesta = tipoRichiesta;
        return this;
    }

    public RichiestaBuilder buildMessaggio(Messaggio messaggio) {
        this.messaggio = messaggio;
        return this;
    }

    public RichiestaBuilder buildMittente(Utente mittente) {
        this.mittente = mittente;
        return this;
    }

    public RichiestaBuilder buildDestinatario(Utente destinatario) {
        this.destinatario = destinatario;
        return this;
    }

    public Richiesta build() {
        Richiesta richiesta = new Richiesta();
        richiesta.setDestinatario(destinatario);
        richiesta.setMittente(mittente);
        richiesta.setTipoRichiesta(tipoRichiesta);
        return richiesta;
    }
}
