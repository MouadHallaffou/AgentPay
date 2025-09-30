package main.java.com.agentpay.view;

import main.java.com.agentpay.controller.ControllerHandler;
import main.java.com.agentpay.exceptions.DatabaseConnectionException;
import main.java.com.agentpay.repository.interfacesImp.AgentRepositoryImp;
import main.java.com.agentpay.service.AuthService;
import main.java.com.agentpay.config.ConfigConnection;

public class Console {

    public static void start() {
        try {
            ConfigConnection.getInstance();
            System.out.println("╔═══════════════════════════════════════════╗");
            System.out.println("║   Welcome to AgentPay Management System   ║");
            System.out.println("╚═══════════════════════════════════════════╝");
        } catch (DatabaseConnectionException e) {
            System.err.println("Database connection failed: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        }

        AuthService authService = new AuthService(new AgentRepositoryImp());
        ControllerHandler controllerHandler = new ControllerHandler(authService);
        Login loginView = new Login(controllerHandler);
        loginView.displayLogin();
    }
}
