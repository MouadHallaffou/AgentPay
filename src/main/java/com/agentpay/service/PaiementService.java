package main.java.com.agentpay.service;

import main.java.com.agentpay.model.Paiement;
import main.java.com.agentpay.repository.interfaces.PaiementRepository;
import main.java.com.agentpay.utils.Validation;

import java.util.Optional;

public class PaiementService {
    private final PaiementRepository paiementRepository;

    public PaiementService(PaiementRepository paiementRepository) {
        this.paiementRepository = paiementRepository;
    }

    public boolean enregistrerPaiement(Paiement paiement) {
        if (Validation.salaireValide(paiement.getMontant())){

        }
        return paiementRepository.insert(paiement);
    }

    public Optional<Paiement> obtenirPaiement(int id) {
        return paiementRepository.findById(id);
    }

    public boolean modifierPaiement(Paiement paiement) {
        return paiementRepository.update(paiement);
    }

    public boolean supprimerPaiement(int id) {
        return paiementRepository.delete(id);
    }
}

