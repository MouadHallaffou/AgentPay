package main.java.com.agentpay.service.interfaces;

import java.util.List;
import java.util.Optional;
import main.java.com.agentpay.model.Paiement;

public interface PaiementService {
    public boolean enregistrerPaiement(Paiement paiement);

    public Optional<Paiement> obtenirPaiement(int id);

    public boolean modifierPaiement(Paiement paiement);

    public boolean supprimerPaiement(int id);

    List<Paiement> getPaiementTotalByDepartement();

    List<Paiement> getAllPaiement();
}
