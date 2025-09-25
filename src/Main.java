import config.ConfigConnection;
import controller.ControllerHandler;
import model.Agent;
import model.enums.TypeAgent;
import repository.interfacesImp.AgentRepositoryImp;
import service.AuthService;

import java.sql.Connection;
import java.sql.SQLException;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
//        System.out.println("╔═══════════════════════════════════════════╗");
//        System.out.println("║   Welcome to AgentPay Management System   ║");
//        System.out.println("╚═══════════════════════════════════════════╝");

        try {
            ConfigConnection.getInstance();
            Connection connection = ConfigConnection.getConnection();
            System.out.println("Connected to the database successfully!");
        } catch (SQLException e) {
            System.err.println("Database connection failed: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("An error occurred: " + e.getMessage());
        }

        try {
            // 1. Créer un agent responsable pour tester
            Agent responsable = new Agent();
            responsable.setFirstName("Manager");
            responsable.setLastName("Test");
            responsable.setEmail("manager@test.com");
            responsable.setPassword("password");
            responsable.setTypeAgent(TypeAgent.RESPONSABLE);

            // 2. Initialiser le controller
            AuthService authService = new AuthService(new AgentRepositoryImp());
            ControllerHandler controller = new ControllerHandler(authService);

            // 3. Simuler la connexion du responsable et accéder au menu
            System.out.println("=== TEST DE LA FONCTION CREATE AGENT ===");
            System.out.println("Simuler la connexion d'un responsable...");

            // Le responsable se connecte et accède au menu
            controller.handleMenu(responsable);

        } catch (Exception e) {
            System.err.println("Erreur lors du test: " + e.getMessage());
            e.printStackTrace();
        }

    }
}