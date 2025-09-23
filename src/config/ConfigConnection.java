package config;

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

    private ConfigConnection() throws SQLException {
        Properties properties = new Properties();
        try (InputStream inputStream = new FileInputStream("application.properties");
        ){
           properties.load(inputStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        String DATABASE_URL = properties.getProperty("DB_URL");
        String USER = properties.getProperty("DB_USER");
        String DB_PASS = properties.getProperty("DB_PASSWORD");
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            this.connection = DriverManager.getConnection(DATABASE_URL,USER,DB_PASS);
        }catch (ClassNotFoundException e){
            System.out.println("Driver MYSQL JDBC non trouvé : " + e.getMessage());
            throw new SQLException(e);
        } catch (SQLException e) {
            System.out.println("Erreur de connexion à la base de données : " + e.getMessage());
            throw e;
        }
    }

    public Connection getConnection(){
        return connection;
    }

    public static ConfigConnection getInstance() throws SQLException {
        if (instance == null){
            instance = new ConfigConnection();
        } else if (instance.getConnection().isClosed()) {
            instance = new ConfigConnection();
        }
        return instance;
    }

    public static void closeConnection(){
        if (instance != null && instance.getConnection() != null){
            try {
                instance.getConnection().close();
                System.out.println("connexion ferme !");
            } catch (SQLException e) {
                System.out.println("erreur lors de la fermeture de connexion" + e.getMessage());
            }
        }
    }
}
