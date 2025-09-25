package repository.interfaces;

import java.util.List;
import java.util.Optional;
import model.Agent;

public interface AgentRepository extends GenericRepository<Agent> {
    // Hérite des méthodes génériques: insert, update, delete, findById, findAll

    Optional<Agent> findByEmail(String email);

    List<Agent> findByDepartement(int departementID);

    boolean desactiveAgent(int agentID);
}
