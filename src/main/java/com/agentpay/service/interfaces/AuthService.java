package main.java.com.agentpay.service.interfaces;

import java.util.Optional;
import main.java.com.agentpay.model.Agent;

@FunctionalInterface
public interface AuthService {
    Optional<Agent> getLogedAgent(String email, String password);
}
