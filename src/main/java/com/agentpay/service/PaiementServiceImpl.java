package main.java.com.agentpay.service;

import main.java.com.agentpay.exceptions.ValidationException;
import main.java.com.agentpay.model.Paiement;
import main.java.com.agentpay.repository.interfaces.PaiementRepository;
import main.java.com.agentpay.service.interfaces.PaiementService;
import main.java.com.agentpay.utils.Validation;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public class PaiementServiceImpl implements PaiementService{
    private final PaiementRepository paiementRepository;

    public PaiementServiceImpl(PaiementRepository paiementRepository) {
        this.paiementRepository = paiementRepository;
    }

    // Enregistrer
    public boolean enregistrerPaiement(Paiement paiement) throws ValidationException {
        if (paiement.getTypePaiement() == null) {
            throw new IllegalArgumentException("Type de paiement obligatoire");
        }
        if (paiement.getMontant() <= 0) {
            throw new IllegalArgumentException("Le montant doit être positif");
        }
        switch (paiement.getTypePaiement()) {
            case SALAIRE -> {
                if (!Validation.salaireValide(paiement.getMontant())) {
                    throw new IllegalArgumentException("Montant de salaire invalide");
                }
            }
            case BONUS, INDEMNITE, PRIME -> {
                //
            }
        }
        return paiementRepository.insert(paiement);
    }

    // getByID
    public Optional<Paiement> obtenirPaiement(int id) {
        if (id <= 0)
            throw new IllegalArgumentException("ID de paiement invalide");
        return paiementRepository.findById(id);
    }

    // Modifier::
    public boolean modifierPaiement(Paiement paiement) {
        if (paiement.getPaiementID() <= 0) {
            throw new IllegalArgumentException("ID de paiement invalide");
        }
        if (paiement.getTypePaiement() == null) {
            throw new IllegalArgumentException("Type de paiement obligatoire");
        }
        if (paiement.getMontant() <= 0) {
            throw new IllegalArgumentException("Le montant doit être positif");
        }
        switch (paiement.getTypePaiement()) {
            case SALAIRE -> {
                if (!Validation.salaireValide(paiement.getMontant())) {
                    throw new IllegalArgumentException("Montant de salaire invalide");
                 }
            } 
            case BONUS, INDEMNITE, PRIME -> {
                //   
            }
        }
        return paiementRepository.update(paiement);
    }

    // delete::
    public boolean supprimerPaiement(int id) {
        if (id <= 0)
            throw new IllegalArgumentException("ID de paiement invalide");
        return paiementRepository.delete(id);
    }

    @Override
    public List<Paiement> getPaiementTotalByDepartement() {
        List<Paiement> paiements = paiementRepository.getPaiementByDepartement();
        if (paiements.isEmpty()){
            throw new RuntimeException();
        }
        return paiements;
    }

    @Override
    public List<Paiement> getAllPaiement() {
        List<Paiement> paiements = paiementRepository.findAll();
        if (paiements.isEmpty()){
            throw new RuntimeException();
        }
        return paiements;
    }

}
