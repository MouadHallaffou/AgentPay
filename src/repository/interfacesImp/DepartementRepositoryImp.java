package repository.interfacesImp;

import config.ConfigConnection;
import model.Departement;
import repository.interfaces.DepartementRepository;
import utils.SQLQueries;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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
    public Optional<Departement> findById(int departementID) {
        try (PreparedStatement statement = connection.prepareStatement(SQLQueries.selectByField("departements", "departementID"))){
            statement.setInt(1, departementID);
            var resultSet = statement.executeQuery();
            if (resultSet.next()){
                Departement departement = new Departement();
                departement.setDepartementID(resultSet.getInt("departementID"));
                departement.setName(resultSet.getString("name"));
                return Optional.of(departement);
            }else {
                return Optional.empty();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return Optional.empty();
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
        String query = SQLQueries.deleteById("departements", departementID);
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            int affectedRow = statement.executeUpdate();
            return affectedRow > 0;
        }catch (Exception e){
            e.printStackTrace();
            return  false;
        }
    }

    @Override
    public List<Departement> findAll() {
        List<Departement> departementList = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(SQLQueries.selectAll("departements"))){
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                Departement departement = new Departement();
                departement.setDepartementID(resultSet.getInt("departementID"));
                departement.setName(resultSet.getString("name"));
                departementList.add(departement);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return departementList;
    }

    @Override
    public boolean findByName(String name){
        try (PreparedStatement statement = connection.prepareStatement(SQLQueries.selectByField("departements","name"))){
            statement.setString(1, name);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()){
                Departement departement = new Departement();
                departement.setName((resultSet.getString("name")));
                return true;
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return false;
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
//        departement.setName("Informatique");
//        departementRepositoryImp.insert(departement);
//        System.out.println("Departement created successfully");
//        System.out.println(departementRepositoryImp.findAll());
//        System.out.println(departementRepositoryImp.findById(3));
//        if(departementRepositoryImp.findByName("data")){
//            System.out.println(" existe ");
//        }else {
//            System.out.println("nout exist");
//        }
    }
}
