package main.java.com.agentpay.controller;

import java.util.List;
import java.util.Optional;

import main.java.com.agentpay.config.ConfigConnection;
import main.java.com.agentpay.exceptions.PaiementInvalideException;
import main.java.com.agentpay.model.Agent;
import main.java.com.agentpay.model.Departement;
import main.java.com.agentpay.model.enums.TypeAgent;
import main.java.com.agentpay.model.enums.TypePaiement;
import main.java.com.agentpay.service.AuthService;
import main.java.com.agentpay.service.AgentServiceImpl;
import main.java.com.agentpay.service.PaiementServiceImpl;
import main.java.com.agentpay.view.Menu;
import main.java.com.agentpay.view.AgentView;
import main.java.com.agentpay.view.MenuHandler;
import main.java.com.agentpay.repository.interfacesImp.AgentRepositoryImp;
import main.java.com.agentpay.repository.interfacesImp.DepartementRepositoryImp;
import main.java.com.agentpay.repository.interfacesImp.PaiementRepositoryImp;
import main.java.com.agentpay.service.DepartementServiceImpl;
import main.java.com.agentpay.utils.Validation;

public class ControllerHandler {
    private final AuthService authService;
    private final AgentServiceImpl agentService;
    private final AgentView agentView;
    private final DepartementServiceImpl departementService;
    private final MenuHandler menuHandler;
    private final PaiementServiceImpl paiementService;

    public ControllerHandler(AuthService authService) {
        this.authService = authService;
        this.agentService = new AgentServiceImpl(new AgentRepositoryImp());
        this.agentView = new AgentView(agentService);
        this.departementService = new DepartementServiceImpl(
                new DepartementRepositoryImp(ConfigConnection.getConnection()));
        this.menuHandler = new MenuHandler(this);
        this.paiementService = new PaiementServiceImpl(new PaiementRepositoryImp(null, null));
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
            int choice = AgentView.getChoice();
            running = processChoice(agent, choice);
        }
        System.out.println("Au revoir!");
    }

    // AFFICHER LE MENU SELON LE RÔLE
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

    // MÉTHODES RESPONSABLES (Directeur)
    public void handleCreateResponsable() {
        try {
            Agent newResponsable = agentView.getAgentInput();
            boolean success = agentService.createAgent(newResponsable);
            if (success) {
                agentView.showMessage("Responsable créé avec succès!");
            } else {
                agentView.showMessage("Erreur lors de la création du responsable.");
            }
        } catch (Exception e) {
            agentView.showMessage("Erreur: " + e.getMessage());
            // e.printStackTrace();
        }
    }

    public void handleUpdateResponsable() {
        try {
            int id = Integer.parseInt(agentView.getInput("ID du responsable à modifier"));
            Optional<Agent> existingResponsable = agentService.getAgentById(id);
            if (existingResponsable.isEmpty() || existingResponsable.get().getTypeAgent() != TypeAgent.RESPONSABLE) {
                agentView.showMessage("Responsable non trouvé.");
                return;
            }
            Agent updatedResponsable = agentView.getUpdateAgentInput(id);
            updatedResponsable.setUserID(id); // ID reste le même
            boolean success = agentService.updateAgent(updatedResponsable);
            if (success) {
                agentView.showMessage("Responsable mis à jour avec succès!");
            } else {
                agentView.showMessage("Erreur lors de la mise à jour du responsable.");
            }
        } catch (NumberFormatException e) {
            agentView.showMessage("ID invalide. Veuillez entrer un nombre.");
        } catch (Exception e) {
            agentView.showMessage("Erreur: " + e.getMessage());
        }
    }

    public void handleDeleteResponsable() {
        try {
            int id = Integer.parseInt(agentView.getInput("ID du Responsable a supprimer"));
            boolean success = agentService.deleteAgent(id);
            if (success) {
                agentView.showMessage("responsable supprimé avec succès!");
            } else {
                agentView.showMessage("Erreur lors de la suppression du responsable avec l'ID: " + id);
            }
        } catch (NumberFormatException e) {
            agentView.showMessage("ID invalide. Veuillez entrer un nombre.");
        } catch (Exception e) {
            agentView.showMessage("Erreur: " + e.getMessage());
        }
    }

    public void handleDesactiveResponsableCompte() {
        try {
            int id = Integer.parseInt(agentView.getInput("ID du responsable à désactiver"));
            Optional<Agent> existingResponsable = agentService.getAgentById(id);
            if (existingResponsable.isEmpty() || existingResponsable.get().getTypeAgent() != TypeAgent.RESPONSABLE) {
                agentView.showMessage("Responsable non trouvé.");
                return;
            }
            Agent responsable = existingResponsable.get();
            responsable.setIsActive(false);
            boolean success = agentService.setAgentAccountStatus(responsable.getUserID());
            if (success) {
                agentView.showMessage("Compte du responsable désactivé avec succès!");
            } else {
                agentView.showMessage("Erreur lors de la désactivation du compte du responsable.");
            }
        } catch (NumberFormatException e) {
            agentView.showMessage("ID invalide. Veuillez entrer un nombre.");
        } catch (Exception e) {
            agentView.showMessage("Erreur: " + e.getMessage());
        }
    }

    public void handleViewAllResponsables() {
        try {
            var responsables = agentService.getAllAgents();
            if (responsables.isEmpty()) {
                agentView.showMessage("Aucun responsable trouvé.");
            } else {
                int index = 1;
                for (Agent res : responsables) {
                    if (res.getTypeAgent() == TypeAgent.RESPONSABLE) {
                        System.out.println(index + ". " + res.getFirstName() + " " + res.getLastName());
                        index++;
                    }
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
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
        try {
            int id = Integer.parseInt(agentView.getInput("ID de l'agent à modifier"));
            Optional<Agent> existingAgent = agentService.getAgentById(id);
            if (!existingAgent.isPresent() || existingAgent.get().getTypeAgent() == TypeAgent.RESPONSABLE) {
                System.out.println("il faut selectionne un exist agent");
                return;
            }
            Agent updatedAgent = agentView.getUpdateAgentInput(id);
            updatedAgent.setUserID(id); // ID reste le même
            System.out.println(updatedAgent);
            boolean success = agentService.updateAgent(updatedAgent);
            if (success) {
                agentView.showMessage("Responsable mis à jour avec succès!");
            } else {
                agentView.showMessage("Erreur lors de la mise à jour du responsable.");
            }
        } catch (NumberFormatException e) {
            agentView.showMessage("ID invalide. Veuillez entrer un nombre.");
        } catch (Exception e) {
            agentView.showMessage("Erreur: " + e.getMessage());
        }
    }

    public void handleDeleteAgent() {
        try {
            int id = Integer.parseInt(agentView.getInput("ID de l'agent a supprimer"));
            boolean success = agentService.deleteAgent(id);
            if (success) {
                agentView.showMessage("agent supprimé avec succès!");
            } else {
                agentView.showMessage("Erreur lors de la suppression de l'agent avec l'ID: " + id);
            }
        } catch (NumberFormatException e) {
            agentView.showMessage("ID invalide. Veuillez entrer un nombre.");
        } catch (Exception e) {
            agentView.showMessage("Erreur: " + e.getMessage());
        }
    }

    public void handleViewDepartmentAgents() {
        try {
            String departementName = agentView.getInput("le name du de partement");
            List<Agent> agents = agentService.finAgentByDepartement(departementName.trim().toUpperCase());
            if (agents == null || agents.isEmpty()) {
                agentView.showMessage("Aucun agent trouvé.");
            } else {
                int index = 1;
                for (Agent res : agents) {
                    if (res.getTypeAgent() == TypeAgent.OUVRIER || res.getTypeAgent() == TypeAgent.STAGIAIRE) {
                        System.out.println(index + ". " + res.getFirstName() + " " + res.getLastName());
                        index++;
                    }
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void handleSearchAgent() {
        try {
            String agentFullName = agentView.getInput("Entrez le nom complet de l'agent");
            Agent agent = agentService.getAgentByFullName(agentFullName);
            if (agent != null) {
                agentView.displayAgent(agent);
            } else {
                agentView.showMessage("Aucun agent trouvé avec ce nom.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            agentView.showMessage("Erreur lors de la recherche de l'agent.");
        }
    }

    // MÉTHODES PAIEMENTS (Responsable)
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
