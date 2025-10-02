package main.java.com.agentpay.repository.interfacesImp;

import main.java.com.agentpay.config.ConfigConnection;
import main.java.com.agentpay.exceptions.DepartementIntrouvableException;
import main.java.com.agentpay.model.Agent;
import main.java.com.agentpay.model.Departement;
import main.java.com.agentpay.model.Paiement;
import main.java.com.agentpay.model.enums.TypeAgent;
import main.java.com.agentpay.model.enums.TypePaiement;
import main.java.com.agentpay.repository.interfaces.AgentRepository;
import main.java.com.agentpay.repository.interfaces.PaiementRepository;
import main.java.com.agentpay.utils.SQLQueries;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PaiementRepositoryImp implements PaiementRepository {
    private final Connection connection;
    private final AgentRepository agentRepository;

    public PaiementRepositoryImp(Connection connection,
            AgentRepository agentRepository) {
        this.connection = ConfigConnection.getConnection();
        this.agentRepository = agentRepository;
    }

    @Override
    public boolean insert(Paiement entity) {
//        System.out.println("Date format: " +  new SimpleDateFormat("yyyy-MM-dd H:m:s").format(entity.getDatePaiement()));
        try {
            PreparedStatement statement = connection.prepareStatement(SQLQueries.insertInto(
                    "paiements", "type_paiement", "montant", "datePaiement", "motif", "agentID"));
            statement.setString(1, entity.getTypePaiement().name());
            statement.setDouble(2, entity.getMontant());
            statement.setString(3, new SimpleDateFormat("yyyy-MM-dd H:m:s").format(entity.getDatePaiement()));
            statement.setString(4, entity.getMotif());
            statement.setInt(5, entity.getAgent().getUserID());

            int rowsAffected = statement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            System.out.println("Error:" + e.getMessage());
//            System.out.println("Erreur lors de l'insertion du paiement: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean update(Paiement entity) {
        try {
            PreparedStatement statement = connection.prepareStatement(SQLQueries.updateQuery(
                    "paiements", "paiementID", "type_paiement", "datePaiement", "motif", "montant", "agentID"));
            statement.setString(1, entity.getTypePaiement().name());
            statement.setDate(2, new java.sql.Date(entity.getDatePaiement().getTime()));
            statement.setString(3, entity.getMotif());
            statement.setDouble(4, entity.getMontant());
            statement.setInt(5, entity.getAgent().getUserID());
            statement.setInt(6, entity.getPaiementID());

            int rowsAffected = statement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean delete(int id) {
        try {
            PreparedStatement statement = connection.prepareStatement(SQLQueries.deleteById("paiements", "paiementID"));
            statement.setInt(1, id);
            int rowsAffected = statement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            System.out.println("Erreur lors de la suppression du paiement: " + e.getMessage());
            return false;
        }
    }

    @Override
    public Optional<Paiement> findById(int id) {
        try {
            PreparedStatement statement = connection
                    .prepareStatement(SQLQueries.selectByField("paiements", "paiementID"));
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                Paiement paiement = new Paiement();
                paiement.setPaiementID(resultSet.getInt("paiementID"));
                paiement.setTypePaiement(TypePaiement.valueOf(resultSet.getString("type_paiement")));
                paiement.setMontant(resultSet.getDouble("montant"));
                paiement.setDatePaiement(resultSet.getDate("datePaiement"));
                paiement.setMotif(resultSet.getString("motif"));
                paiement.setAgent(agentRepository.findById(resultSet.getInt("agentID")).orElse(null));
                return Optional.of(paiement);
            }
        } catch (SQLException e) {
            System.out.println("Erreur lors de la recherche du paiement par ID: " + e.getMessage());
        }
        return Optional.empty();
    }

    @Override
    public List<Paiement> findAll() throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement(SQLQueries.selectAll("paiements"));) {
            ResultSet resultSet = statement.executeQuery();
            var paiements = new ArrayList<Paiement>();
            while (resultSet.next()) {
                Paiement paiement = new Paiement();
                paiement.setPaiementID(resultSet.getInt("paiementID"));
                paiement.setTypePaiement(TypePaiement.valueOf(resultSet.getString("type_paiement")));
                paiement.setDatePaiement(resultSet.getDate("datePaiement"));
                paiement.setMontant(resultSet.getDouble("montant"));
                paiement.setMotif(resultSet.getString("motif"));
                paiement.setAgent(agentRepository.findById(resultSet.getInt("agentID")).orElse(null));
                paiements.add(paiement);
            }
            return paiements;
        } catch (SQLException e) {
            System.out.println("Erreur lors de la récupération des paiements: " + e.getMessage());
        }
        return java.util.Collections.emptyList();
    }

    @Override
    public List<Paiement> getPaiementByDepartement() throws SQLException{
        List<Paiement> paiementList = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(SQLQueries.selectPaiementByDepartement())) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Departement departement = new Departement();
                departement.setDepartementID(resultSet.getInt("departementID"));
                departement.setName(resultSet.getString(2));

                Agent agent = new Agent();
                agent.setUserID(resultSet.getInt("agentID"));
                agent.setFirstName(resultSet.getString("firstName"));
                agent.setLastName(resultSet.getString("lastName"));
                agent.setTypeAgent(TypeAgent.valueOf(resultSet.getString("type_agent")));
                agent.setDepartement(departement);

                Paiement paiement = new Paiement();
                paiement.setPaiementID(resultSet.getInt("paiementID"));
                paiement.setMontant(resultSet.getDouble("montant"));
                paiement.setDatePaiement(resultSet.getDate("datePaiement"));
                paiement.setAgent(agent);

                paiementList.add(paiement);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return paiementList;
    }

//    public static void main(String[] args) {
//        Connection connection1 = ConfigConnection.getConnection();
//        AgentRepository agentRepository1=new AgentRepositoryImp();
//        PaiementRepositoryImp paiementRepositoryim = new PaiementRepositoryImp(ConfigConnection.getConnection(),agentRepository1 );
//        paiementRepositoryim.findAll().forEach(System.out::println);
//        paiementRepositoryim.getPaiementByDepartement().forEach(System.out::println);
//    }

}
