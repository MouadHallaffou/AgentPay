package controller;

import java.util.Optional;
import java.util.Scanner;

import model.Agent;
import service.AuthService;

public class AuthController {
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    public Optional<Agent> authenticate(String email, String password) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Email: ");
        email = scanner.nextLine();
        System.out.print("Password: ");
        password = scanner.nextLine();

        Optional<Agent> agentOpt = authService.getLogedAgent(email,password);
        if (agentOpt.isPresent()){
            System.out.println("conexion reussit!");
            return agentOpt;
        }else {
            System.out.println("email ou mot de pass incorect!");
            return Optional.empty();
        }
    }

}
