package main.java.com.agentpay.repository.interfaces;

import java.sql.SQLException;

import main.java.com.agentpay.model.Departement;

public interface DepartementRepository extends GenericRepository<Departement> {
    // Hérite des méthodes génériques: insert, update, delete, findById, findAll
    boolean findByName(String name) throws SQLException;
}
