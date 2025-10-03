package main.java.com.agentpay.repository.interfaces;

import main.java.com.agentpay.model.Paiement;
import java.sql.SQLException;
import java.util.List;

public interface PaiementRepository extends GenericRepository<Paiement> {
    // Hérite des méthodes génériques: insert, update, delete, findById, findAll
    List<Paiement> getPaiementByDepartement() throws SQLException;
    boolean updateConditionValidee(Paiement entity);
}
