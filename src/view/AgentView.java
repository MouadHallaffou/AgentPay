package view;

import java.util.Scanner;

public class AgentView {
    private final Scanner scanner = new Scanner(System.in);

    public void displayMenu() {
        System.out.println("\n=== Agent Management ===");
        System.out.println("1. Register Agent");
        System.out.println("2. List Agents");
        System.out.println("3. Exit");
        System.out.print("Choice: ");
    }

    public String getInput(String message) {
        System.out.print(message + ": ");
        return scanner.nextLine();
    }

    public void showMessage(String message) {
        System.out.println(message);
    }
}
