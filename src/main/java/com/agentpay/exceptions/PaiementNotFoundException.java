package main.java.com.agentpay.exceptions;

public class PaiementNotFoundException extends RuntimeException {
  public PaiementNotFoundException(String message) {
    super(message);
  }
}
