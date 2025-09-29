package main.java.com.agentpay.exceptions;

//Quand un paiement recherché par ID n’existe pas.

public class PaiementNotFoundException extends RuntimeException {
    public PaiementNotFoundException(String message) {
        super(message);
    }
}
