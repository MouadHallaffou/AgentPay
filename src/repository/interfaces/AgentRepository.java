package repository.interfaces;

import java.util.Optional;
import model.Agent;

public interface AgentRepository {
    Optional<Agent> findByEmail(String email);
    void save(Agent agent);
    Optional<Agent> findById(int id);
    void update(Agent agent);
    void delete(int id);
}
