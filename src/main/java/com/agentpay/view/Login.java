package main.java.com.agentpay.view;

import main.java.com.agentpay.controller.AuthController;
import main.java.com.agentpay.model.Agent;
import java.util.Optional;
import java.util.Scanner;

public class Login {
    private final AuthController authController;

    public Login(AuthController authController) {
        this.authController = authController;
    }

    public void displayLogin() {
        System.out.println("╔═════════════════════════════╗");
        System.out.println("║           LOGIN             ║");
        System.out.println("╚═════════════════════════════╝");
        boolean loginSuccessful = false;
        while (!loginSuccessful) {
            Scanner scanner = new Scanner(System.in);
            System.out.print("Email: ");
            String email = scanner.nextLine().trim();
            System.out.print("Password: ");
            String password = scanner.nextLine().trim();

            if (email.isEmpty() || password.isEmpty()) {
                System.out.println("Email and password are required!");
                continue;
            }
            Optional<Agent> agentOpt = authController.authenticate(email, password);
            if (agentOpt.isPresent()) {
                loginSuccessful = true;
                String fullName = agentOpt.get().getFirstName() + " " + agentOpt.get().getLastName();
                System.out.println("╔═════════════════════════════════╗");
                System.out.println("     Welcome " + fullName + "        ");
                System.out.println("╚═════════════════════════════════╝");

                authController.handleMenu(agentOpt.get());
                scanner.close();
            }
        }
    }
}
