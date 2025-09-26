package service;

import java.util.Scanner;
import model.Agent;
import model.enums.TypeAgent;
import repository.interfaces.AgentRepository;
import utils.Validation;
import java.util.Optional;
import java.util.List;

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

            // verifiie si l'email existe deja ou non
            Optional<Agent> existingAgent = agentRepository.findByEmail(agent.getEmail());
            if (existingAgent.isPresent()) {
                throw new RuntimeException("Un agent avec cet email existe déjà!");
            }
            // repository
            return agentRepository.insert(agent);

        } catch (Exception e) {
            System.err.println("Erreur lors de la création de l'agent: " + e.getMessage());
            return false;
        }
    }

    // update
    public boolean updateAgent(Agent agent) {
        try {
            // validation
            if (!isValidAgent(agent)) {
                return false;
            }
            // verifie l'existances de l'agent:
            Optional<Agent> existingAgent = agentRepository.findById(agent.getUserID());
            if (existingAgent.isEmpty()) {
                throw new RuntimeException("Agent non trouvé!");
            }

            return agentRepository.update(agent);
        } catch (Exception e) {
            System.err.println("Erreur lors de la mise à jour de l'agent: " + e.getMessage());
            return false;
        }
    }

    // delete agent
    public boolean deleteAgent(int agentId) {
        try {
            // verifie l'existance de l'agent
            Optional<Agent> existingAgent = agentRepository.findById(agentId);
            if (existingAgent.isEmpty()) {
                throw new RuntimeException("Agent non trouvé!");
            }

            return agentRepository.delete(agentId);
        } catch (Exception e) {
            System.err.println("Erreur lors de la suppression de l'agent: " + e.getMessage());
            return false;
        }
    }



    // validation
    private boolean isValidAgent(Agent agent) {
        if (agent == null && Validation.isValidPassword(agent.getPassword()) && Validation.isValidEmail(agent.getEmail()) && Validation.isValideName(agent.getFirstName()) && Validation.isValideName(agent.getLastName()));

        return true;
    }

}
