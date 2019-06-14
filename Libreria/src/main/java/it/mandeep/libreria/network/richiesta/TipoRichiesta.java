package it.mandeep.libreria.network.richiesta;

import java.io.Serializable;

public enum TipoRichiesta implements Serializable {
    LOGIN,
    LOGOUT,
    SIGNUP,
    UPDATE_USER,
    DELETE_USER,
    SEND_MESSAGE
}
