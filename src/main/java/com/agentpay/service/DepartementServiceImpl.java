package main.java.com.agentpay.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import main.java.com.agentpay.model.Departement;
import main.java.com.agentpay.repository.interfaces.DepartementRepository;
import main.java.com.agentpay.service.interfaces.DepartementService;

public class DepartementServiceImpl implements DepartementService {
    private final DepartementRepository departementRepository;

    public DepartementServiceImpl(DepartementRepository departementRepository) {
        this.departementRepository = departementRepository;
    }

    // create
    @Override
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

    // update
    @Override
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

    // delete
    @Override
    public boolean deleteDepartement(int departementID) {
        try {
            Optional<Departement> existingDepartement = departementRepository.findById(departementID);
            if (existingDepartement.isPresent()) {
                return departementRepository.delete(departementID);
            } else {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // findbyID
    @Override
    public Optional<Departement> findDepartementById(int departementID) {
        try {
            return departementRepository.findById(departementID);
        } catch (Exception e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }

    // getAll
    @Override
    public List<Departement> getAllDepartements() {
        List<Departement> allDepartements = new ArrayList<>();
        try {
            allDepartements.addAll(departementRepository.findAll());
            return allDepartements;
        } catch (Exception e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

}
