import config.ConfigConnection;
import controller.ControllerHandler;
import model.Agent;
import model.enums.TypeAgent;
import repository.interfacesImp.AgentRepositoryImp;
import service.AgentService;
import service.AuthService;
import view.Login;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

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

        AuthService authService = new AuthService(new AgentRepositoryImp());
        ControllerHandler controllerHandler = new ControllerHandler(authService);
        Login loginView = new Login(controllerHandler);
        loginView.displayLogin();
    }
}