package main.java.com.agentpay.view;

import main.java.com.agentpay.service.interfaces.DepartementService;

import java.util.Scanner;

public class DepartementView {
    private static final Scanner scanner = new Scanner(System.in);
    private final DepartementService departementService;

    public DepartementView(DepartementService departementService) {
        this.departementService = departementService;
    }

    public String getInput(String message) {
        System.out.print(message + ": ");
        return scanner.nextLine().trim();
    }

    public void showMessage(String message) {
        System.out.println(message);
    }
}
