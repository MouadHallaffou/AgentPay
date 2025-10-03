package main.java.com.agentpay.controller;

import main.java.com.agentpay.exceptions.PaiementInvalideException;
import main.java.com.agentpay.model.Agent;
import main.java.com.agentpay.model.Paiement;
import main.java.com.agentpay.model.enums.TypeAgent;
import main.java.com.agentpay.model.enums.TypePaiement;
import main.java.com.agentpay.service.interfaces.PaiementService;
import main.java.com.agentpay.service.interfaces.AgentService;
import main.java.com.agentpay.view.AgentView;

import java.util.List;

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
 
    public void handleAddBonus() {
        try {
            boolean success = paiementService.enregistrerPaiement(agentView.getPaiementInput(TypePaiement.BONUS));
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
    
    public void handleAddIndemnity() {
        try {
            boolean success = paiementService.enregistrerPaiement(agentView.getPaiementInput(TypePaiement.INDEMNITE));
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

    public void handleDepartmentAgentsCount() {
        try {
            String departementName = agentView.getInput("le name du de partement");
            List<Agent> agents = agentService.finAgentByDepartement(departementName.trim().toUpperCase());
            if (agents == null || agents.isEmpty()) {
                agentView.showMessage("Aucun agent trouvé.");
            } else {
                int index = 1;
                for (Agent res : agents) {
                    if (res.getTypeAgent() == TypeAgent.OUVRIER || res.getTypeAgent() == TypeAgent.STAGIAIRE) {
                        System.out.println("----------------------------------");
                        System.out.println(index + ". " + res.getFirstName() + " " + res.getLastName());
                        index++;
                    }
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void handleViewAgentPayments() {
        try {
            String email = agentView.getInput("l'email de l'agent");
            List<Paiement> paiements = agentService.getPaiementsByEmail(email);
            if (paiements == null || paiements.isEmpty()) {
                agentView.showMessage("Aucun paiement trouvé pour cet agent.");
            } else {
                System.out.printf("%-5s || %-10s || %-15s || %-10s || %-20s%n", "ID", "Montant", "Type", "Date", "Agent Email");
                System.out.println("-------------------------------------------------------------------------------");
                for (Paiement paiement : paiements) {
                    System.out.printf("%-5d || %-10.2f || %-15s || %-10s || %-20s%n",
                            paiement.getPaiementID(),
                            paiement.getMontant(),
                            paiement.getTypePaiement(),
                            paiement.getDatePaiement(),
                            paiement.getAgent().getEmail());
                }
            }
        } catch (Exception e) {
            System.out.println("Erreur lors de la récupération des paiements: " + e.getMessage());
        }
    }

    public void handleFilterPayments() {
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
        try {
            double totalPaiements = paiementService.getAllPaiement().stream()
                    .mapToDouble(Paiement::getMontant)
                    .sum();
            agentView.showMessage("Total des paiements effectués: " + totalPaiements);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void handleFilterPaymentsByType() {
        try {
            String typeStr = agentView.getInput("le type de paiement (SALAIRE, PRIME, BONUS, INDEMNITE)");
            TypePaiement typePaiement = TypePaiement.valueOf(typeStr.trim().toUpperCase());
            List<Paiement> paiements = paiementService.getAllPaiement().stream()
                    .filter(p -> p.getTypePaiement() == typePaiement)
                    .toList();
            if (paiements.isEmpty()) {
                agentView.showMessage("Aucun paiement trouvé pour le type: " + typePaiement);
            } else {
                System.out.printf("%-5s || %-10s || %-15s || %-10s || %-20s%n", "ID", "Montant", "Type", "Date", "Agent Email");
                System.out.println("-------------------------------------------------------------------------------");
                for (Paiement paiement : paiements) {
                    System.out.printf("%-5d || %-10.2f || %-15s || %-10s || %-20s%n",
                            paiement.getPaiementID(),
                            paiement.getMontant(),
                            paiement.getTypePaiement(),
                            paiement.getDatePaiement(),
                            paiement.getAgent().getEmail());
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void handleFilterPaymentsByAmount() {
        try {
            double minAmount = Double.parseDouble(agentView.getInput("le montant minimum"));
            double maxAmount = Double.parseDouble(agentView.getInput("le montant maximum"));
            List<Paiement> paiements = paiementService.getAllPaiement().stream()
                    .filter(p -> p.getMontant() >= minAmount && p.getMontant() <= maxAmount)
                    .toList();
            if (paiements.isEmpty()) {
                agentView.showMessage("Aucun paiement trouvé pour le montant spécifié.");
            } else {
                System.out.printf("%-5s || %-10s || %-15s || %-10s || %-20s%n", "ID", "Montant", "Type", "Date", "Agent Email");
                System.out.println("-------------------------------------------------------------------------------");
                for (Paiement paiement : paiements) {
                    System.out.printf("%-5d || %-10.2f || %-15s || %-10s || %-20s%n",
                            paiement.getPaiementID(),
                            paiement.getMontant(),
                            paiement.getTypePaiement(),
                            paiement.getDatePaiement(),
                            paiement.getAgent().getEmail());
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    public void handleSortPaymentsByAmount() {
        try {
            List<Paiement> paiements = paiementService.getAllPaiement().stream()
                    .sorted((p1, p2) -> Double.compare(p2.getMontant(), p1.getMontant()))
                    .toList();
            if (paiements.isEmpty()) {
                agentView.showMessage("Aucun paiement trouvé.");
            } else {
                System.out.printf("%-5s || %-10s || %-15s || %-10s || %-20s%n", "ID", "Montant", "Type", "Date", "Agent Email");
                System.out.println("-------------------------------------------------------------------------------");
                for (Paiement paiement : paiements) {
                    System.out.printf("%-5d || %-10.2f || %-15s || %-10s || %-20s%n",
                            paiement.getPaiementID(),
                            paiement.getMontant(),
                            paiement.getTypePaiement(),
                            paiement.getDatePaiement(),
                            paiement.getAgent().getEmail());
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void handleFilterPaymentsByDate() {
        try {
            String dateStr = agentView.getInput("la date (format: YYYY-MM-DD)");
            List<Paiement> paiements = paiementService.getAllPaiement().stream()
                    .filter(p -> p.getDatePaiement().toString().equals(dateStr))
                    .toList();
            if (paiements.isEmpty()) {
                agentView.showMessage("Aucun paiement trouvé pour la date: " + dateStr);
            } else {
                System.out.printf("%-5s || %-10s || %-15s || %-10s || %-20s%n", "ID", "Montant", "Type", "Date", "Agent Email");
                System.out.println("-------------------------------------------------------------------------------");
                for (Paiement paiement : paiements) {
                    System.out.printf("%-5d || %-10.2f || %-15s || %-10s || %-20s%n",
                            paiement.getPaiementID(),
                            paiement.getMontant(),
                            paiement.getTypePaiement(),
                            paiement.getDatePaiement(),
                            paiement.getAgent().getEmail());
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void handleEditSalary() {

    }

    public void handleEditPrime() {

    }

    public void handleVerifieBonus() {
    }

    public void handleVerifieIndemnity() {
    }
}