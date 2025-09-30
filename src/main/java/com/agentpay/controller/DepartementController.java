package main.java.com.agentpay.controller;

import main.java.com.agentpay.model.Departement;
import main.java.com.agentpay.service.interfaces.DepartementService;
import main.java.com.agentpay.utils.Validation;
import main.java.com.agentpay.view.AgentView;

public class DepartementController {

    private final DepartementService departementService;
    private final AgentView agentView;

    public DepartementController(DepartementService departementService, AgentView agentView) {
        this.departementService = departementService;
        this.agentView = agentView;
    }

    // MÉTHODES DÉPARTEMENTS (Directeur)
    public void handleCreateDepartement() {
        try {
            String nomDepartement = agentView.getInput("Nom du département");
            if (!Validation.isValideName(nomDepartement)) {
                agentView.showMessage("Le nom du département ne peut pas être vide!");
                return;
            }
            Departement newDepartement = new Departement();
            newDepartement.setName(nomDepartement);
            boolean success = departementService.createDepartement(newDepartement);
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
        try {
            int id = Integer.parseInt(agentView.getInput("ID du département à modifier"));
            String newName = agentView.getInput("Nouveau nom du département");
            if (!Validation.isValideName(newName)) {
                agentView.showMessage("Le nom du département ne pas valide!");
                return;
            }
            Departement updatedepatement = new Departement();
            updatedepatement.setDepartementID(id);
            updatedepatement.setName(newName);
            boolean success = departementService.updateDepartement(updatedepatement);
            if (success) {
                agentView.showMessage("Département mis à jour avec succès!");
            } else {
                agentView.showMessage("Erreur lors de la mise à jour du département.");
            }
        } catch (NumberFormatException e) {
            agentView.showMessage("ID invalide. Veuillez entrer un nombre.");
        } catch (Exception e) {
            agentView.showMessage("Erreur: " + e.getMessage());
        }
    }

    public void handleDeleteDepartement() {
        try {
            int id = Integer.parseInt(agentView.getInput("ID du département à supprimer"));
            boolean success = departementService.deleteDepartement(id);
            if (success) {
                agentView.showMessage("Département supprimé avec succès!");
            } else {
                agentView.showMessage("Erreur lors de la suppression du département.");
            }
        } catch (NumberFormatException e) {
            agentView.showMessage("ID invalide. Veuillez entrer un nombre.");
        } catch (Exception e) {
            agentView.showMessage("Erreur: " + e.getMessage());
        }
    }

    public void handleGetDepartementById() {
        try {
            int id = Integer.parseInt(agentView.getInput("ID du département à consulter"));
            var departementOpt = departementService.findDepartementById(id);
            if (departementOpt.isPresent()) {
                agentView.showMessage(departementOpt.get().toString());
            } else {
                agentView.showMessage("Département non trouvé.");
            }
        } catch (NumberFormatException e) {
            agentView.showMessage("ID invalide. Veuillez entrer un nombre.");
        } catch (Exception e) {
            agentView.showMessage("Erreur: " + e.getMessage());
        }
    }

    public void handleViewAllDepartements() {
        try {
            var departements = departementService.getAllDepartements();
            if (departements.isEmpty()) {
                agentView.showMessage("Aucun département trouvé.");
            } else {
                agentView.showMessage("Liste des départements:");
                System.out.printf("%-25s %-25s %-15s%n", "Département", "Responsable", "Total Agents");
                for (var dep : departements) {
                    String responsable = dep.getResponsable();
                    int totalAgents = dep.getTotalAgents();
                    System.out.printf("%-25s %-25s %-15d%n", dep.getName(), responsable, totalAgents);
                }
            }
        } catch (Exception e) {
            agentView.showMessage("Erreur: " + e.getMessage());
        }
    }
}