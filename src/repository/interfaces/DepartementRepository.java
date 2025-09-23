package repository.interfaces;

import model.Departement;
import java.util.List;
import java.util.Optional;

public interface DepartementRepository {
    boolean create(Departement departement);
    boolean update(Departement departement);
    boolean delete(int idDepartement);
    Optional<Departement> findById(int idDepartement);
    List<Departement> findAll();
}
