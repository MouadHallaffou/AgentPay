package main.java.com.agentpay.view;

import main.java.com.agentpay.controller.ControllerHandler;
import java.util.Scanner;

public class Login {
    private final ControllerHandler authController;

    public Login(ControllerHandler authController) {
        this.authController = authController;
    }

    public void displayLogin() {
        System.out.println("╔═════════════════════════════╗");
        System.out.println("║           LOGIN             ║");
        System.out.println("╚═════════════════════════════╝");
        Scanner scanner = new Scanner(System.in);
        System.out.print("Email: ");
        String email = scanner.nextLine();
        System.out.print("Password: ");
        String password = scanner.nextLine();
        authController.authenticate(email, password);
    }
    
}
