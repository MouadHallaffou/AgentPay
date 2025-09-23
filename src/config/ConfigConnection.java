package config;

import java.sql.Connection;

public class DBConnection {
    private static DBConnection getInst;
    private Connection connection;

    private DBConnection(){

    }

}
