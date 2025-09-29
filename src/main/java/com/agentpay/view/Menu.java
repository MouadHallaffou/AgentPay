package main.java.com.agentpay.view;

public class Menu {

    // Méthodes d'affichage des menus
    public static void afficherMenuDirecteur() {
        System.out.println("=== Menu Directeur ===");
        System.out.println("1.➤ Gestion des départements");
        System.out.println("2.➤ Gestion des responsables");
        System.out.println("3.➤ Consultation des agents");
        System.out.println("4.➤ Statistiques globales");
        System.out.println("0.➤ Déconnexion");
    }

    public static void afficherGestionDepartement() {
        System.out.println("=== Gestion Départements ===");
        System.out.println("1.➤ Ajouter département");
        System.out.println("2.➤ Modifier département");
        System.out.println("3.➤ Supprimer département");
        System.out.println("4.➤ Voir tous les départements");
        System.out.println("0.➤ Retour");
        System.out.print("Votre choix: ");
    }

    public static void afficherMenuGestionResponsable() {
        System.out.println("=== Gestion Responsables ===");
        System.out.println("1.➤ Ajouter un responsable");
        System.out.println("2.➤ Modifier un responsable");
        System.out.println("3.➤ Supprimer un responsable");
        System.out.println("4.➤ Désactiver un responsable");
        System.out.println("5.➤ Voir tous les responsables");
        System.out.println("6.➤ Gérer les paiements des responsables");
        System.out.println("0.➤ Retour");
        System.out.print("Votre choix: ");
    }

    public static void afficherMenuAllAgents() {
        System.out.println("=== Consultation des Agents ===");
        System.out.println("1.➤ Voir tous les agents");
        System.out.println("2.➤ Rechercher un agent par ID");
        System.out.println("3.➤ Filtrer par département");
        System.out.println("4.➤ Filtrer par rôle");
        System.out.println("0.➤ Retour");
    }

    public static void afficherMenuStatistiquesDirecteur() {
        System.out.println("=== Statistiques Globales ===");
        System.out.println("1.➤ Nombre total d'agents par rôle");
        System.out.println("2.➤ Total des paiements par département");
        System.out.println("3.➤ Moyenne des paiements par type");
        System.out.println("4.➤ Historique des paiements");
        System.out.println("5.➤ Identifier paiements inhabituels");
        System.out.println("0.➤ Retour");
    }

    // Menu pour les responsables
    public static void afficherMenuResponsable() {
        System.out.println("=== Menu Responsable ===");
        System.out.println("1.➤ Gestion des agents");
        System.out.println("2.➤ Gestion des paiements");
        System.out.println("3.➤ Statistiques du département");
        System.out.println("0.➤ Déconnexion");
    }

    public static void afficherMenuGestionAgentsResponsable() {
        System.out.println("=== Gestion des Agents ===");
        System.out.println("1.➤ Ajouter un agent (Ouvrier/Stagiaire)");
        System.out.println("2.➤ Modifier un agent");
        System.out.println("3.➤ Supprimer un agent");
        System.out.println("4.➤ Voir tous les agents du département");
        System.out.println("5.➤ Rechercher un agent");
        System.out.println("0.➤ Retour");
        System.out.print("Votre choix: ");
    }

    public static void afficherMenuGestionPaiementsResponsable() {
        System.out.println("=== Gestion des Paiements ===");
        System.out.println("1.➤ Ajouter un salaire");
        System.out.println("2.➤ Ajouter une prime");
        System.out.println("3.➤ Ajouter un bonus");
        System.out.println("4.➤ Ajouter une indemnité");
        System.out.println("5.➤ Consulter les paiements d'un agent");
        System.out.println("6.➤ Filtrer les paiements");
        System.out.println("0.➤ Retour");
    }

    public static void afficherMenuStatistiquesResponsable() {
        System.out.println("=== Statistiques Département ===");
        System.out.println("1.➤ Total des agents du département");
        System.out.println("2.➤ Total des paiements du département");
        System.out.println("3.➤ Moyenne des paiements par agent");
        System.out.println("4.➤ Historique des paiements du département");
        System.out.println("0.➤ Retour");
    }

    // Menu pour les agents (ouvriers ou stagiaires)
    public static void afficherMenuAgent() {
        System.out.println("=== Menu Agent ===");
        System.out.println("1.➤ Consulter mes informations personnelles");
        System.out.println("2.➤ Historique de mes paiements");
        System.out.println("3.➤ Filtrer mes paiements");
        System.out.println("4.➤ Total de mes paiements");
        System.out.println("0.➤ Déconnexion");
    }

    public static void afficherMenuFiltragePaiementsAgent() {
        System.out.println("=== Filtrer mes paiements ===");
        System.out.println("1.➤ Par type (Salaire/Prime/Bonus/Indemnité)");
        System.out.println("2.➤ Par montant");
        System.out.println("3.➤ Par date");
        System.out.println("0.➤ Retour");
    }

    public static void logout() {
        System.out.println("Déconnexion...");
    }

    public static void invalidChoice() {
        System.out.println("Choix invalide. Veuillez réessayer.");
    }

}