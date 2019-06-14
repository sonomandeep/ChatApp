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

/**
 * La classe {@code UtenteDao} impementa dei metodi, tra i quali anche i metodi CRUD, per interagire con la tabella
 * utenti del database, tutto questo seguendo il pattern DAO.
 */
public class UtenteDao implements Dao<Utente> {

    private PreparedStatement preparedStatement;
    private ResultSet rs;
    private List<Utente> utenti = new ArrayList<>();

    public UtenteDao() {
        createTable();
    }

    /**
     * Metodo che crea la tabella se non esiste, viene chiamato direttamente del costruttore della classe
     * per essere sicuri che venga creata la tabella.
     */
    private void createTable() {

        try {
            DatabaseMetaData dbm = Connect.getConnection().getMetaData();
            rs = dbm.getTables(null, null, "utenti", null);

            if (!rs.next()) {
                preparedStatement = Connect.getConnection().prepareStatement("CREATE TABLE utenti (\n" +
                        "    id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,\n" +
                        "    nome TEXT NOT NULL,\n" +
                        "    cognome TEXT NOT NULL,\n" +
                        "    username TEXT UNIQUE NOT NULL,\n" +
                        "    password TEXT NOT NULL\n" +
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

    /**
     * Metodo che aggiunge un utente al database.
     * @param utente {@link Utente} che deve essere aggiunto.
     */
    @Override
    public void add(Utente utente) {
        try {
            preparedStatement = Connect.getConnection().prepareStatement("INSERT INTO " +
                    "Utenti (nome, cognome, username, password) " +
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

    /**
     * Metodo che legge un utente identificandolo in base all'id.
     * @param id {@code id} per cercare l'utnete.
     * @return {@link Utente} letto.
     */
    @Override
    public Utente read(int id) {
        Utente utente;
        try {
            preparedStatement = Connect.getConnection().prepareStatement("SELECT " +
                    "id, nome, cognome, username, password " +
                    "FROM utenti " +
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

    /**
     * Metodo che legge tutti gli utenti presenti nel database.
     * @return {@link List<Utente>} Lista degli utenti.
     */
    @Override
    public List<Utente> readAll() {

        try {
            preparedStatement = Connect.getConnection().prepareStatement("SELECT " +
                    "id, nome, cognome, username, password " +
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

    /**
     * Metodo che effettua l'aggiornamento dei dati di un utente.
     * @param utente {@link Utente} che deve essere aggiornato.
     */
    @Override
    public void update(Utente utente) {
        try {
            preparedStatement = Connect.getConnection().prepareStatement("UPDATE utenti SET nome = ?, " +
                    "cognome = ?, username = ?, password = ? WHERE id = ?");
            preparedStatement.setString(1, utente.getNome());
            preparedStatement.setString(2, utente.getCognome());
            preparedStatement.setString(3, utente.getUsername());
            preparedStatement.setString(4, utente.getPassword());
            preparedStatement.setInt(5, utente.getID());
            preparedStatement.execute();
            preparedStatement.close();
        } catch (SQLException e){
            System.err.println("Errore durante l'aggiornamento dei dati di un utente: " + e.getMessage());
        }
    }

    /**
     * Metodo che effettua l'eliminazione di un utente dal database.
     * @param utente {@link Utente} che deve essere eliminato.
     */
    @Override
    public void delete(Utente utente) {
        try {
            preparedStatement = Connect.getConnection().prepareStatement("DELETE FROM utenti WHERE id = ?");
            preparedStatement.setInt(1, utente.getID());
            preparedStatement.close();
        } catch (SQLException e) {
            System.err.println("Errore durante l'eliminazione di un utente: " + e.getMessage());
        }
    }

    /**
     * Metodo che effettua il login dell'utente passato come parametro se le credenziali sono corrette.
     * @param utente {@link Utente} che deve essere loggato.
     * @return {@code 0} se va tutto a buon fine, {@code 1} se la password è sbagliata, {@code 2} credenziali sbagliate,
     *         {@code -1} errore.
     */
    public int login(Utente utente) {
        try {
            preparedStatement = Connect.getConnection().prepareStatement("SELECT id, nome, cognome, username, password " +
                    "FROM Utenti WHERE username = ? AND password = ?");
            preparedStatement.setString(1, utente.getUsername());
            preparedStatement.setString(2, utente.getPassword());
            rs = preparedStatement.executeQuery();

            if (rs.next()) {
                if (rs.getString("username").equals(utente.getUsername())) {
                    if (rs.getString("password").equals(utente.getPassword()))
                        return 0;
                    else
                        return 1;
                } else
                    return 2;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }
}
