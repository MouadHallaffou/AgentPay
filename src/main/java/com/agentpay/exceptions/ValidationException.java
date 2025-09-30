package main.java.com.agentpay.exceptions;

//Erreurs liées à la base de données (échec d’insertion...)

public class ValidationException extends RuntimeException {
    public ValidationException(String message) {
        super(message);
    }
}
