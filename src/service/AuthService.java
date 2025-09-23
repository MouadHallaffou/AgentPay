package service;

import java.util.Optional;

import model.Agent;
import repository.interfaces.AgentRepository;

public class AuthService {
    private final AgentRepository agentRepository;

    public AuthService (AgentRepository agentRepository){
        this.agentRepository = agentRepository;
    }

    public boolean login(String email, String password){
        Optional<Agent> agentOpt = agentRepository.findByEmail(email);
        if(agentOpt.isPresent()){
            model.Agent agent = agentOpt.get();
            return agent.authentifier(email, password);
        }
        return false;
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
