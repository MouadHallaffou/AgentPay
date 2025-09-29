package main.java.com.agentpay.service;

import main.java.com.agentpay.model.Paiement;
import main.java.com.agentpay.repository.interfaces.PaiementRepository;

import java.sql.PreparedStatement;

public class ServicePaiement {
    private final PaiementRepository paiementRepository;

    public ServicePaiement(PaiementRepository paiementRepository){
        this.paiementRepository = paiementRepository;
    }

    // create paiement:
    public boolean createPaiement(Paiement paiement) {
        try {

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
