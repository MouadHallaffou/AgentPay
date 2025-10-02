package main.java.com.agentpay.view;

import main.java.com.agentpay.model.Paiement;

public class PaiementView {

    public void displayPaiement(Paiement paiement) {
        System.out.println("Paiement de " + paiement.getMontant() + " pour l'agent ID " + paiement.getAgent().getUserID());
    }

}
