package main.java.com.agentpay.controller;

import main.java.com.agentpay.model.Departement;
import main.java.com.agentpay.service.interfaces.DepartementService;
import main.java.com.agentpay.utils.Validation;
import main.java.com.agentpay.view.DepartementView;

public class DepartementController {

    private final DepartementService departementService;
    private final DepartementView departementView;

    public DepartementController(DepartementService departementService, DepartementView departementView) {
        this.departementService = departementService;
        this.departementView = departementView;
    }

    // MÉTHODES DÉPARTEMENTS (Directeur)
    public void handleCreateDepartement() {
        try {
            String nomDepartement = departementView.getInput("Nom du département");
            if (!Validation.isValideName(nomDepartement)) {
                departementView.showMessage("Le nom du département ne peut pas être vide!");
                return;
            }
            Departement newDepartement = new Departement();
            newDepartement.setName(nomDepartement);
            boolean success = departementService.createDepartement(newDepartement);
            if (success) {
                departementView.showMessage("Département '" + nomDepartement + "' créé avec succès!");
            } else {
                departementView.showMessage("Erreur lors de la création du département.");
            }
        } catch (Exception e) {
            departementView.showMessage("Erreur: " + e.getMessage());
        }
    }

    public void handleUpdateDepartement() {
        try {
            int id = Integer.parseInt(departementView.getInput("ID du département à modifier"));
            String newName = departementView.getInput("Nouveau nom du département");
            if (!Validation.isValideName(newName)) {
                departementView.showMessage("Le nom du département ne pas valide!");
                return;
            }
            Departement updatedepatement = new Departement();
            updatedepatement.setDepartementID(id);
            updatedepatement.setName(newName);
            boolean success = departementService.updateDepartement(updatedepatement);
            if (success) {
                departementView.showMessage("Département mis à jour avec succès!");
            } else {
                departementView.showMessage("Erreur lors de la mise à jour du département.");
            }
        } catch (NumberFormatException e) {
            departementView.showMessage("ID invalide. Veuillez entrer un nombre.");
        } catch (Exception e) {
            departementView.showMessage("Erreur: " + e.getMessage());
        }
    }

    public void handleDeleteDepartement() {
        try {
            int id = Integer.parseInt(departementView.getInput("ID du département à supprimer"));
            boolean success = departementService.deleteDepartement(id);
            if (success) {
                departementView.showMessage("Département supprimé avec succès!");
            } else {
                departementView.showMessage("Erreur lors de la suppression du département.");
            }
        } catch (NumberFormatException e) {
            departementView.showMessage("ID invalide. Veuillez entrer un nombre.");
        } catch (Exception e) {
            departementView.showMessage("Erreur: " + e.getMessage());
        }
    }

    public void handleGetDepartementById() {
        try {
            int id = Integer.parseInt(departementView.getInput("ID du département à consulter"));
            var departementOpt = departementService.findDepartementById(id);
            if (departementOpt.isPresent()) {
                departementView.showMessage(departementOpt.get().toString());
            } else {
                departementView.showMessage("Département non trouvé.");
            }
        } catch (NumberFormatException e) {
            departementView.showMessage("ID invalide. Veuillez entrer un nombre.");
        } catch (Exception e) {
            departementView.showMessage("Erreur: " + e.getMessage());
        }
    }

    public void handleViewAllDepartements() {
        try {
            var departements = departementService.getAllDepartements();
            if (departements.isEmpty()) {
                departementView.showMessage("Aucun département trouvé.");
            } else {
                departementView.showMessage("Liste des départements:");
                System.out.printf("%-25s %-2s %-15s%n", "Département", "||", "Total Agents");
//                System.out.println(departements);
                for (var dep : departements) {

                    System.out.printf("%-25s %-2s %-15d%n", dep.getName() , "||", dep.getTotalAgents());
                }
            }
        } catch (Exception e) {
            departementView.showMessage("Erreur: " + e.getMessage());
        }
    }
}