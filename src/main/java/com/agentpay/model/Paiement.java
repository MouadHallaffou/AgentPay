package main.java.com.agentpay.model;

import main.java.com.agentpay.model.enums.TypePaiement;
import java.util.Date;

public class Paiement {
    private int paiementID;
    private TypePaiement typePaiement;
    private double montant;
    private Date datePaiement;
    private String motif;
    private Agent agent;

    public Paiement() {
    }

    public Paiement(int paiementID, TypePaiement typePaiement, String motif, Agent agent) {
        this.paiementID = paiementID;
        this.typePaiement = typePaiement;
        this.datePaiement = new Date();
        this.motif = motif;
        this.agent = agent;
    }

    public int getPaiementID() {
        return paiementID;
    }

    public void setPaiementID(int paiementID) {
        this.paiementID = paiementID;
    }

    public TypePaiement getTypePaiement() {
        return typePaiement;
    }

    public void setTypePaiement(TypePaiement typePaiement) {
        this.typePaiement = typePaiement;
    }

    public double getMontant() {
        return montant;
    }

    public void setMontant(double montant) {
        this.montant = montant;
    }

    public Date getDatePaiement() {
        return datePaiement;
    }

    public void setDatePaiement(Date datePaiement) {
        this.datePaiement = datePaiement;
    }

    public String getMotif() {
        return motif;
    }

    public void setMotif(String motif) {
        this.motif = motif;
    }

    public Agent getAgent() {
        return agent;
    }

    public void setAgent(Agent agent) {
        this.agent = agent;
    }

    @Override
    public String toString() {
        return "Paiement{" +
                "paiementID=" + paiementID +
                ", typePaiement=" + typePaiement +
                ", montant=" + montant +
                ", datePaiement=" + datePaiement +
                ", motif='" + motif + '\'' +
                ", agent=" + agent +
                '}';
    }
}
