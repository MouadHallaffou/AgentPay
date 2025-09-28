package main.java.com.agentpay.controller;

import java.util.Optional;
import main.java.com.agentpay.model.Agent;
import main.java.com.agentpay.service.AuthService;
import main.java.com.agentpay.service.AgentService;
import main.java.com.agentpay.view.Menus;
import main.java.com.agentpay.view.AgentView;
import main.java.com.agentpay.view.MenuHandler;
import main.java.com.agentpay.repository.interfacesImp.AgentRepositoryImp;
import main.java.com.agentpay.repository.interfacesImp.DepartementRepositoryImp;
import main.java.com.agentpay.service.DepartementService;
import main.java.com.agentpay.utils.Validation;

public class ControllerHandler {
    private final AuthService authService;
    private final AgentService agentService;
    private final AgentView agentView;
    private final DepartementService departementService;
    private final MenuHandler menuHandler;

    public ControllerHandler(AuthService authService) {
        this.authService = authService;
        this.agentService = new AgentService(new AgentRepositoryImp());
        this.agentView = new AgentView();
        this.departementService = new DepartementService(new DepartementRepositoryImp());
        this.menuHandler = new MenuHandler(this);
    }

    // AUTHENTIFICATION
    public Optional<Agent> authenticate(String email, String password) {
        Optional<Agent> agentOpt = authService.getLogedAgent(email, password);
        if (agentOpt.isPresent()) {
            // System.out.println("Connexion réussie!");
            return agentOpt;
        } else {
            System.out.println("Email ou mot de passe incorrect!");
            return Optional.empty();
        }
    }

    // GÉRER LE MENU APRÈS LOGIN
    public void handleMenu(Agent agent) {
        boolean running = true;
        while (running) {
            displayMenuForRole(agent);
            int choice = agentView.getChoice();
            running = processChoice(agent, choice);
        }
        System.out.println("Au revoir!");
    }

    // AFFICHER LE MENU SELON LE RÔLE
    private void displayMenuForRole(Agent agent) {
        switch (agent.getTypeAgent()) {
            case DIRECTEUR:
                Menus.afficherMenuDirecteur();
                break;
            case RESPONSABLE:
                Menus.afficherMenuResponsable();
                break;
            case OUVRIER:
            case STAGIAIRE:
                Menus.afficherMenuAgent();
                break;
            default:
                System.out.println("Type d'agent inconnu!");
                break;
        }
        System.out.print("Votre choix: ");
    }

    // TRAITER LE CHOIX SELON LE RÔLE
    private boolean processChoice(Agent agent, int choice) {
        return switch (agent.getTypeAgent()) {
            case DIRECTEUR -> menuHandler.handleDirecteurChoice(choice);
            case RESPONSABLE -> menuHandler.handleResponsableChoice(choice);
            case OUVRIER, STAGIAIRE -> menuHandler.handleAgentChoice(choice);
            default -> {
                System.out.println("Type d'agent non reconnu!");
                yield false;
            }
        };
    }

    // MÉTHODES DÉPARTEMENTS (Directeur)
    public void handleCreateDepartement() {
        try {
            String nomDepartement = agentView.getInput("Nom du département");
            if (!Validation.isValideName(nomDepartement)) {
                agentView.showMessage("Le nom du département ne peut pas être vide!");
                return;
            }
            boolean success = departementService.createDepartement(nomDepartement);
            if (success) {
                agentView.showMessage("Département '" + nomDepartement + "' créé avec succès!");
            } else {
                agentView.showMessage("Erreur lors de la création du département.");
            }
        } catch (Exception e) {
            agentView.showMessage("Erreur: " + e.getMessage());
        }
    }

    public void handleUpdateDepartement() {
        agentView.showMessage("Fonctionnalité en développement");
    }

    public void handleDeleteDepartement() {
        agentView.showMessage("Fonctionnalité en développement");
    }

    public void handleViewAllDepartements() {
        agentView.showMessage("Fonctionnalité en développement");
    }

    // MÉTHODES RESPONSABLES (Directeur)
    public void handleCreateResponsable() {
        agentView.showMessage("Fonctionnalité en développement");
    }

    public void handleUpdateResponsable() {
        agentView.showMessage("Fonctionnalité en développement");
    }

    public void handleDeleteResponsable() {
        agentView.showMessage("Fonctionnalité en développement");
    }

    public void handleViewAllResponsables() {
        agentView.showMessage("Fonctionnalité en développement");
    }

    public void handleManageResponsablePayments() {
        agentView.showMessage("Fonctionnalité en développement");
    }

    // MÉTHODES CONSULTATION AGENTS (Directeur)
    public void handleViewAllAgents() {
        agentView.showMessage("Fonctionnalité en développement");
    }

    public void handleSearchAgentById() {
        agentView.showMessage("Fonctionnalité en développement");
    }

    public void handleFilterAgentsByDepartment() {
        agentView.showMessage("Fonctionnalité en développement");
    }

    public void handleFilterAgentsByRole() {
        agentView.showMessage("Fonctionnalité en développement");
    }

    // MÉTHODES STATISTIQUES DIRECTEUR
    public void handleAgentsCountByRole() {
        agentView.showMessage("Fonctionnalité en développement");
    }

    public void handleTotalPaymentsByDepartment() {
        agentView.showMessage("Fonctionnalité en développement");
    }

    public void handleAveragePaymentsByType() {
        agentView.showMessage("Fonctionnalité en développement");
    }

    public void handlePaymentHistory() {
        agentView.showMessage("Fonctionnalité en développement");
    }

    public void handleIdentifyUnusualPayments() {
        agentView.showMessage("Fonctionnalité en développement");
    }

    // MÉTHODES AGENTS (Responsable)
    public void handleCreateAgent() {
        try {
            Agent newAgent = agentView.getAgentInput();
            boolean success = agentService.createAgent(newAgent);
            if (success) {
                agentView.showMessage("Agent créé avec succès!");
            } else {
                agentView.showMessage("Erreur lors de la création de l'agent.");
            }
        } catch (Exception e) {
            agentView.showMessage("Erreur: " + e.getMessage());
        }
    }

    public void handleUpdateAgent() {
        agentView.showMessage("Fonctionnalité en développement");
    }

    public void handleDeleteAgent() {
        agentView.showMessage("Fonctionnalité en développement");
    }

    public void handleViewDepartmentAgents() {
        agentView.showMessage("Fonctionnalité en développement");
    }

    public void handleSearchAgent() {
        agentView.showMessage("Fonctionnalité en développement");
    }

    // MÉTHODES PAIEMENTS (Responsable)
    public void handleAddSalary() {
        agentView.showMessage("Fonctionnalité en développement");
    }

    public void handleAddPrime() {
        agentView.showMessage("Fonctionnalité en développement");
    }

    public void handleAddBonus() {
        agentView.showMessage("Fonctionnalité en développement");
    }

    public void handleAddIndemnity() {
        agentView.showMessage("Fonctionnalité en développement");
    }

    public void handleViewAgentPayments() {
        agentView.showMessage("Fonctionnalité en développement");
    }

    public void handleFilterPayments() {
        agentView.showMessage("Fonctionnalité en développement");
    }

    // MÉTHODES STATISTIQUES RESPONSABLE
    public void handleDepartmentAgentsCount() {
        agentView.showMessage("Fonctionnalité en développement");
    }

    public void handleDepartmentTotalPayments() {
        agentView.showMessage("Fonctionnalité en développement");
    }

    public void handleAveragePaymentsPerAgent() {
        agentView.showMessage("Fonctionnalité en développement");
    }

    public void handleDepartmentPaymentHistory() {
        agentView.showMessage("Fonctionnalité en développement");
    }

    // MÉTHODES AGENT SIMPLE
    public void handleViewAgentInfo() {
        agentView.showMessage("Fonctionnalité en développement");
    }

    public void handleViewPaymentHistory() {
        agentView.showMessage("Fonctionnalité en développement");
    }

    public void handleViewTotalPayments() {
        agentView.showMessage("Fonctionnalité en développement");
    }

    // MÉTHODES FILTRAGE PAIEMENTS AGENT
    public void handleFilterPaymentsByType() {
        agentView.showMessage("Fonctionnalité en développement");
    }

    public void handleFilterPaymentsByAmount() {
        agentView.showMessage("Fonctionnalité en développement");
    }

    public void handleFilterPaymentsByDate() {
        agentView.showMessage("Fonctionnalité en développement");
    }

}
