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

    public DepartementRepositoryImp(Connection connection){
        this.connection = connection;
    }

    @Override
    public boolean create(Departement departement) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQLQueries.insertInto("departements", "name"))){
            preparedStatement.setString(1, departement.getName());
            preparedStatement.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean update(Departement departement) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQLQueries.updateQuery("departements", "name", "departementID"))) {
            preparedStatement.setInt(1, departement.getDepartementID());
            preparedStatement.setString(2, departement.getName());
            preparedStatement.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean delete(int departementID) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQLQueries.deleteById("departements", departementID))) {
            preparedStatement.setInt(1, departementID);
            preparedStatement.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
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
        ConfigConnection.getInstance();
        Connection con = ConfigConnection.getConnection();
        DepartementRepositoryImp departementRepositoryImp = new DepartementRepositoryImp(con);
        Departement departement = new Departement();
        // departement.setName("Informatique");
        // departementRepositoryImp.create(departement);
        // System.out.println("Departement created successfully");
        departement.setDepartementID(1);
        departement.setName("DATA");
        departementRepositoryImp.update(departement);
        System.out.println("Departement " + departement.getName() + " updated successfully");
    }
}
