package main.java.com.agentpay.config;

import main.java.com.agentpay.exceptions.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConfigConnection {
    private static ConfigConnection instance;
    private Connection connection;

    private ConfigConnection() throws DatabaseConnectionException {
        Properties properties = new Properties();
        try (InputStream inputStream = new FileInputStream("application.properties")) {
            properties.load(inputStream);
        } catch (IOException e) {
            throw new DatabaseConnectionException("Erreur lors du chargement du fichier de configuration", e);
        }

        String DATABASE_URL = properties.getProperty("DB_URL");
        String USER = properties.getProperty("DB_USER");
        String DB_PASS = properties.getProperty("DB_PASSWORD");
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            this.connection = DriverManager.getConnection(DATABASE_URL, USER, DB_PASS);
        } catch (ClassNotFoundException e) {
            throw new DatabaseConnectionException("Driver MYSQL JDBC non trouvé", e);
        } catch (SQLException e) {
            throw new DatabaseConnectionException("Erreur de connexion à la base de données", e);
        }
    }

    public static Connection getConnection() throws DatabaseConnectionException {
        if (instance == null) {
            getInstance();
        }
        return instance.connection;
    }

    public static ConfigConnection getInstance() throws DatabaseConnectionException {
        if (instance == null) {
            instance = new ConfigConnection();
        } else {
            try {
                if (instance.connection.isClosed()) {
                    instance = new ConfigConnection();
                }
            } catch (SQLException e) {
                throw new DatabaseConnectionException("Erreur lors de la vérification de l'état de la connexion", e);
            }
        }
        return instance;
    }

    public static void closeConnection() throws DatabaseConnectionException {
        if (instance != null && instance.connection != null) {
            try {
                instance.connection.close();
            } catch (SQLException e) {
                throw new DatabaseConnectionException("Erreur lors de la fermeture de la connexion", e);
            }
        }
    }
}
