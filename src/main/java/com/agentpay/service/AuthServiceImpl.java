package main.java.com.agentpay.service;

import java.util.Optional;
import main.java.com.agentpay.model.Agent;
import main.java.com.agentpay.service.interfaces.AgentService;
import main.java.com.agentpay.service.interfaces.AuthService;

public class AuthServiceImpl implements AuthService {
    private final AgentService agentService;

    public AuthServiceImpl(AgentService agentService) {
        this.agentService = agentService;
    }

    @Override
    public Optional<Agent> getLogedAgent(String email, String password) {
        Optional<Agent> agentOpt = agentService.findByEmail(email);
        if (agentOpt.isPresent() && agentOpt.get().authentifier(email, password)) {
            return agentOpt;
        } else {
            return Optional.empty();
        }
    }
    
}
