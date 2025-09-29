package main.java.com.agentpay.repository.interfacesImp;

import main.java.com.agentpay.model.Paiement;
import main.java.com.agentpay.model.enums.TypePaiement;
import main.java.com.agentpay.repository.interfaces.PaiementRepository;
import main.java.com.agentpay.utils.SQLQueries;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import java.util.Optional;

public class PaiementRepositoryImp implements PaiementRepository {
    private final Connection connection;
    private final main.java.com.agentpay.repository.interfaces.AgentRepository agentRepository;

    public PaiementRepositoryImp(Connection connection,
            main.java.com.agentpay.repository.interfaces.AgentRepository agentRepository) {
        this.connection = connection;
        this.agentRepository = agentRepository;
    }

    @Override
    public boolean insert(Paiement entity) {
        try {
            PreparedStatement statement = connection.prepareStatement(SQLQueries.insertInto(
                    "paiements", "typePaiement", "datePaiement", "motif", "agentID"));
            statement.setString(1, entity.getTypePaiement().name());
            statement.setDate(2, new java.sql.Date(entity.getDatePaiement().getTime()));
            statement.setString(3, entity.getMotif());
            statement.setInt(4, entity.getAgent().getUserID());
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
                    "paiements", "paiementID", "typePaiement", "datePaiement", "motif", "agentID"));
            statement.setString(1, entity.getTypePaiement().name());
            statement.setDate(2, new java.sql.Date(entity.getDatePaiement().getTime()));
            statement.setString(3, entity.getMotif());
            statement.setInt(4, entity.getAgent().getUserID());
            statement.setInt(5, entity.getPaiemantID());
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
                paiement.setPaiemantID(resultSet.getInt("paiementID"));
                paiement.setTypePaiement(TypePaiement.valueOf(resultSet.getString("typePaiement")));
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
        try {
            PreparedStatement statement = connection.prepareStatement(SQLQueries.selectAll("paiements"));
            ResultSet resultSet = statement.executeQuery();
            var paiements = new java.util.ArrayList<Paiement>();
            while (resultSet.next()) {
                Paiement paiement = new Paiement();
                paiement.setPaiemantID(resultSet.getInt("paiementID"));
                paiement.setTypePaiement(TypePaiement.valueOf(resultSet.getString("typePaiement")));
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
}
