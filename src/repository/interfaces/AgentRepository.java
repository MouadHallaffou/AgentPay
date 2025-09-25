package repository.interfaces;

import java.util.List;
import java.util.Optional;
import model.Agent;

public interface AgentRepository {
    void insert(Agent agent);
    void update(Agent agent);
    boolean delete(int agentID);
    Optional<Agent> findByEmail(String email);
    Optional<Agent> findById(int agentID);
    List<Agent> findAll();
    List<Agent> findByDepartement(int departementID);
}
