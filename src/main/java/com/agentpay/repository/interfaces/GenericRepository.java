package main.java.com.agentpay.repository.interfaces;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface GenericRepository<T>{
    boolean insert(T entity) throws SQLException;
    boolean update(T entity) throws SQLException;
    boolean delete(int id) throws SQLException;
    Optional<T> findById(int id) throws SQLException;
    List<T> findAll() throws SQLException;
}
