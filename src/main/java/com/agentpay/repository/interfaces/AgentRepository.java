package main.java.com.agentpay.repository.interfaces;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import main.java.com.agentpay.model.Agent;
import main.java.com.agentpay.model.Paiement;

public interface AgentRepository extends GenericRepository<Agent> {
    // Hérite des méthodes génériques: insert, update, delete, findById, findAll
    Optional<Agent> findByEmail(String email);
    List<Agent> findByDepartement(String  departementName);
    boolean setAgentAccountStatus(int agentID);
    List<Paiement> findPaiementsByEmail(String email) throws SQLException;
}
