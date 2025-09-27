package main.java.com.agentpay.controller;

import java.util.Optional;
import main.java.com.agentpay.model.Agent;
import main.java.com.agentpay.service.AuthService;
import main.java.com.agentpay.service.AgentService;
import main.java.com.agentpay.view.Menus;
import main.java.com.agentpay.view.AgentView;
import main.java.com.agentpay.repository.interfacesImp.AgentRepositoryImp;
import main.java.com.agentpay.repository.interfacesImp.DepartementRepositoryImp;
import main.java.com.agentpay.service.DepartementService;
import main.java.com.agentpay.utils.Validation;

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

    // AUTHENTIFICATION
    public Optional<Agent> authenticate(String email, String password) {
        Optional<Agent> agentOpt = authService.getLogedAgent(email, password);
        if (agentOpt.isPresent()) {
            System.out.println("Connexion réussie!");
            return agentOpt;
        } else {
            System.out.println("Email ou mot de passe incorrect!");
            return Optional.empty();
        }
    }

    // MÉTHODE POUR GÉRER LA CRÉATION D'UN AGENT
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

    // MÉTHODE POUR CRÉER UN DÉPARTEMENT
    private void handleCreateDepartement() {
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
            case DIRECTEUR -> handleDirecteur(choice);
            case RESPONSABLE -> handleResponsable(choice);
            case OUVRIER, STAGIAIRE -> handleSimpleAgent(choice);
            default -> {
                System.out.println("Type d'agent non reconnu!");
                yield false;
            }
        };
    }

    // GÉRER LE MENU DIRECTEUR
    private boolean handleDirecteur(int choice) {
        return switch (choice) {
            case 1 -> {
                System.out.println("1. ➤ Créer un département");
                handleCreateDepartement();
                yield true;
            }
            case 2 -> {
                System.out.println("2. ➤  Assigner responsable");
                //assignation responsable
                agentView.showMessage("Fonctionnalité en développement");
                yield true;
            }
            case 3 -> {
                System.out.println("3. ➤ Consulter tous les agents");
                //consultation agents
                agentView.showMessage("Fonctionnalité en développement");
                yield true;
            }
            case 4 -> {
                System.out.println("4. ➤  Statistiques globales");
                //statistiques
                agentView.showMessage("Fonctionnalité en développement");
                yield true;
            }
            case 0 -> {
                Menus.logout();
                yield false;
            }
            default -> {
                Menus.invalidChoice();
                yield true;
            }
        };
    }

    // GÉRER LE MENU RESPONSABLE
    private boolean handleResponsable(int choice) {
        return switch (choice) {
            case 1 -> {
                System.out.println("1. ➤  Ajouter un agent");
                handleCreateAgent();
                yield true;
            }
            case 2 -> {
                System.out.println("2. ➤  Modifier un agent");
                //modification agent
                agentView.showMessage("Fonctionnalité en développement");
                yield true;
            }
            case 3 -> {
                System.out.println("3. ➤  Ajouter paiement");
                //ajout paiement
                agentView.showMessage("Fonctionnalité en développement");
                yield true;
            }
            case 4 -> {
                System.out.println("4. ➤  Consulter paiements département");
                //consultation paiements
                agentView.showMessage("Fonctionnalité en développement");
                yield true;
            }
            case 0 -> {
                Menus.logout();
                yield false;
            }
            default -> {
                Menus.invalidChoice();
                yield true;
            }
        };
    }

    // GÉRER LE MENU AGENT OUVRIER, STAGIAIRE
    private boolean handleSimpleAgent(int choice) {
        return switch (choice) {
            case 1 -> {
                System.out.println("1. ➤  Consulter mes informations");
                //consultation informations
                agentView.showMessage("Fonctionnalité en développement");
                yield true;
            }
            case 2 -> {
                System.out.println("2. ➤  Historique paiements");
                //historique paiements
                agentView.showMessage("Fonctionnalité en développement");
                yield true;
            }
            case 3 -> {
                System.out.println("3. ➤  Total paiements");
                //total paiements
                agentView.showMessage("Fonctionnalité en développement");
                yield true;
            }
            case 0 -> {
                Menus.logout();
                yield false;
            }
            default -> {
                Menus.invalidChoice();
                yield true;
            }
        };
    }

}
