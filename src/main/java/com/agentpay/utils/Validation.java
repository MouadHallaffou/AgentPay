package main.java.com.agentpay.utils;

public class Validation {

    public static boolean isValidEmail(String email) {
        return email != null && email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$");
    }

    public static boolean isValideName(String name) {
        return name != null && !name.trim().isEmpty() &&
                name.matches("^[a-zA-ZÀ-ÿ '-]+$") &&
                name.length() >= 2;
    }

    public static boolean isValidPassword(String password) {
        return password != null && password.length() >= 8;
    }

    public static boolean salaireValide(double salaire) {
        return  salaire > 0;
    }
    
}
