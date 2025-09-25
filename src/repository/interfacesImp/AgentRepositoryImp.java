package repository.interfacesImp;

import model.Agent;
import repository.interfaces.AgentRepository;
import utils.SQLQueries;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
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
        try (PreparedStatement statement = connection.prepareStatement(SQLQueries.selectByField("agents", "email"))) {
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
    public boolean insert(Agent agent) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                SQLQueries.insertInto("agents", "firstName", "lastName", "email", "password", "type_agent"))) {
            preparedStatement.setString(1, agent.getFirstName());
            preparedStatement.setString(2, agent.getLastName());
            preparedStatement.setString(3, agent.getEmail());
            preparedStatement.setString(4, agent.getPassword());
            preparedStatement.setString(5, agent.getTypeAgent().name());
            int rowsAffected = preparedStatement.executeUpdate();
            System.out.println("Agent inserted successfully");
            return rowsAffected > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Optional<Agent> findById(int id) {
        try (PreparedStatement statement = connection.prepareStatement(SQLQueries.selectByField("agents", "agentID"))) {
            statement.setInt(1, id);
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
    public boolean update(Agent agent) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                "UPDATE agents SET firstName = ?, lastName = ?, email = ?, password = ?, type_agent = ? WHERE agentID = ?")) {
            preparedStatement.setString(1, agent.getFirstName());
            preparedStatement.setString(2, agent.getLastName());
            preparedStatement.setString(3, agent.getEmail());
            preparedStatement.setString(4, agent.getPassword());
            preparedStatement.setString(5, agent.getTypeAgent().name());
            preparedStatement.setInt(6, agent.getUserID());
            int rowsAffected = preparedStatement.executeUpdate();
            System.out.println("Agent updated successfully");
            return rowsAffected > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean delete(int id) {
        String query = SQLQueries.deleteById("agents", id);
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            int rowsAffected = statement.executeUpdate();
            System.out.println("Agent deleted successfully");
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<Agent> findAll() {
        List<Agent> agents = new java.util.ArrayList<>();
        String query = SQLQueries.selectAll("agents");
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            var resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Agent agent = new Agent();
                agent.setUserID(resultSet.getInt("agentID"));
                agent.setFirstName(resultSet.getString("firstName"));
                agent.setLastName(resultSet.getString("lastName"));
                agent.setEmail(resultSet.getString("email"));
                agent.setPassword(resultSet.getString("password"));
                agent.setTypeAgent(model.enums.TypeAgent.valueOf(resultSet.getString("type_agent")));
                agents.add(agent);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return agents;
    }

    @Override
    public List<Agent> findByDepartement(int departementID) {
        return List.of();
    }

    @Override
    public boolean desactiveAgent(int agentID) {
        return false;
    }

    public static void main(String[] args) throws SQLException {
        AgentRepositoryImp agentRepositoryImp = new AgentRepositoryImp();
        Optional<Agent> agentOpt = agentRepositoryImp.findByEmail("anass@gmail.com");
        agentOpt.ifPresentOrElse(
                agent -> System.out.println("Agent found: " + agent.getFirstName()),
                () -> System.out.println("Agent not found"));

        // Agent newAgent = new Agent();
        // newAgent.setFirstName("Anass");
        // newAgent.setLastName("Hallaffou");
        // newAgent.setEmail("anass@gmail.com");
        // newAgent.setPassword("password123");
        // newAgent.setTypeAgent(model.enums.TypeAgent.OUVRIER);
        // agentRepositoryImp.insert(newAgent);

    }

}