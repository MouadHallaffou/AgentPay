package main.java.com.agentpay.view;

public class Menus {
    public static void afficherMenuDirecteur() {
        System.out.println("\n=== Menu Directeur ===");
        System.out.println("1.➤ Département");
        System.out.println("2.➤ Responsable");
        System.out.println("3.➤ Agents");
        System.out.println("4.➤ Paiements");
        System.out.println("5.➤ Statistiques");
        System.out.println("0.➤ Quitter");
    }
        public static void afficherMenuDepartement() {
        System.out.println("\n=== Menu Département ===");
        System.out.println("1.➤ Ajouter département");
        System.out.println("2.➤ Modifier département");
        System.out.println("3.➤ Supprimer département");
        System.out.println("4.➤ Voir départements");
        System.out.println("0.➤ Retour");
    }

    public static void afficherMenuMangeResponsable() {
        System.out.println("\n=== Menu Responsable ===");
        System.out.println("1.➤ Ajouter Responsable");
        System.out.println("2.➤ Modifier Responsable");
        System.out.println("3.➤ Supprimer Responsable");
        System.out.println("4.➤ Voir Responsables");
        System.out.println("0.➤ Retour");
    }
    
    public static void afficherMenuAllAgents() {
        System.out.println("\n=== Menu All Agents ===");
        System.out.println("1.➤ Voir tous les agents");
        System.out.println("2.➤ Rechercher un agent par ID");
        System.out.println("0.➤ Retour");
    }
    // Menu pour les responsables
    public static void afficherMenuResponsable() {
        System.out.println("\n=== Menu Responsable ===");
        System.out.println("1.➤ Agents");
        System.out.println("2.➤ Paiements");
        System.out.println("3.➤ Statistiques");
        System.out.println("0.➤ Quitter");
    }
    // Menu pour les agents
    public static void afficherMenuAgent() {
        System.out.println("\n=== Menu Agent ===");
        System.out.println("1.➤ Consulte informations");
        System.out.println("2.➤ Historique paiements");
        System.out.println("3.➤ Total paiements");
        System.out.println("0.➤ Quitter");
    }
    public static void logout() {
        System.out.println("Déconnexion...");
    }
    public static void invalidChoice() {
        System.out.println("Choix invalide. Veuillez réessayer.");
    }
}
