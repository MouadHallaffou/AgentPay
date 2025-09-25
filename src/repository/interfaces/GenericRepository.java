package repository.interfaces;

import java.util.List;
import java.util.Optional;

public interface GenericRepository<T> {
    boolean insert(T entity);

    boolean update(T entity);

    boolean delete(int id);

    Optional<T> findById(int id);

    List<T> findAll();
}
