package main.java.com.agentpay.repository.interfaces;

import main.java.com.agentpay.model.Paiement;

public interface PaiementRepository extends GenericRepository<Paiement> {
    // Hérite des méthodes génériques: insert, update, delete, findById, findAll    
}
