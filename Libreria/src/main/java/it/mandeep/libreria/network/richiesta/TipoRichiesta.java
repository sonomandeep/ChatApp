package it.mandeep.libreria.network.richiesta;

import java.io.Serializable;

public enum TipoRichiesta implements Serializable {
    LOGIN,
    LOGOUT,
    SIGNUP,
    SEND_MESSAGE
}
