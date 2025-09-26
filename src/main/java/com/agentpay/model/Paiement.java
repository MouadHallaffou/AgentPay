package main.java.com.agentpay.model;

import main.java.com.agentpay.model.enums.TypePaiement;
import java.util.Date;

public class Paiement {
    private int paiemantID;
    private TypePaiement typePaiement;
    private Date datePaiement;
    private String motif;
    private Agent agent;

    public Paiement() {
    }

    public Paiement(int paiemantID, TypePaiement typePaiement, Date datePaiement, String motif, Agent agent) {
        paiemantID = paiemantID;
        this.typePaiement = typePaiement;
        this.datePaiement = datePaiement;
        this.motif = motif;
        this.agent = agent;
    }

    public int getPaiemantID() {
        return paiemantID;
    }

    public void setPaiemantID(int paiemantID) {
        paiemantID = paiemantID;
    }

    public TypePaiement getTypePaiement() {
        return typePaiement;
    }

    public void setTypePaiement(TypePaiement typePaiement) {
        this.typePaiement = typePaiement;
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

}
