package main.java.com.agentpay.service;

import main.java.com.agentpay.exceptions.PaiementInvalideException;
import main.java.com.agentpay.exceptions.ValidationException;
import main.java.com.agentpay.model.Paiement;
import main.java.com.agentpay.repository.interfaces.PaiementRepository;
import main.java.com.agentpay.service.interfaces.PaiementService;
import main.java.com.agentpay.utils.Validation;
import java.util.List;
import java.util.Optional;
import java.sql.SQLException;

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
        try {
            return paiementRepository.insert(paiement);
        } catch (SQLException e) {
            throw new RuntimeException("Erreur lors de l'insertion du paiement", e);
        }
    }

    // getByID
    public Optional<Paiement> obtenirPaiement(int id) throws PaiementInvalideException {
        if (id <= 0)
            throw new PaiementInvalideException("ID de paiement invalide");
        try {
            return paiementRepository.findById(id);
        } catch (SQLException e) {
            throw new RuntimeException("Erreur lors de la récupération du paiement", e);
        }
    }

    // Modifier::
    public boolean modifierPaiement(Paiement paiement) throws ValidationException {
        if (paiement.getPaiementID() <= 0) {
            throw new PaiementInvalideException("ID de paiement invalide");
        }
        if (paiement.getTypePaiement() == null) {
            throw new PaiementInvalideException("Type de paiement obligatoire");
        }
        if (paiement.getMontant() <= 0) {
            throw new PaiementInvalideException("Le montant doit être positif");
        }
        switch (paiement.getTypePaiement()) {
            case SALAIRE -> {
                if (!Validation.salaireValide(paiement.getMontant())) {
                    throw new PaiementInvalideException("Montant de salaire invalide");
                 }
            } 
            case BONUS, INDEMNITE, PRIME -> {
                //   
            }
        }
        try {
            return paiementRepository.update(paiement);
        } catch (SQLException e) {
            throw new RuntimeException("Erreur lors de la modification du paiement", e);
        }
    }

    // delete::
    public boolean supprimerPaiement(int id) throws PaiementInvalideException {
        if (id <= 0)
            throw new PaiementInvalideException("ID de paiement invalide");
        try {
            return paiementRepository.delete(id);
        } catch (SQLException e) {
            throw new RuntimeException("Erreur lors de la suppression du paiement", e);
        }
    }

    @Override
    public List<Paiement> getPaiementTotalByDepartement() throws PaiementInvalideException {
        try {
            List<Paiement> paiements = paiementRepository.getPaiementByDepartement();
            if (paiements.isEmpty()){
                throw new PaiementInvalideException("Aucun paiement trouvé pour ce département");
            }
            return paiements;
        } catch (SQLException e) {
            throw new RuntimeException("Erreur lors de la récupération des paiements par département", e);
        }
    }

    @Override
    public List<Paiement> getAllPaiement() throws PaiementInvalideException {
        try {
            List<Paiement> paiements = paiementRepository.findAll();
            if (paiements.isEmpty()){
                throw new PaiementInvalideException("Aucun paiement trouvé");
            }
            return paiements;
        } catch (SQLException e) {
            throw new RuntimeException("Erreur lors de la récupération de tous les paiements", e);
        }
    }

}
