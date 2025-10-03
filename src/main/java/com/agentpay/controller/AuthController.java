package main.java.com.agentpay.controller;

import java.util.Optional;
import main.java.com.agentpay.model.Agent;
import main.java.com.agentpay.service.AuthServiceImpl;
import main.java.com.agentpay.view.AgentView;
import main.java.com.agentpay.view.Menu;

public class AuthController {

    private final AuthServiceImpl authService;
    private final AgentController agentController;
    private final DepartementController departementController;
    private final PaiementController paiementController;

    public AuthController(AuthServiceImpl authService, AgentController agentController,
            DepartementController departementController, PaiementController paiementController) {
        this.authService = authService;
        this.agentController = agentController;
        this.departementController = departementController;
        this.paiementController = paiementController;
    }

    public Optional<Agent> authenticate(String email, String password) {
        Optional<Agent> agentOpt = authService.getLogedAgent(email, password);
        if (agentOpt.isPresent()) {
            return agentOpt;
        } else {
            System.out.println("Email ou mot de passe incorrect!");
            return Optional.empty();
        }
    }

    public void handleMenu(Agent agent) {
        boolean running = true;
        while (running) {
            displayMenuForRole(agent);
            int choice = AgentView.getChoice();
            running = processChoice(agent, choice);
        }
        System.out.println("Au revoir!");
    }

    private void displayMenuForRole(Agent agent) {
        switch (agent.getTypeAgent()) {
            case DIRECTEUR:
                Menu.afficherMenuDirecteur();
                break;
            case RESPONSABLE:
                Menu.afficherMenuResponsable();
                break;
            case OUVRIER:
            case STAGIAIRE:
                Menu.afficherMenuAgent();
                break;
            default:
                System.out.println("Type d'agent inconnu!");
                break;
        }
        System.out.print("Votre choix: ");
    }

    private boolean processChoice(Agent agent, int choice) {
        switch (agent.getTypeAgent()) {
            case DIRECTEUR:
                return handleDirecteurChoice(choice);
            case RESPONSABLE:
                return handleResponsableChoice(choice);
            case OUVRIER:
            case STAGIAIRE:
                return handleAgentChoice(choice);
            default:
                System.out.println("Type d'agent non reconnu!");
                return false;
        }
    }

    // Traitement des choix du directeur
    private boolean handleDirecteurChoice(int choice) {
        switch (choice) {
            case 1:
                return handleGestionDepartement();
            case 2:
                return handleGestionResponsable();
            case 3:
                return handleConsultationAgents();
            case 4:
                return handleStatistiquesDirecteur();
            case 0:
                Menu.logout();
                return false;
            default:
                Menu.invalidChoice();
                return true;
        }
    }

    // Traitement des choix du responsable
    private boolean handleResponsableChoice(int choice) {
        switch (choice) {
            case 1:
                return handleGestionAgentsResponsable();
            case 2:
                return handleGestionPaiementsResponsable();
            case 3:
                return handleStatistiquesResponsable();
            case 0:
                Menu.logout();
                return false;
            default:
                Menu.invalidChoice();
                return true;
        }
    }

    // Traitement des choix de l'agent
    private boolean handleAgentChoice(int choice) {
        switch (choice) {
            case 1:
                agentController.handleViewAgentInfo();
                return true;
            case 2:
                agentController.handleViewPaymentHistory();
                return true;
            case 3:
                return handleFiltragePaiementsAgent();
            case 4:
                agentController.handleViewTotalPayments();
                return true;
            case 0:
                Menu.logout();
                return false;
            default:
                Menu.invalidChoice();
                return true;
        }
    }

    // Sous-menus du directeur 
    private boolean handleGestionDepartement() {
        boolean stayInMenu = true;
        while (stayInMenu) {
            Menu.afficherGestionDepartement();
            int subChoice = AgentView.getChoice();
            switch (subChoice) {
                case 1:
                    departementController.handleCreateDepartement();
                    break;
                case 2:
                    departementController.handleUpdateDepartement();
                    break;
                case 3:
                    departementController.handleDeleteDepartement();
                    break;
                case 4:
                    departementController.handleViewAllDepartements();
                    break;
                case 0:
                    stayInMenu = false; 
                    break;
                default:
                    Menu.invalidChoice();
            }
        }
        return true; 
    }

    private boolean handleGestionResponsable() {
        boolean stayInMenu = true;
        while (stayInMenu) {
            Menu.afficherMenuGestionResponsable();
            int subChoice = AgentView.getChoice();
            switch (subChoice) {
                case 1:
                    agentController.handleCreateResponsable();
                    break;
                case 2:
                    agentController.handleUpdateResponsable();
                    break;
                case 3:
                    agentController.handleDeleteResponsable();
                    break;
                case 4:
                    agentController.handleDesactiveResponsableCompte();
                    break;
                case 5:
                    agentController.handleViewAllResponsables();
                    break;
                case 6:
                    handleGestionPaiementsResponsableDirecteur();
                    break;
                case 0:
                    stayInMenu = false;
                    break;
                default:
                    Menu.invalidChoice();
            }
        }
        return true;
    }

    // Sous-menu spécifique pour la gestion des paiements des responsables (Directeur)
    private void handleGestionPaiementsResponsableDirecteur() {
        boolean stayInSubMenu = true;
        while (stayInSubMenu) {
            Menu.afficherMenuTypePaiement();
            int typePaiementChoice = AgentView.getChoice();
            switch (typePaiementChoice) {
                case 1:
                    paiementController.handleAddSalary();
                    break;
                case 2:
                    paiementController.handleAddPrime();
                    break;
                case 3:
                    paiementController.handleAddBonus();
                    break;
                case 4:
                    paiementController.handleAddIndemnity();
                    break;
                case 0:
                    stayInSubMenu = false;
                    break;
                default:
                    Menu.invalidChoice();
            }
        }
    }

    private boolean handleConsultationAgents() {
        boolean stayInMenu = true;
        while (stayInMenu) {
            Menu.afficherMenuAllAgents();
            int subChoice = AgentView.getChoice();
            switch (subChoice) {
                case 1:
                    agentController.handleViewAllAgents();
                    break;
                case 2:
                    agentController.handleSearchAgent();
                    break;
                case 3:
                    agentController.handleFilterAgentsByDepartment();
                    break;
                case 4:
                    agentController.handleFilterAgentsByRole();
                    break;
                case 0:
                    stayInMenu = false;
                    break;
                default:
                    Menu.invalidChoice();
            }
        }
        return true;
    }

    private boolean handleStatistiquesDirecteur() {
        boolean stayInMenu = true;
        while (stayInMenu) {
            Menu.afficherMenuStatistiquesDirecteur();
            int subChoice = AgentView.getChoice();
            switch (subChoice) {
                case 1:
                    agentController.handleAgentsCountByRole();
                    break;
                case 2:
                    agentController.handleTotalPaymentsByDepartment();
                    break;
                case 3:
                    agentController.handleAveragePaymentsByType();
                    break;
                case 4:
                    agentController.handlePaymentHistory();
                    break;
                case 5:
                    agentController.handleIdentifyUnusualPayments();
                    break;
                case 0:
                    stayInMenu = false;
                    break;
                default:
                    Menu.invalidChoice();
            }
        }
        return true;
    }

    // Sous-menus du responsable 
    private boolean handleGestionAgentsResponsable() {
        boolean stayInMenu = true;
        while (stayInMenu) {
            Menu.afficherMenuGestionAgentsResponsable();
            int subChoice = AgentView.getChoice();
            switch (subChoice) {
                case 1:
                    agentController.handleCreateAgent();
                    break;
                case 2:
                    agentController.handleUpdateAgent();
                    break;
                case 3:
                    agentController.handleDeleteAgent();
                    break;
                case 4:
                    agentController.handleViewDepartmentAgents();
                    break;
                case 5:
                    agentController.handleSearchAgent();
                    break;
                case 0:
                    stayInMenu = false;
                    break;
                default:
                    Menu.invalidChoice();
            }
        }
        return true;
    }

    private boolean handleGestionPaiementsResponsable() {
        boolean stayInMenu = true;
        while (stayInMenu) {
            Menu.afficherMenuGestionPaiementsResponsable();
            int subChoice = AgentView.getChoice();
            switch (subChoice) {
                case 1:
                    paiementController.handleAddSalary();
                    break;
                case 2:
                    paiementController.handleAddPrime();
                    break;
                case 3:
                    paiementController.handleViewAgentPayments();
                    break;
                case 4:
                    paiementController.handleFilterPayments();
                    break;
                case 0:
                    stayInMenu = false;
                    break;
                default:
                    Menu.invalidChoice();
            }
        }
        return true;
    }

    private boolean handleStatistiquesResponsable() {
        boolean stayInMenu = true;
        while (stayInMenu) {
            Menu.afficherMenuStatistiquesResponsable();
            int subChoice = AgentView.getChoice();
            switch (subChoice) {
                case 1:
                    paiementController.handleDepartmentAgentsCount();
                    break;
                case 2:
                    paiementController.handleDepartmentTotalPayments();
                    break;
                case 3:
                    paiementController.handleAveragePaymentsPerAgent();
                    break;
                case 4:
                    paiementController.handleDepartmentPaymentHistory();
                    break;
                case 0:
                    stayInMenu = false;
                    break;
                default:
                    Menu.invalidChoice();
            }
        }
        return true;
    }

    // Sous-menus de l'agent 
    private boolean handleFiltragePaiementsAgent() {
        boolean stayInMenu = true;
        while (stayInMenu) {
            Menu.afficherMenuFiltragePaiementsAgent();
            int subChoice = AgentView.getChoice();
            switch (subChoice) {
                case 1:
                    paiementController.handleFilterPaymentsByType();
                    break;
                case 2:
                    paiementController.handleFilterPaymentsByAmount();
                    break;
                case 3:
                    paiementController.handleFilterPaymentsByDate();
                    break;
                case 0:
                    stayInMenu = false;
                    break;
                default:
                    Menu.invalidChoice();
            }
        }
        return true;
    }
}