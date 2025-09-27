package main.java.com.agentpay.service;

import main.java.com.agentpay.model.Departement;
import main.java.com.agentpay.repository.interfaces.DepartementRepository;
import main.java.com.agentpay.utils.Validation;

public class DepartementService {
    private final DepartementRepository departementRepository;

    public DepartementService(DepartementRepository departementRepository) {
        this.departementRepository = departementRepository;
    }

    public boolean createDepartement(Departement departement) {
        try {
//            if (!isValideDepartement(departement)) {
//                return false;
//            }
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

    // MÉTHODE POUR CRÉER UN DÉPARTEMENT À PARTIR D'UN NOM
    public boolean createDepartement(String nom) {
        try {
            if (nom == null || nom.trim().isEmpty()) {
                return false;
            }

            Departement departement = new Departement();
            departement.setName(nom.trim());

            return createDepartement(departement);
        } catch (Exception e) {
            System.out.println("Erreur lors de la création du département: " + e.getMessage());
            return false;
        }
    }

    public boolean isValideDepartement(Departement departement) {
        return departement != null && Validation.isValideName(departement.getName());
    }

}
