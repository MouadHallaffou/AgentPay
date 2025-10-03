package main.java.com.agentpay.view;

import main.java.com.agentpay.exceptions.DatabaseConnectionException;
import main.java.com.agentpay.config.AppConfig;
import main.java.com.agentpay.config.ConfigConnection;

public class Console {

    public static void start() {
        try {
            ConfigConnection.getInstance();
            System.out.println("╔═════════════════════════════╗");
            System.out.println("║    Welcome to AgentPay MS   ║");
            System.out.println("╚═════════════════════════════╝");
        } catch (DatabaseConnectionException e) {
            System.err.println("Database connection failed: " + e.getMessage());
            return;
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
            return;
        }

        AppConfig appConfig = new AppConfig();

        Login loginView = new Login(appConfig.getAuthController());
        loginView.displayLogin();
    }
}