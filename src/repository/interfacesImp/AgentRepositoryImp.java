package repository.interfacesImp;

import model.Agent;
import repository.interfaces.AgentRepository;
import utils.SQLQueries;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;
import java.util.Optional;

import config.ConfigConnection;

public class AgentRepositoryImp implements AgentRepository {
    private final Connection connection;
    
    public AgentRepositoryImp() {
        this.connection = ConfigConnection.getConnection();
    }

    @Override
    public Optional<Agent> findByEmail(String email) {
        // String query = "SELECT * FROM agent WHERE email = ?";
        try (PreparedStatement statement = connection.prepareStatement(SQLQueries.FIND_AGENT_BY_EMAIL)) {
            statement.setString(1, email);
            var resultSet = statement.executeQuery();
            if (resultSet.next()) {
                Agent agent = new Agent();
                agent.setUserID(resultSet.getInt("agentID"));
                agent.setFirstName(resultSet.getString("firstName"));
                agent.setLastName(resultSet.getString("lastName"));
                agent.setEmail(resultSet.getString("email"));
                agent.setPassword(resultSet.getString("password"));
                agent.setTypeAgent(model.enums.TypeAgent.valueOf(resultSet.getString("type_agent")));
                return Optional.of(agent);
            } else {
                return Optional.empty();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return Optional.empty();

        }
    }

    @Override
    public void insert(Agent agent) {

    }

    @Override
    public Optional<Agent> findById(int id) {
        return Optional.empty();
    }

    @Override
    public void update(Agent agent) {

    }

    @Override
    public void delete(int id) {

    }

    @Override
    public List<Agent> findAll() {
        return List.of();
    }

    @Override
    public List<Agent> findByDepartement(Long idDepartement) {
        return List.of();
    }
}
