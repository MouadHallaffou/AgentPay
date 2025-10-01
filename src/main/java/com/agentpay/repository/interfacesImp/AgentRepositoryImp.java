package main.java.com.agentpay.repository.interfacesImp;

import main.java.com.agentpay.model.Agent;
import main.java.com.agentpay.model.Paiement;
import main.java.com.agentpay.model.enums.TypeAgent;
import main.java.com.agentpay.repository.interfaces.AgentRepository;
import main.java.com.agentpay.utils.SQLQueries;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import main.java.com.agentpay.config.ConfigConnection;

public class AgentRepositoryImp implements AgentRepository {
    private final Connection connection;

    public AgentRepositoryImp() {
        this.connection = ConfigConnection.getConnection();
    }

    @Override
    public Optional<Agent> findByEmail(String email) {
        try (PreparedStatement statement = connection.prepareStatement(SQLQueries.selectByField("agents", "email"))) {
            statement.setString(1, email);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                Agent agent = new Agent();
                agent.setUserID(rs.getInt("agentID"));
                agent.setFirstName(rs.getString("firstName"));
                agent.setLastName(rs.getString("lastName"));
                agent.setEmail(rs.getString("email"));
                agent.setPassword(rs.getString("password"));
                agent.setTypeAgent(TypeAgent.valueOf(rs.getString("type_agent")));
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
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQLQueries.insertInto("agents", "firstName", "lastName", "email", "password", "type_agent", "departementID"))) {
            preparedStatement.setString(1, agent.getFirstName());
            preparedStatement.setString(2, agent.getLastName());
            preparedStatement.setString(3, agent.getEmail());
            preparedStatement.setString(4, agent.getPassword());
            preparedStatement.setString(5, agent.getTypeAgent().name());
            preparedStatement.setInt(6, agent.getDepartement().getDepartementID());
            int rowsAffected = preparedStatement.executeUpdate();
            System.out.println("Agent inserted successfully");
            return rowsAffected > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Optional<Agent> findById(int agentID) {
        try (PreparedStatement statement = connection.prepareStatement(SQLQueries.selectByField("agents", "agentID"))) {
            statement.setInt(1, agentID);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                Agent agent = new Agent();
                agent.setUserID(rs.getInt("agentID"));
                agent.setFirstName(rs.getString("firstName"));
                agent.setLastName(rs.getString("lastName"));
                agent.setEmail(rs.getString("email"));
                agent.setPassword(rs.getString("password"));
                agent.setTypeAgent(TypeAgent.valueOf(rs.getString("type_agent")));
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
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQLQueries.updateQuery("agents", "agentID", "firstName", "lastName", "email", "password", "type_agent", "departementID"))) {
            preparedStatement.setString(1, agent.getFirstName());
            preparedStatement.setString(2, agent.getLastName());
            preparedStatement.setString(3, agent.getEmail());
            preparedStatement.setString(4, agent.getPassword());
            preparedStatement.setString(5, agent.getTypeAgent().name());
            preparedStatement.setInt(6, agent.getDepartement().getDepartementID());
            preparedStatement.setInt(7, agent.getUserID());
            int rowsAffected = preparedStatement.executeUpdate();
            System.out.println("Agent updated successfully");
            return rowsAffected > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean delete(int agentID) {
        String query = SQLQueries.deleteById("agents", "agentID");
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, agentID);
            int rowsAffected = statement.executeUpdate();
            // System.out.println("Agent deleted successfully");
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<Agent> findAll() {
        List<Agent> agents = new ArrayList<>();
        String query = SQLQueries.selectAll("agents");
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Agent agent = new Agent();
                agent.setUserID(rs.getInt("agentID"));
                agent.setFirstName(rs.getString("firstName"));
                agent.setLastName(rs.getString("lastName"));
                agent.setEmail(rs.getString("email"));
                agent.setPassword(rs.getString("password"));
                agent.setTypeAgent(TypeAgent.valueOf(rs.getString("type_agent")));
                agents.add(agent);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return agents;
    }

    @Override
    public List<Agent> findByDepartement(String departementName) {
        List<Agent> agents = new ArrayList<>();
        String query = "SELECT a.agentID, a.firstName, a.lastName, a.email, a.password, a.type_agent " + "FROM agents a JOIN departements d ON a.departementID = d.departementID " + "WHERE d.name = ?";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, departementName.toLowerCase());
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                Agent agent = new Agent();
                agent.setUserID(rs.getInt("agentID"));
                agent.setFirstName(rs.getString("firstName"));
                agent.setLastName(rs.getString("lastName"));
                agent.setEmail(rs.getString("email"));
                agent.setPassword(rs.getString("password"));
                agent.setTypeAgent(TypeAgent.valueOf(rs.getString("type_agent")));

                agents.add(agent);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return agents;
    }

    @Override
    public boolean setAgentAccountStatus(int agentID) {
        String query = "UPDATE agents SET isActive = ? WHERE agentID = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setBoolean(1, false);
            statement.setInt(2, agentID);
            int rowsAffected = statement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<Paiement> findPaiementsByEmail(String email) throws SQLException {
        List<Paiement> paiements = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(SQLQueries.historiquePaiementAgents(email))) {
            statement.setString(1, email);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Paiement paiement = new Paiement();
                paiement.setPaiementID(resultSet.getInt("paiementID"));
                paiement.setMontant(resultSet.getDouble("montant"));
                paiement.setDatePaiement(resultSet.getDate("datePaiement"));
                Agent agent = new Agent();
                agent.setFirstName(resultSet.getString("firstName"));
                agent.setLastName(resultSet.getString("lastName"));
                paiement.setAgent(agent);
                paiements.add(paiement);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return paiements;
    }

}