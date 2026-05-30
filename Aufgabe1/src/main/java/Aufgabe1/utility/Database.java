package Aufgabe1.utility;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Kapselt den Aufbau der JDBC-Verbindung. Die Verbindungsparameter werden aus einer
 * Property-Datei gelesen (kein Hardcoding von Treiber/URL/Login), damit ein
 * Datenbankwechsel allein über die Property-Datei möglich ist.
 *
 * Erwartete Schlüssel: db.driver, db.url, db.user, db.password.
 */
public class Database {

    private final Properties props;

    public Database(Properties props) {
        this.props = props;
    }

    /** Lädt die Properties aus einer Datei im Dateisystem. */
    public static Database fromFile(Path propertiesPath) throws IOException {
        Properties props = new Properties();
        try (InputStream in = Files.newInputStream(propertiesPath)) {
            props.load(in);
        }
        return new Database(props);
    }

    /**
     * Öffnet eine neue Verbindung. Der Aufrufer ist für das Schließen verantwortlich
     * (try-with-resources). Der Treiber wird über den Classpath automatisch geladen
     * (JDBC 4+), die explizite Class.forName-Auflösung dient nur als klare Fehlermeldung.
     */
    public Connection getConnection() throws SQLException {
        String driver = props.getProperty("db.driver");
        String url = props.getProperty("db.url");
        String user = props.getProperty("db.user");
        String password = props.getProperty("db.password");

        if (url == null || url.isBlank()) {
            throw new SQLException("db.url fehlt in der Property-Datei");
        }
        if (driver != null && !driver.isBlank()) {
            try {
                Class.forName(driver);
            } catch (ClassNotFoundException e) {
                throw new SQLException("JDBC-Treiber nicht gefunden: " + driver, e);
            }
        }
        return DriverManager.getConnection(url, user, password);
    }
}
