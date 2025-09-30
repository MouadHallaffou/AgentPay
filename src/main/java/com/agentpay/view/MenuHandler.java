package main.java.com.agentpay.view;

import main.java.com.agentpay.controller.ControllerHandler;
import main.java.com.agentpay.model.Paiement;

public class MenuHandler {
    private final ControllerHandler controller;

    public MenuHandler(ControllerHandler controller) {
        this.controller = controller;
    }

    // Traitement des choix du directeur
    public boolean handleDirecteurChoice(int choice) {
        switch (choice) {
            case 1 -> {
                Menu.afficherGestionDepartement();
                return handleGestionDepartement();
            }
            case 2 -> {
                Menu.afficherMenuGestionResponsable();
                return handleGestionResponsable();
            }
            case 3 -> {
                Menu.afficherMenuAllAgents();
                return handleConsultationAgents();
            }
            case 4 -> {
                Menu.afficherMenuStatistiquesDirecteur();
                return handleStatistiquesDirecteur();
            }
            case 0 -> {
                Menu.logout();
                return false;
            }
            default -> {
                Menu.invalidChoice();
                return true;
            }
        }
    }

    // Traitement des choix du responsable
    public boolean handleResponsableChoice(int choice) {
        switch (choice) {
            case 1 -> {
                Menu.afficherMenuGestionAgentsResponsable();
                return handleGestionAgentsResponsable();
            }
            case 2 -> {
                Menu.afficherMenuGestionPaiementsResponsable();
                return handleGestionPaiementsResponsable();
            }
            case 3 -> {
                Menu.afficherMenuStatistiquesResponsable();
                return handleStatistiquesResponsable();
            }
            case 0 -> {
                Menu.logout();
                return false;
            }
            default -> {
                Menu.invalidChoice();
                return true;
            }
        }
    }

    // Traitement des choix de l'agent
    public boolean handleAgentChoice(int choice) {
        switch (choice) {
            case 1 -> {
                controller.handleViewAgentInfo();
                return true;
            }
            case 2 -> {
                controller.handleViewPaymentHistory();
                return true;
            }
            case 3 -> {
                Menu.afficherMenuFiltragePaiementsAgent();
                return handleFiltragePaiementsAgent();
            }
            case 4 -> {
                controller.handleViewTotalPayments();
                return true;
            }
            case 0 -> {
                Menu.logout();
                return false;
            }
            default -> {
                Menu.invalidChoice();
                return true;
            }
        }
    }

    // Sous-menus du directeur
    private boolean handleGestionDepartement() {
        int subChoice = AgentView.getChoice();
        switch (subChoice) {
            case 1 -> controller.handleCreateDepartement();
            case 2 -> controller.handleUpdateDepartement();
            case 3 -> controller.handleDeleteDepartement();
            case 4 -> controller.handleViewAllDepartements();
            case 0 -> {
                return true;
            }
            default -> Menu.invalidChoice();
        }
        return true;
    }

    private boolean handleGestionResponsable() {
        int subChoice = AgentView.getChoice();
        switch (subChoice) {
            case 1 -> controller.handleCreateResponsable();
            case 2 -> controller.handleUpdateResponsable();
            case 3 -> controller.handleDeleteResponsable();
            case 4 -> controller.handleDesactiveResponsableCompte();
            case 5 -> controller.handleViewAllResponsables();
            case 6 -> controller.handleManageResponsablePayments();
            case 0 -> {
                return true;
            }
            default -> Menu.invalidChoice();
        }
        return true;
    }

    private boolean handleConsultationAgents() {
        int subChoice = AgentView.getChoice();
        switch (subChoice) {
            case 1 -> controller.handleViewAllAgents();
            case 2 -> controller.handleSearchAgent();
            case 3 -> controller.handleFilterAgentsByDepartment();
            case 4 -> controller.handleFilterAgentsByRole();
            case 0 -> {
                return true;
            }
            default -> Menu.invalidChoice();
        }
        return true;
    }

    private boolean handleStatistiquesDirecteur() {
        int subChoice = AgentView.getChoice();
        switch (subChoice) {
            case 1 -> controller.handleAgentsCountByRole();
            case 2 -> controller.handleTotalPaymentsByDepartment();
            case 3 -> controller.handleAveragePaymentsByType();
            case 4 -> controller.handlePaymentHistory();
            case 5 -> controller.handleIdentifyUnusualPayments();
            case 0 -> {
                return true;
            }
            default -> Menu.invalidChoice();
        }
        return true;
    }

    // Sous-menus du responsable
    private boolean handleGestionAgentsResponsable() {
        int subChoice = AgentView.getChoice();
        switch (subChoice) {
            case 1 -> controller.handleCreateAgent();
            case 2 -> controller.handleUpdateAgent();
            case 3 -> controller.handleDeleteAgent();
            case 4 -> controller.handleViewDepartmentAgents();
            case 5 -> controller.handleSearchAgent();
            case 0 -> {
                return true;
            }
            default -> Menu.invalidChoice();
        }
        return true;
    }

    private boolean handleGestionPaiementsResponsable() {
        int subChoice = AgentView.getChoice();
        switch (subChoice) {
            case 1 -> controller.handleAddSalary();
            case 2 -> controller.handleAddPrime();
            case 3 -> controller.handleAddBonus();
            case 4 -> controller.handleAddIndemnity();
            case 5 -> controller.handleViewAgentPayments();
            case 6 -> controller.handleFilterPayments();
            case 0 -> {
                return true;
            }
            default -> Menu.invalidChoice();
        }
        return true;
    }

    private boolean handleStatistiquesResponsable() {
        int subChoice = AgentView.getChoice();
        switch (subChoice) {
            case 1 -> controller.handleDepartmentAgentsCount();
            case 2 -> controller.handleDepartmentTotalPayments();
            case 3 -> controller.handleAveragePaymentsPerAgent();
            case 4 -> controller.handleDepartmentPaymentHistory();
            case 0 -> {
                return true;
            }
            default -> Menu.invalidChoice();
        }
        return true;
    }

    // Sous-menus de l'agent
    private boolean handleFiltragePaiementsAgent() {
        int subChoice = AgentView.getChoice();
        switch (subChoice) {
            case 1 -> controller.handleFilterPaymentsByType();
            case 2 -> controller.handleFilterPaymentsByAmount();
            case 3 -> controller.handleFilterPaymentsByDate();
            case 0 -> {
                return true;
            }
            default -> Menu.invalidChoice();
        }
        return true;
    }
}