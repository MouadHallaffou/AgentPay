package repository.interfaces;

import model.Departement;
import java.util.List;
import java.util.Optional;

public interface DepartementRepository {
    boolean create(Departement departement);
    boolean update(Departement departement);
    boolean delete(int departementID);
    Optional<Departement> findById(int departementID);
    List<Departement> findAll();
}
