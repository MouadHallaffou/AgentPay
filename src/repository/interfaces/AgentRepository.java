package repository.interfaces;

import java.util.List;
import java.util.Optional;
import model.Agent;

public interface AgentRepository {
    Optional<Agent> findByEmail(String email);
    void insert(Agent agent);
    Optional<Agent> findById(int agentID);
    void update(Agent agent);
    void delete(int agentID);
    List<Agent> findAll();
    List<Agent> findByDepartement(int departementID);
}
