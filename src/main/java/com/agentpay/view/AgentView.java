package main.java.com.agentpay.view;

import java.util.Scanner;
import main.java.com.agentpay.model.Agent;
import main.java.com.agentpay.model.Departement;
import main.java.com.agentpay.model.enums.TypeAgent;

public class AgentView {
    private static final Scanner scanner = new Scanner(System.in);

    public String getInput(String message) {
        System.out.print(message + ": ");
        return scanner.nextLine().trim();
    }

    public void showMessage(String message) {
        System.out.println(message);
    }

    // CREER UN AGENT
    public Agent getAgentInput() {
        System.out.println("=== Create New Agent ===");

        String firstName = getInput("First Name");
        String lastName = getInput("Last Name");
        String email = getInput("Email");
        String password = generatePassword(firstName);

        System.out.println("Agent Type:");
        System.out.println("1. OUVRIER");
        System.out.println("2. RESPONSABLE");
        System.out.println("3. STAGIAIRE");
        System.out.print("Choose type (1-3): ");
        int typeChoice = Integer.parseInt(scanner.nextLine());

        TypeAgent typeAgent;
        switch (typeChoice) {
            case 1:
                typeAgent = TypeAgent.OUVRIER;
                break;
            case 2:
                typeAgent = TypeAgent.RESPONSABLE;
                break;
            case 3:
                typeAgent = TypeAgent.STAGIAIRE;
                break;
            default:
                typeAgent = TypeAgent.OUVRIER;
                break;
        }
        int departementID = Integer.parseInt(getInput("departementID"));
        Agent agent = new Agent();
        agent.setFirstName(firstName);
        agent.setLastName(lastName);
        agent.setEmail(email);
        agent.setPassword(password);
        agent.setTypeAgent(typeAgent);
        Departement departement = new Departement();
        departement.setDepartementID(departementID);
        agent.setDepartement(departement);

        // System.out.println("Création agent: " + agent.getFirstName() + " " +
        // agent.getLastName() + " "
        // + agent.getTypeAgent() + " depID=" +
        // agent.getDepartement().getDepartementID());

        return agent;
    }

    private String generatePassword(String firstName) {
        return firstName.toLowerCase() + "123";
    }

    public static int getChoice() {
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    // update agent
    public Agent getUpdateAgentInput(int userID) {
        System.out.println("=== Update Agent ===");
        String firstName = getInput("First Name");
        String lastName = getInput("Last Name");
        String email = getInput("Email");
        String password = getInput("Password");
        if (password.trim().equals(null)) {
            password = generatePassword(firstName);
        }

        System.out.println("Agent Type:");
        System.out.println("1. OUVRIER");
        System.out.println("2. RESPONSABLE");
        System.out.println("3. STAGIAIRE");
        System.out.print("Choose type (1-3): ");
        int typeChoice = Integer.parseInt(scanner.nextLine());

        TypeAgent typeAgent;
        switch (typeChoice) {
            case 1:
                typeAgent = TypeAgent.OUVRIER;
                break;
            case 2:
                typeAgent = TypeAgent.RESPONSABLE;
                break;
            case 3:
                typeAgent = TypeAgent.STAGIAIRE;
                break;
            default:
                typeAgent = TypeAgent.OUVRIER;
                break;
        }
        int departementID = Integer.parseInt(getInput("departementID"));
        Agent agent = new Agent();
        agent.setUserID(userID);
        agent.setFirstName(firstName);
        agent.setLastName(lastName);
        agent.setEmail(email);
        agent.setPassword(password);
        agent.setTypeAgent(typeAgent);
        Departement departement = new Departement();
        departement.setDepartementID(departementID);
        agent.setDepartement(departement);
        return agent;
    }

}
