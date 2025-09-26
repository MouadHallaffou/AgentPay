package main.java.com.agentpay.service;

import java.util.Optional;
import main.java.com.agentpay.model.Agent;
import main.java.com.agentpay.repository.interfaces.AgentRepository;

public class AuthService {
    private final AgentRepository agentRepository;

    public AuthService (AgentRepository agentRepository){
        this.agentRepository = agentRepository;
    }

    public Optional<Agent> getLogedAgent(String email, String password) {
        Optional<Agent> agentOpt = agentRepository.findByEmail(email);
        if (agentOpt.isPresent() && agentOpt.get().authentifier(email, password)) {
            return agentOpt;
        } else {
            return Optional.empty();
        }
    }
    
}
