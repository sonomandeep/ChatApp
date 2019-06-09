package it.mandeep.chatapp.server.database;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connect {

    private static File file = new File("database");
    private static final String DB_PATH = "jdbc:sqlite:database/database.db";
    private static Connection connection = null;

    private Connect() {}

    public static Connection getConnection() throws SQLException {
        if (file.mkdir())
            System.out.println("Cartella database creata.");
        if (connection == null)
            connection = DriverManager.getConnection(DB_PATH);
        return connection;
    }
}
