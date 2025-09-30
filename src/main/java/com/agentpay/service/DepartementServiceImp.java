package main.java.com.agentpay.service;

import java.util.*;
import java.util.Optional;
import main.java.com.agentpay.model.Departement;
import main.java.com.agentpay.repository.interfaces.DepartementRepository;
import main.java.com.agentpay.repository.interfacesImp.DepartementRepositoryImp;

public class DepartementService {
    private final DepartementRepository departementRepository;

    public DepartementService(DepartementRepository departementRepository) {
        this.departementRepository = departementRepository;
    }

    public boolean createDepartement(Departement departement) {
        try {
            boolean departementExists = departementRepository.findByName(departement.getName());
            if (departementExists) {
                throw new RuntimeException("Un département avec ce nom existe déjà!");
            }
            return departementRepository.insert(departement);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public boolean updateDepartement(Departement departement) {
        try {
            Optional<Departement> existingDepartement = departementRepository.findById(departement.getDepartementID());
            if (!existingDepartement.isPresent()) {
                throw new RuntimeException("Le département avec l'ID spécifié n'existe pas!");
            }
            return departementRepository.update(departement);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public boolean deleteDepartement(int departementID) {
        try {
            DepartementRepositoryImp departementRepositoryImp = new DepartementRepositoryImp();
            Optional<Departement> existingDepartement = departementRepositoryImp.findById(departementID);
            if (existingDepartement.isPresent()) {
                return departementRepositoryImp.delete(departementID);
            } else {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public Optional<Departement> findDepartementById(int departementID) {
        try {
            return departementRepository.findById(departementID);
        } catch (Exception e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }

    public List<Departement> getAllDepartements(){
        List<Departement> allDepartements = new ArrayList<>();
        try {
            DepartementRepositoryImp departementRepositoryImp = new DepartementRepositoryImp();
            allDepartements.addAll(departementRepositoryImp.findAll());
            return allDepartements;
        } catch (Exception e){
            e.printStackTrace();
            return Collections.emptyList();
        }
    }


}
