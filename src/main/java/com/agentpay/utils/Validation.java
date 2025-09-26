package main.java.com.agentpay.utils;

public class Validation {
    
    public static boolean isValidEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
        return email.matches(emailRegex);
    }

    public static boolean isValideName(String name) {
        String nameRegex = "^[a-zA-Z]+(([',. -][a-zA-Z ])?[a-zA-Z]*)*$";
        return name.matches(nameRegex);
    }

    public static boolean isValidPassword(String password) {
        return password.length() >= 8;
    }

}
