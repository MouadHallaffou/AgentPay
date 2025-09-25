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
                "UPDATE departements SET name = ? WHERE departementID = ?")) {
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
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                "DELETE FROM departements WHERE departementID = ?")) {
            preparedStatement.setInt(1, departementID);
            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Optional<Departement> findById(int departementID) {
        try (PreparedStatement statement = connection.prepareStatement(
                "SELECT * FROM departements WHERE departementID = ?")) {
            statement.setInt(1, departementID);
            var resultSet = statement.executeQuery();
            if (resultSet.next()) {
                Departement departement = new Departement();
                departement.setDepartementID(resultSet.getInt("departementID"));
                departement.setName(resultSet.getString("name"));
                return Optional.of(departement);
            } else {
                return Optional.empty();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }

    @Override
    public List<Departement> findAll() {
        List<Departement> departements = new java.util.ArrayList<>();
        String query = "SELECT * FROM departements";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            var resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Departement departement = new Departement();
                departement.setDepartementID(resultSet.getInt("departementID"));
                departement.setName(resultSet.getString("name"));
                departements.add(departement);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return departements;
    }

    public static void main(String[] args) throws SQLException {
        DepartementRepositoryImp departementRepositoryImp = new DepartementRepositoryImp();
        Departement departement = new Departement();
        // departement.setName("Informatique");
        // departementRepositoryImp.insert(departement);
        // System.out.println("Departement created successfully");
        departement.setDepartementID(1);
        departement.setName("DATA");
        departementRepositoryImp.update(departement);
        System.out.println("Departement " + departement.getName() + " updated successfully");
    }
}
