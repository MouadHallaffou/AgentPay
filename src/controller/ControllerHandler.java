package controller;

import java.util.Optional;
import model.Agent;
import service.AuthService;
import service.menu.MenuService;

public class ControllerHandler {
    private final AuthService authService;
    private final MenuService menuService;

    public ControllerHandler(AuthService authService) {
        this.authService = authService;
        this.menuService = new MenuService();
    }

    public Optional<Agent> authenticate (String email, String password) {
        Optional<Agent> agentOpt = authService.getLogedAgent(email,password);
        if (agentOpt.isPresent()){
            System.out.println("conexion reussit!");
            return agentOpt;
        }else {
            System.out.println("email ou mot de pass incorect!");
            return Optional.empty();
        }
    }

    public void handleMenu(Agent agent) {
        boolean running = true;
        while (running) {
            int choice = menuService.afficherMenu(agent);

            switch (agent.getTypeAgent()) {
                case DIRECTEUR -> running = handleDirecteur(choice);
                case RESPONSABLE -> running = handleResponsable(choice);
                case OUVRIER, STAGIAIRE -> running = handleSimpleAgent(choice);
            }
        }
    }

    private boolean handleDirecteur(int choice) {
        return switch (choice) {
            case 1 -> { System.out.println("➡ Créer un département"); yield true; }
            case 2 -> { System.out.println("➡ Assigner responsable"); yield true; }
            case 3 -> { System.out.println("➡ Consulter tous les agents"); yield true; }
            case 4 -> { System.out.println("➡ Statistiques globales"); yield true; }
            case 0 -> false;
            default -> { System.out.println("Choix invalide."); yield true; }
        };
    }

    private boolean handleResponsable(int choice) {
        return switch (choice) {
            case 1 -> { System.out.println("➡ Ajouter un agent"); yield true; }
            case 2 -> { System.out.println("➡ Modifier un agent"); yield true; }
            case 3 -> { System.out.println("➡ Ajouter paiement"); yield true; }
            case 4 -> { System.out.println("➡ Consulter paiements département"); yield true; }
            case 0 -> false;
            default -> { System.out.println("Choix invalide."); yield true; }
        };
    }

    private boolean handleSimpleAgent(int choice) {
        return switch (choice) {
            case 1 -> { System.out.println("➡ Consulter mes informations"); yield true; }
            case 2 -> { System.out.println("➡ Historique paiements"); yield true; }
            case 3 -> { System.out.println("➡ Total paiements"); yield true; }
            case 0 -> false;
            default -> { System.out.println("Choix invalide."); yield true; }
        };
    }

}
