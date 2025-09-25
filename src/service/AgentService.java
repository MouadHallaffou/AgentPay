package service;

import java.util.Scanner;
import model.Agent;
import model.enums.TypeAgent;
import repository.interfaces.AgentRepository;
import utils.Validation;

import java.util.Optional;

public class AgentService {
    private final AgentRepository agentRepository;

    public AgentService(AgentRepository agentRepository) {
        this.agentRepository = agentRepository;
    }

    // CRÉER UN AGENT
    public boolean createAgent(Agent agent) {
        try {
            if (!isValidAgent(agent)) {
                return false;
            }

            // 2. Vérifier si l'email existe déjà
            Optional<Agent> existingAgent = agentRepository.findByEmail(agent.getEmail());
            if (existingAgent.isPresent()) {
                throw new RuntimeException("Un agent avec cet email existe déjà!");
            }

            // 3. Appeler le repository pour insérer
            agentRepository.insert(agent);
            return true;

        } catch (Exception e) {
            System.err.println("Erreur lors de la création de l'agent: " + e.getMessage());
            return false;
        }
    }

    // MÉTHODE DE VALIDATION
    private boolean isValidAgent(Agent agent) {
        return agent != null &&
               Validation.isValidEmail(agent.getEmail()) &&
               Validation.isValidPassword(agent.getPassword()) &&
               Validation.isValideName(agent.getFirstName()) &&
               Validation.isValideName(agent.getLastName()) &&
               agent.getTypeAgent() != null;
    }

    
}
