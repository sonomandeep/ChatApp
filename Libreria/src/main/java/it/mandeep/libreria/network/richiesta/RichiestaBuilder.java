package it.mandeep.libreria.network.richiesta;

import it.mandeep.libreria.datastructures.Messaggio;
import it.mandeep.libreria.datastructures.Utente;

import java.io.Serializable;

public interface RichiestaBuilder extends Serializable {
    RichiestaBuilder buildTipoRichiesta(TipoRichiesta tipoRichiesta);
    RichiestaBuilder buildMessaggio(Messaggio messaggio);
    RichiestaBuilder buildMittente(Utente mittente);
    RichiestaBuilder buildDestinatario(Utente destinatario);
    Richiesta build();
}
