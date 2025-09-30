package main.java.com.agentpay.exceptions;

//Erreurs liées à la base de données (échec d’insertion...)

public class PaiementDatabaseException extends RuntimeException {
    public PaiementDatabaseException(String message) {
        super(message);
    }
}
