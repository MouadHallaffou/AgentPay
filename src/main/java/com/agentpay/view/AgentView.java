package main.java.com.agentpay.view;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;
import java.util.Scanner;

import main.java.com.agentpay.model.*;
import main.java.com.agentpay.model.enums.TypeAgent;
import main.java.com.agentpay.model.enums.TypePaiement;
import main.java.com.agentpay.service.interfaces.AgentService;

public class AgentView {
    private static final Scanner scanner = new Scanner(System.in);
    private final AgentService agentService;

    Paiement paiement = new Paiement();
    PaiementBonus paiementBonus = new PaiementBonus();
    PaiementIndemenite paiementIndemenite = new PaiementIndemenite();

    public AgentView(AgentService agentService) {
        this.agentService = agentService;
    }

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

        return agent;
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

    // CREER UN PAIEMENT
    public Paiement getCreatePaiementInput(TypePaiement typePaiement) {
        System.out.println("=== Create New Payment (" + typePaiement + ") ===");

        double montant = Double.parseDouble(getInput("Montant"));
        String dateStr = getInput("Date (YYYY-MM-DD HH:MM:SS)");
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd H:m:s");
        Date date;
        try {
            date = simpleDateFormat.parse(dateStr);
        } catch (ParseException e) {
            date = new Date();
            System.out.println("Invalid date format. la date enregistre est : " + date);
        }
        String motif = getInput("motif");
        int id = Integer.parseInt(getInput("user"));
        paiement.setMontant(montant);
        paiement.setDatePaiement(date);
        paiement.setTypePaiement(typePaiement);
        paiement.setMotif(motif);

        switch (typePaiement) {
            case BONUS:
                paiementBonus.setConditionvalide(false);
                paiementBonus.setPaiement(paiement);
                paiement.setPaiementBonus(paiementBonus);
                break;

            case INDEMNITE:
                paiementIndemenite.setConditionvalide(false);
                paiementIndemenite.setPaiement(paiement);
                paiement.setPaiementIndemenite(paiementIndemenite);
                break;
            default:
                break;
        }

        Optional<Agent> agent = agentService.getAgentById(id);
        agent.orElseThrow(() -> new RuntimeException("l'agent assigne n'existe pas!"));
        paiement.setAgent(agent.get());
        return paiement;
    }

    public Paiement getEditPaiementInput(TypePaiement typePaiement) {
        System.out.println("=== Update du paiement (" + typePaiement + ") ===");

        double montant = Double.parseDouble(getInput("Montant"));
        String dateStr = getInput("Date (YYYY-MM-DD HH:MM:SS)");
        String motif = getInput("Motif");
//        int agentId = Integer.parseInt(getInput("ID de l'agent"));

        Date date;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            date = sdf.parse(dateStr);
        } catch (ParseException e) {
            date = new Date();
            System.out.println("Format de date invalide. Date enregistre est : " + date);
        }

        Paiement paiement = new Paiement();
        paiement.setMontant(montant);
        paiement.setDatePaiement(date);
        paiement.setTypePaiement(typePaiement);
        paiement.setMotif(motif);

        switch (typePaiement) {
            case BONUS:
                paiementBonus.setConditionvalide(true);
                paiementBonus.setPaiement(paiement);
                paiement.setPaiementBonus(paiementBonus);
                break;

            case INDEMNITE:
                paiementIndemenite.setConditionvalide(true);
                paiementIndemenite.setPaiement(paiement);
                paiement.setPaiementIndemenite(paiementIndemenite);
                break;
            default:
                break;
        }

        return paiement;
    }

    public void displayAgent(Agent agent) {
        String status = agent.getIsActive() ? "active" : "susepnd";
        System.out.println("agent:" + agent.getFirstName().toUpperCase() + " " + agent.getLastName().toUpperCase() + " est:" + status + ", leur role: " + agent.getTypeAgent());
    }
}
