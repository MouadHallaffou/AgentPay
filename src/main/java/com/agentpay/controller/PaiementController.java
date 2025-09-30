package main.java.com.agentpay.controller;

import main.java.com.agentpay.exceptions.PaiementInvalideException;
import main.java.com.agentpay.model.enums.TypePaiement;
import main.java.com.agentpay.service.interfaces.PaiementService;
import main.java.com.agentpay.service.interfaces.AgentService;
import main.java.com.agentpay.view.AgentView;

public class PaiementController {
    private final PaiementService paiementService;
    private final AgentView agentView;
    private final AgentService agentService;

    public PaiementController(PaiementService paiementService, AgentView agentView, AgentService agentService) {
        this.paiementService = paiementService;
        this.agentView = agentView;
        this.agentService = agentService;
    }

    public void handleAddSalary() {
        try {
            boolean success = paiementService.enregistrerPaiement(agentView.getPaiementInput(TypePaiement.SALAIRE));
            if (success) {
                agentView.showMessage("Paiement enregistré avec succès !");
            } else {
                agentView.showMessage("Erreur lors de l'enregistrement du paiement.");
            }
        } catch (PaiementInvalideException e) {
            agentView.showMessage("Erreur de validation : " + e.getMessage());
        } catch (Exception e) {
            agentView.showMessage("Erreur : " + e.getMessage());
        }
    }

    public void handleAddPrime() {
        try {
            boolean success = paiementService.enregistrerPaiement(agentView.getPaiementInput(TypePaiement.PRIME));
            if (success) {
                agentView.showMessage("Paiement enregistré avec succès !");
            } else {
                agentView.showMessage("Erreur lors de l'enregistrement du paiement.");
            }
        } catch (PaiementInvalideException e) {
            agentView.showMessage("Erreur de validation : " + e.getMessage());
        } catch (Exception e) {
            agentView.showMessage("Erreur : " + e.getMessage());
        }
    }

    // Méthodes à implémenter
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

    public void handleViewPaymentHistory() {
        agentView.showMessage("Fonctionnalité en développement");
    }

    public void handleViewTotalPayments() {
        agentView.showMessage("Fonctionnalité en développement");
    }

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