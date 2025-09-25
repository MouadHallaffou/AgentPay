package view;

import controller.AuthController;
import java.util.Scanner;

public class Login {
    private final AuthController authController;

    public Login(AuthController authController) {
        this.authController = authController;
    }

    public void displayLogin() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Email: ");
        String email = scanner.nextLine();
        System.out.print("Password: ");
        String password = scanner.nextLine();
        authController.authenticate(email, password);
    }
    
}
