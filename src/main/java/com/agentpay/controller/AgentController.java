package main.java.com.agentpay.controller;

import java.util.List;
import java.util.Optional;
import main.java.com.agentpay.model.Agent;
import main.java.com.agentpay.model.enums.TypeAgent;
import main.java.com.agentpay.service.interfaces.AgentService;
import main.java.com.agentpay.service.interfaces.DepartementService;
import main.java.com.agentpay.view.AgentView;

public class AgentController {
    private final AgentService agentService;
    private final AgentView agentView;
    private final DepartementService departementService;

    public AgentController(AgentService agentService, AgentView agentView, DepartementService departementService) {
        this.agentService = agentService;
        this.agentView = agentView;
        this.departementService = departementService;
    }

    // Méthodes pour Directeur 
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
            updatedResponsable.setUserID(id);
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

    // Méthodes pour Responsable 
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
            updatedAgent.setUserID(id);
            System.out.println(updatedAgent);
            boolean success = agentService.updateAgent(updatedAgent);
            if (success) {
                agentView.showMessage("Agent mis à jour avec succès!");
            } else {
                agentView.showMessage("Erreur lors de la mise à jour de l'agent.");
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

    // Méthodes de consultation 
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

    public void handleManageResponsablePayments() {
        agentView.showMessage("Fonctionnalité en développement");
    }

    // Méthodes statistiques 
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

    public void handleViewAgentInfo() {
        agentView.showMessage("Fonctionnalité en développement");
    }

    public void handleViewPaymentHistory() {agentView.showMessage("Fonctionnalité en développement");
    }

    public void handleViewTotalPayments() {agentView.showMessage("Fonctionnalité en développement");
    }
}