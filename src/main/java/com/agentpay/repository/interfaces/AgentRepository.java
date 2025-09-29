package main.java.com.agentpay.repository.interfaces;

import java.util.List;
import java.util.Optional;
import main.java.com.agentpay.model.Agent;

public interface AgentRepository extends GenericRepository<Agent> {
    // Hérite des méthodes génériques: insert, update, delete, findById, findAll
    Optional<Agent> findByEmail(String email);
    List<Agent> findByDepartement(String  departementName);
    boolean setAgentAccountStatus(int agentID);
}
