package main.java.com.agentpay.exceptions;

public class PaiementInvalideException extends RuntimeException {
    public PaiementInvalideException(String message) {
        super(message);
    }
}
