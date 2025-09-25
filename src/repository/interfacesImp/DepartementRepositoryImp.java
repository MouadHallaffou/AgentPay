package repository.interfacesImp;

import config.ConfigConnection;
import model.Departement;
import repository.interfaces.DepartementRepository;
import utils.SQLQueries;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class DepartementRepositoryImp implements DepartementRepository {
    private final Connection connection;

    public DepartementRepositoryImp(Connection connection) {
        this.connection = connection;
    }

    public DepartementRepositoryImp() {
        this.connection = ConfigConnection.getConnection();
    }

    @Override
    public boolean insert(Departement departement) {
        try (PreparedStatement preparedStatement = connection
                .prepareStatement(SQLQueries.insertInto("departements", "name"))) {
            preparedStatement.setString(1, departement.getName());
            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean update(Departement departement) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(
            SQLQueries.updateQuery("departements", "departementID", "name"))) {
            preparedStatement.setString(1, departement.getName());
            preparedStatement.setInt(2, departement.getDepartementID());
            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean delete(int departementID) {
        return false;
    }

    @Override
    public Optional<Departement> findById(int departementID) {
        return Optional.empty();
    }

    @Override
    public List<Departement> findAll() {
        return List.of();
    }

    public static void main(String[] args) throws SQLException {
        try {
            ConfigConnection.getInstance();
            Connection connection = ConfigConnection.getConnection();
            System.out.println("Connected to the database successfully!");
        } catch (SQLException e) {
            System.err.println("Database connection failed: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("An error occurred: " + e.getMessage());
        }
        DepartementRepositoryImp departementRepositoryImp = new DepartementRepositoryImp();
        Departement departement = new Departement();
        // departement.setName("Informatique");
        // departementRepositoryImp.insert(departement);
        // System.out.println("Departement created successfully");
        departement.setDepartementID(1);
        departement.setName("Informatique et Reseau");
        departementRepositoryImp.update(departement);
        System.out.println("Departement " + departement.getName() + " updated successfully");
    }
}
