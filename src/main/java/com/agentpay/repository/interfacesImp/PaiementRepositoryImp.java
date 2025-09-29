package main.java.com.agentpay.repository.interfacesImp;

import main.java.com.agentpay.config.ConfigConnection;
import main.java.com.agentpay.model.Paiement;
import main.java.com.agentpay.model.enums.TypePaiement;
import main.java.com.agentpay.repository.interfaces.AgentRepository;
import main.java.com.agentpay.repository.interfaces.PaiementRepository;
import main.java.com.agentpay.utils.SQLQueries;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public class PaiementRepositoryImp implements PaiementRepository {
    private final Connection connection;
    private final AgentRepository agentRepository;

    public PaiementRepositoryImp(Connection connection,
            AgentRepository agentRepository) {
        this.connection = connection;
        this.agentRepository = agentRepository;
    }

    @Override
    public boolean insert(Paiement entity) {
        try {
            PreparedStatement statement = connection.prepareStatement(SQLQueries.insertInto(
                    "paiements", "type_paiement", "montant", "datePaiement", "motif", "agentID"));
            statement.setString(1, entity.getTypePaiement().name());
            statement.setDouble(2, entity.getMontant());
            statement.setDate(3, new java.sql.Date(entity.getDatePaiement().getTime()));
            statement.setString(4, entity.getMotif());
            statement.setInt(5, entity.getAgent().getUserID());

            int rowsAffected = statement.executeUpdate();
            return rowsAffected > 0;
        } catch (Exception e) {
            System.out.println("Erreur lors de l'insertion du paiement: " + e.getMessage());
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
        } catch (Exception e) {
            System.out.println("Erreur lors de la mise à jour du paiement: " + e.getMessage());
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
        } catch (Exception e) {
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
        } catch (Exception e) {
            System.out.println("Erreur lors de la recherche du paiement par ID: " + e.getMessage());
        }
        return Optional.empty();
    }

    @Override
    public List<Paiement> findAll() {
        try (PreparedStatement statement = connection.prepareStatement(SQLQueries.selectAll("paiements"));) {
            ResultSet resultSet = statement.executeQuery();
            var paiements = new java.util.ArrayList<Paiement>();
            while (resultSet.next()) {
                Paiement paiement = new Paiement();
                paiement.setPaiementID(resultSet.getInt("paiementID"));
                paiement.setTypePaiement(TypePaiement.valueOf(resultSet.getString("type_paiement")));
                paiement.setDatePaiement(resultSet.getDate("datePaiement"));
                paiement.setMotif(resultSet.getString("motif"));
                paiement.setAgent(agentRepository.findById(resultSet.getInt("agentID")).orElse(null));
                paiements.add(paiement);
            }
            return paiements;
        } catch (Exception e) {
            System.out.println("Erreur lors de la récupération des paiements: " + e.getMessage());
        }
        return java.util.Collections.emptyList();
    }

    public static void main(String[] args) {
        try {
            ConfigConnection.getInstance();
            System.out.println("Connected to the database successfully!");
        } catch (SQLException e) {
            System.out.println("Database connection failed: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        }

        Paiement paiement = new Paiement();
        Connection connection = ConfigConnection.getConnection();
        AgentRepositoryImp agentRepositoryImp = new AgentRepositoryImp();
        PaiementRepositoryImp paiementRepositoryImp = new PaiementRepositoryImp(connection, agentRepositoryImp);

        paiement.setTypePaiement(TypePaiement.SALAIRE);
        paiement.setMontant(12000);
        paiement.setDatePaiement(new Date());
        paiement.setMotif("test1");
        paiement.setAgent(agentRepositoryImp.findById(1).get());
        // paiementRepositoryImp.insert(paiement);
        // System.out.println("ok");

        List<Paiement> paiements = paiementRepositoryImp.findAll();
        paiements.forEach(paiement1 -> System.out.println(paiement1.getPaiementID()));
    }
}
