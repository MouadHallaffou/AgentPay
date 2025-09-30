package main.java.com.agentpay.service.interfaces;

import java.util.List;
import java.util.Optional;
import main.java.com.agentpay.model.Departement;

public interface DepartementService {
    public boolean createDepartement(Departement departement);

    public boolean updateDepartement(Departement departement);

    public boolean deleteDepartement(int departementID);

    public Optional<Departement> findDepartementById(int departementID);

    public List<Departement> getAllDepartements();
}
