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
    public void insert(Agent agent) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQLQueries.insertInto("agents", "firstName", "lastName", "email", "password", "type_agent"))) {
            preparedStatement.setString(1, agent.getFirstName());
            preparedStatement.setString(2, agent.getLastName());
            preparedStatement.setString(3, agent.getEmail());
            preparedStatement.setString(4, agent.getPassword());
            preparedStatement.setString(5, agent.getTypeAgent().name());
            preparedStatement.executeUpdate();
            System.out.println("Agent inserted successfully");
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error inserting agent");
        }
    }


    @Override
    public Optional<Agent> findById(int id) {
        return Optional.empty();
    }

    @Override
    public void update(Agent agent) {

    }

    @Override
    public boolean delete(int id) {
        String query = "DELETE FROM agents WHERE agentID = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            statement.executeUpdate();
            System.out.println("Agent deleted successfully");
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error deleting agent");
        }
    }

    @Override
    public List<Agent> findAll() {
        return List.of();
    }

    @Override
    public List<Agent> findByDepartement(int departementID) {
        return List.of();
    }




    public static void main(String[] args) throws SQLException {
        Connection con = ConfigConnection.getInstance().getConnection();
        AgentRepositoryImp agentRepositoryImp = new AgentRepositoryImp();
        Optional<Agent> agentOpt = agentRepositoryImp.findByEmail("anass@gmail.com");
        agentOpt.ifPresentOrElse(
            agent -> System.out.println("Agent found: " + agent.getFirstName()),
            () -> System.out.println("Agent not found")
        );

//        Agent newAgent = new Agent();
//        newAgent.setFirstName("Anass");
//        newAgent.setLastName("Hallaffou");
//        newAgent.setEmail("anass@gmail.com");
//        newAgent.setPassword("password123");
//        newAgent.setTypeAgent(model.enums.TypeAgent.OUVRIER);
//        agentRepositoryImp.insert(newAgent);

    }

}
