package it.mandeep.libreria.datastructures;

import java.io.Serializable;
import java.util.Objects;

/**
 * La classe {@code Utente} rappresenta l'utilizzatore del programma.
 */
public class Utente implements Serializable {

    private static final long serialVersionUID = 1163020545145091393L;
    private int ID;
    private String nome;
    private String cognome;
    private String username;
    private String password;
    private String adress;
    private boolean online;

    public Utente(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public Utente(String nome, String cognome, String username, String password) {
        this.nome = nome;
        this.cognome = cognome;
        this.username = username;
        this.password = password;
    }

    public Utente(int ID, String nome, String cognome, String username, String password) {
        this.ID = ID;
        this.nome = nome;
        this.cognome = cognome;
        this.username = username;
        this.password = password;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public boolean isOnline() {
        return online;
    }

    public void setOnline(boolean online) {
        this.online = online;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Utente)) return false;
        Utente utente = (Utente) o;
        return getID() == utente.getID() &&
                isOnline() == utente.isOnline() &&
                Objects.equals(getNome(), utente.getNome()) &&
                Objects.equals(getCognome(), utente.getCognome()) &&
                Objects.equals(getUsername(), utente.getUsername()) &&
                Objects.equals(getPassword(), utente.getPassword()) &&
                Objects.equals(getAdress(), utente.getAdress());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getID(), getNome(), getCognome(), getUsername(), getPassword(), getAdress(), isOnline());
    }

    @Override
    public String toString() {
        return "Utente{" +
                "ID=" + ID +
                ", nome='" + nome + '\'' +
                ", cognome='" + cognome + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", adress='" + adress + '\'' +
                ", online=" + online +
                '}';
    }
}
