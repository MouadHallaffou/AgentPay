package main.java.com.agentpay.exceptions;

//montant négatif, type manquant, etc.

public class PaiementValidationException extends RuntimeException {
    public PaiementValidationException(String message) {
        super(message);
    }
}
