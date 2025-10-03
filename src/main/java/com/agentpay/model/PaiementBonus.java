package main.java.com.agentpay.model;

import main.java.com.agentpay.model.enums.TypePaiement;

public class PaiementBonus extends Paiement{
    private Paiement paiement;
    public PaiementBonus() {
    }

    public PaiementBonus(int paiementID, TypePaiement typePaiement, String motif, Agent agent) {
        super(paiementID, typePaiement, motif, agent);
    }

    public void setPaiement(Paiement paiement) {
        this.paiement = paiement;
    }

    public Paiement getPaiement() {
        return paiement;
    }

}
