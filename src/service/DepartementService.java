package service;

import model.Departement;
import repository.interfaces.DepartementRepository;
import utils.Validation;


public class DepartementService  {
    private final DepartementRepository departementRepository;

    public DepartementService(DepartementRepository departementRepository) {
        this.departementRepository = departementRepository;
    }

    public boolean createDepartement(Departement departement){
        try {
            if (isValideDepartement(departement)){
                return  false;
            }
            boolean departementExists = departementRepository.findByName(departement.getName());
            if (departementExists) {
                throw new RuntimeException("Un département avec ce nom existe déjà!");
            }
            return departementRepository.insert(departement);
        } catch (Exception e) {
            return false;
        }
    }


    
    public boolean isValideDepartement(Departement departement){
        return departement != null && Validation.isValideName(departement.getName());
    }

}
