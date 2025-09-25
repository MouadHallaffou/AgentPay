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

            // 2. Vérifier si l'email existe déjà
            Optional<Agent> existingAgent = agentRepository.findByEmail(agent.getEmail());
            if (existingAgent.isPresent()) {
                throw new RuntimeException("Un agent avec cet email existe déjà!");
            }

            // 3. Appeler le repository pour insérer (maintenant retourne boolean)
            return agentRepository.insert(agent);

        } catch (Exception e) {
            System.err.println("Erreur lors de la création de l'agent: " + e.getMessage());
            return false;
        }
    }

    // METTRE À JOUR UN AGENT
    public boolean updateAgent(Agent agent) {
        try {
            // 1. Validation des données
            if (!isValidAgent(agent)) {
                return false;
            }

            // 2. Vérifier si l'agent existe
            Optional<Agent> existingAgent = agentRepository.findById(agent.getUserID());
            if (existingAgent.isEmpty()) {
                throw new RuntimeException("Agent non trouvé!");
            }

            // 3. Appeler le repository pour mettre à jour
            return agentRepository.update(agent);

        } catch (Exception e) {
            System.err.println("Erreur lors de la mise à jour de l'agent: " + e.getMessage());
            return false;
        }
    }

    // SUPPRIMER UN AGENT
    public boolean deleteAgent(int agentId) {
        try {
            // 1. Vérifier si l'agent existe
            Optional<Agent> existingAgent = agentRepository.findById(agentId);
            if (existingAgent.isEmpty()) {
                throw new RuntimeException("Agent non trouvé!");
            }

            // 2. Appeler le repository pour supprimer
            return agentRepository.delete(agentId);

        } catch (Exception e) {
            System.err.println("Erreur lors de la suppression de l'agent: " + e.getMessage());
            return false;
        }
    }

    // RÉCUPÉRER TOUS LES AGENTS
    public List<Agent> getAllAgents() {
        try {
            return agentRepository.findAll();
        } catch (Exception e) {
            System.err.println("Erreur lors de la récupération des agents: " + e.getMessage());
            return new java.util.ArrayList<>();
        }
    }

    // RÉCUPÉRER UN AGENT PAR ID
    public Optional<Agent> getAgentById(int id) {
        try {
            return agentRepository.findById(id);
        } catch (Exception e) {
            System.err.println("Erreur lors de la récupération de l'agent: " + e.getMessage());
            return Optional.empty();
        }
    }

    // MÉTHODE DE VALIDATION
    private boolean isValidAgent(Agent agent) {
        if (agent == null)
            return false;
        if (agent.getFirstName() == null || agent.getFirstName().trim().isEmpty())
            return false;
        if (agent.getLastName() == null || agent.getLastName().trim().isEmpty())
            return false;
        if (agent.getEmail() == null || agent.getEmail().trim().isEmpty())
            return false;
        if (agent.getPassword() == null || agent.getPassword().trim().isEmpty())
            return false;
        if (agent.getTypeAgent() == null)
            return false;

        return true;
    }

}
