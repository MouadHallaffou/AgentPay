package repository.interfaces;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import model.Agent;

public interface AgentRepository {
    Optional<Agent> findByEmail(String email);
    void insert(Agent agent);
    Optional<Agent> findById(int id);
    void update(Agent agent);
    void delete(int id);
    List<Agent> findAll();
    List<Agent> findByDepartement(Long idDepartement);
}
