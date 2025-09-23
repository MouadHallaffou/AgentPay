import config.ConfigConnection;
import controller.AuthController;
import repository.interfaces.AgentRepository;
import repository.interfacesImp.AgentRepositoryImp;

import java.sql.Connection;
import java.sql.SQLException;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        System.out.println("╔═══════════════════════════════════════════╗");
        System.out.println("║   Welcome to AgentPay Management System   ║");
        System.out.println("╚═══════════════════════════════════════════╝");

        try {
            Connection connection = ConfigConnection.getInstance().getConnection();
            System.out.println("Connected to the database successfully!");
        } catch (SQLException e) {
            System.err.println("Database connection failed: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("An error occurred: " + e.getMessage());
        }

        AgentRepositoryImp agentRepositoryImp = new AgentRepositoryImp();
        AuthController authController = new AuthController(new service.AuthService(agentRepositoryImp));
        authController.authenticate("","");

        

    }
}