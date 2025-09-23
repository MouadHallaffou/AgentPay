package service;

import model.Agent;

public class MenuService {

    public void afficherMenu(Agent agent) {
        switch(agent.getTypeAgent()) {
            case DIRECTEUR:
                afficherMenuDirecteur();
                break;
            case RESPONSABLE_DEPARTEMENT:
                afficherMenuResponsable();
                break;
            case OUVRIER:
            case STAGIAIRE:
                afficherMenuAgent();
                break;
        }
    }

    private void afficherMenuDirecteur() {
        System.out.println("1. Créer un département");
        System.out.println("2. Assigner responsable");
        System.out.println("3. Consulter tous les agents");
        System.out.println("4. Statistiques globales");
        System.out.println("0. Quitter");
    }

    private void afficherMenuResponsable() {
        System.out.println("1. Ajouter un agent");
        System.out.println("2. Modifier un agent");
        System.out.println("3. Ajouter paiement");
        System.out.println("4. Consulter paiements département");
        System.out.println("0. Quitter");
    }

    private void afficherMenuAgent() {
        System.out.println("1. Consulter mes informations");
        System.out.println("2. Historique paiements");
        System.out.println("3. Total paiements");
        System.out.println("0. Quitter");
    }
}

