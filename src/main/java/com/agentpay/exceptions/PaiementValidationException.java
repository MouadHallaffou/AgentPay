package main.java.com.agentpay.exceptions;

public class PaiementValidationException extends RuntimeException {
  public PaiementValidationException(String message) {
    super(message);
  }
}
