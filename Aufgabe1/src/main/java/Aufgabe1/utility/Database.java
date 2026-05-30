package Aufgabe1.utility;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

// JDBC-Verbindung aus einer Property-Datei (kein Hardcoding von Treiber/URL/Login)
public class Database {

    private final Properties props;

    public Database(Properties props) {
        this.props = props;
    }

    public static Database fromFile(Path propertiesPath) throws IOException {
        Properties props = new Properties();
        try (InputStream in = Files.newInputStream(propertiesPath)) {
            props.load(in);
        }
        return new Database(props);
    }

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
