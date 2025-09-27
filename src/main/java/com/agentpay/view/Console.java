package main.java.com.agentpay.view;

import java.sql.Connection;
import java.sql.SQLException;

import main.java.com.agentpay.config.ConfigConnection;
import main.java.com.agentpay.controller.ControllerHandler;
import main.java.com.agentpay.repository.interfacesImp.AgentRepositoryImp;
import main.java.com.agentpay.service.AuthService;

public class Console {

    public static void start() {
        try {
            ConfigConnection.getInstance();
            System.out.println("╔═══════════════════════════════════════════╗");
            System.out.println("║   Welcome to AgentPay Management System   ║");
            System.out.println("╚═══════════════════════════════════════════╝");
        } catch (SQLException e) {
            System.out.println("Database connection failed: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        }

        AuthService authService = new AuthService(new AgentRepositoryImp());
        ControllerHandler controllerHandler = new ControllerHandler(authService);
        Login loginView = new Login(controllerHandler);
        loginView.displayLogin();
    }
}
