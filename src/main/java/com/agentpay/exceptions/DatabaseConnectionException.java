package main.java.com.agentpay.exceptions;

public class DatabaseConnectionException extends RuntimeException {
  public DatabaseConnectionException(String message) {
    super(message);
  }
}
