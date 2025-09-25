package service.menu;

import model.Agent;
import java.util.Scanner;

public class MenuService {
    private final Scanner scanner = new Scanner(System.in);

    public int afficherMenu(Agent agent) {
        switch(agent.getTypeAgent()) {
            case DIRECTEUR -> afficherMenuDirecteur();
            case RESPONSABLE -> afficherMenuResponsable();
            case OUVRIER, STAGIAIRE -> afficherMenuAgent();
        }
        System.out.print("Votre choix : ");
        return Integer.parseInt(scanner.nextLine());
    }

    private void afficherMenuDirecteur() {
        System.out.println("\n=== Menu Directeur ===");
        System.out.println("1. Créer un département");
        System.out.println("2. Assigner responsable");
        System.out.println("3. Consulter tous les agents");
        System.out.println("4. Statistiques globales");
        System.out.println("0. Quitter");
    }

    private void afficherMenuResponsable() {
        System.out.println("\n=== Menu Responsable ===");
        System.out.println("1. Ajouter un agent");
        System.out.println("2. Modifier un agent");
        System.out.println("3. Ajouter paiement");
        System.out.println("4. Consulter paiements département");
        System.out.println("0. Quitter");
    }

    private void afficherMenuAgent() {
        System.out.println("\n=== Menu Agent ===");
        System.out.println("1. Consulter mes informations");
        System.out.println("2. Historique paiements");
        System.out.println("3. Total paiements");
        System.out.println("0. Quitter");
    }
}
