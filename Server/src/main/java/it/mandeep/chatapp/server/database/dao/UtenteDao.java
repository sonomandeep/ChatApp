package it.mandeep.chatapp.server.database.dao;

import it.mandeep.chatapp.server.database.Connect;
import it.mandeep.libreria.database.Dao;
import it.mandeep.libreria.datastructures.Utente;

import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UtenteDao implements Dao<Utente> {

    private PreparedStatement preparedStatement;
    private ResultSet rs;
    private List<Utente> utenti = new ArrayList<>();

    public UtenteDao() {
        createTable();
    }

    @Override
    public void createTable() {

        try {
            DatabaseMetaData dbm = Connect.getConnection().getMetaData();
            rs = dbm.getTables(null, null, "utenti", null);

            if (!rs.next()) {
                preparedStatement = Connect.getConnection().prepareStatement("CREATE TABLE utenti (\n" +
                        "    id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,\n" +
                        "    nome TEXT NOT NULL,\n" +
                        "    cognome TEXT NOT NULL,\n" +
                        "    username TEXT UNIQUE NOT NULL,\n" +
                        "    paswrod TEXT NOT NULL\n" +
                        ");");
                preparedStatement.execute();
                preparedStatement.close();
                rs.close();
            } else
                System.out.println("La tabella utenti esiste già.");

        } catch (SQLException e) {
            System.err.println("Errore durante la creazione della tabella utenti. Errore: " + e.getMessage() + ".");
        }
    }

    @Override
    public void add(Utente utente) {
        try {
            preparedStatement = Connect.getConnection().prepareStatement("INSERT INTO \n" +
                    "Utenti (nome, cognome, username, password) \n" +
                    "VALUES (?, ?, ?, ?)");
            preparedStatement.setString(1, utente.getNome());
            preparedStatement.setString(2, utente.getCognome());
            preparedStatement.setString(3, utente.getUsername());
            preparedStatement.setString(4, utente.getPassword());

            preparedStatement.execute();
            preparedStatement.close();

            System.out.println("Utente aggiunto.");

        } catch (SQLException e) {
            System.err.println("Errore durante l'aggiunta di un utente: " + e.getMessage() + ".");
        }
    }

    @Override
    public Utente read(int id) {
        Utente utente;
        try {
            preparedStatement = Connect.getConnection().prepareStatement("SELECT \n" +
                    "id, nome, cognome, username, password \n" +
                    "FROM utenti \n" +
                    "WHERE id = ?");
            preparedStatement.setInt(1, id);
            rs = preparedStatement.executeQuery();
            utente = new Utente(id, rs.getString("nome"), rs.getString("cognome"),
                    rs.getString("username"), rs.getString("password"));

            preparedStatement.close();
            rs.close();
            return utente;
        } catch (SQLException e) {
            System.err.println("Errore durante la lettura di un utente: " + e.getMessage() + ".");
        }
        return null;
    }

    @Override
    public List<Utente> readAll() {

        try {
            preparedStatement = Connect.getConnection().prepareStatement("SELECT \n" +
                    "id, nome, cognome, username, password \n" +
                    "FROM utenti");
            rs = preparedStatement.executeQuery();

            while (rs.next()) {
                utenti.add(new Utente(rs.getInt("id"), rs.getString("nome"),
                        rs.getString("cognome"), rs.getString("username"),
                        rs.getString("password")));
            }

            preparedStatement.close();
            rs.close();
            return utenti;
        } catch (SQLException e) {
            System.err.println("Errore durante la lettura degli utenti: " + e.getMessage() + ".");
        }
        return null;
    }

    @Override
    public void update(Utente utente) {

    }

    @Override
    public void delete(Utente utente) {

    }

    public int login(Utente utente) {
        try {
            preparedStatement = Connect.getConnection().prepareStatement("SELECT id, nome, cognome, username, password \n" +
                    "FROM Utenti WHERE username = ? AND password = ?");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return 0;
    }
}
