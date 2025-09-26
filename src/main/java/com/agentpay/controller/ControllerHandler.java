package main.java.com.agentpay.controller;

import java.util.Optional;
import main.java.com.agentpay.model.Agent;
import main.java.com.agentpay.service.AuthService;
import main.java.com.agentpay.service.AgentService;
import main.java.com.agentpay.view.MenuService;
import main.java.com.agentpay.view.AgentView;
import main.java.com.agentpay.repository.interfacesImp.AgentRepositoryImp;
import main.java.com.agentpay.repository.interfacesImp.DepartementRepositoryImp;
import main.java.com.agentpay.service.DepartementService;

public class ControllerHandler {
    private final AuthService authService;
    private final AgentService agentService;
    private final AgentView agentView;
    private final DepartementService departementService;

    public ControllerHandler(AuthService authService) {
        this.authService = authService;
        this.agentService = new AgentService(new AgentRepositoryImp());
        this.agentView = new AgentView();
        this.departementService = new DepartementService(new DepartementRepositoryImp());
    }

    public Optional<Agent> authenticate(String email, String password) {
        Optional<Agent> agentOpt = authService.getLogedAgent(email, password);
        if (agentOpt.isPresent()) {
            Agent agent = agentOpt.get();
            switch (agent.getTypeAgent()) {
                case DIRECTEUR:
                    MenuService.afficherMenuDirecteur();
                    break;
                case RESPONSABLE:
                    MenuService.afficherMenuResponsable();
                    break;
                case OUVRIER:
                    MenuService.afficherMenuAgent();
                    break;
                default:
                    System.out.println("Type d'agent inconnu !");
                    return Optional.empty();
            }
            return Optional.of(agent);
        } else {
            System.out.println("Email ou mot de passe incorrect !");
            return Optional.empty();
        }
    }

    private boolean handleDirecteur(int choice) {
        return switch (choice) {
            case 1 -> {
                System.out.println("➡ Créer un département");
                yield true;
            }
            case 2 -> {
                System.out.println("➡ Assigner responsable");
                yield true;
            }
            case 3 -> {
                System.out.println("➡ Consulter tous les agents");
                yield true;
            }
            case 4 -> {
                System.out.println("➡ Statistiques globales");
                yield true;
            }
            case 0 -> false;
            default -> {
                System.out.println("Choix invalide.");
                yield true;
            }
        };
    }

    private boolean handleResponsable(int choice) {
        return switch (choice) {
            case 1 -> {
                System.out.println("➡ Ajouter un agent");
                handleCreateAgent();
                yield true;
            }
            case 2 -> {
                System.out.println("➡ Modifier un agent");
                yield true;
            }
            case 3 -> {
                System.out.println("➡ Ajouter paiement");
                yield true;
            }
            case 4 -> {
                System.out.println("➡ Consulter paiements département");
                yield true;
            }
            case 0 -> false;
            default -> {
                System.out.println("Choix invalide.");
                yield true;
            }
        };
    }

    // methode pour gerer la creation d'un agent:
    private void handleCreateAgent() {
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

    private boolean handleSimpleAgent(int choice) {
        return switch (choice) {
            case 1 -> {
                System.out.println("➡ Consulter mes informations");
                yield true;
            }
            case 2 -> {
                System.out.println("➡ Historique paiements");
                yield true;
            }
            case 3 -> {
                System.out.println("➡ Total paiements");
                yield true;
            }
            case 0 -> false;
            default -> {
                System.out.println("Choix invalide.");
                yield true;
            }
        };
    }

}
