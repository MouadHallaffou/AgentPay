package main.java.com.agentpay.service;

import main.java.com.agentpay.model.Agent;
import main.java.com.agentpay.repository.interfaces.AgentRepository;
import main.java.com.agentpay.service.interfaces.AgentService;
import main.java.com.agentpay.utils.Validation;

import java.util.ArrayList;
import java.util.Optional;
import java.util.List;

public class AgentServiceImpl implements AgentService{
    private final AgentRepository agentRepository;

    public AgentServiceImpl(AgentRepository agentRepository) {
        this.agentRepository = agentRepository;
    }

    // CRÉER UN AGENT
    public boolean createAgent(Agent agent) {
        try {
            if (!isValidAgent(agent)) {
                return false;
            }
            Optional<Agent> existingAgent = agentRepository.findByEmail(agent.getEmail());
            if (existingAgent.isPresent()) {
                throw new RuntimeException("Un agent avec cet email existe déjà!");
            }
            return agentRepository.insert(agent);

        } catch (Exception e) {
            System.out.println("Erreur lors de la création de l'agent: " + e.getMessage());
            return false;
        }
    }

    // update
    public boolean updateAgent(Agent agent) {
        try {
            if (!isValidAgent(agent)) {
                return false;
            }
            Optional<Agent> existingAgent = agentRepository.findById(agent.getUserID());
            if (existingAgent.isEmpty()) {
                throw new RuntimeException("Agent non trouvé!");
            }
            return agentRepository.update(agent);
        } catch (Exception e) {
            System.out.println("Erreur lors de la mise à jour de l'agent: " + e.getMessage());
            return false;
        }
    }

    // delete agent
    public boolean deleteAgent(int agentId) {
        try {
            Optional<Agent> existingAgent = agentRepository.findById(agentId);
            if (existingAgent.isEmpty()) {
                throw new RuntimeException("Agent non trouvé!");
            }
            return agentRepository.delete(agentId);
        } catch (Exception e) {
            System.out.println("Erreur lors de la suppression de l'agent: " + e.getMessage());
            return false;
        }
    }

    // RÉCUPÉRER TOUS LES AGENTS
    public List<Agent> getAllAgents() {
        try {
            return agentRepository.findAll();
        } catch (Exception e) {
            System.out.println("Erreur lors de la récupération des agents: " + e.getMessage());
            return new java.util.ArrayList<>();
        }
    }

    // RÉCUPÉRER UN AGENT PAR ID
    public Optional<Agent> getAgentById(int id) {
        try {
            return agentRepository.findById(id);
        } catch (Exception e) {
            System.out.println("Erreur lors de la récupération de l'agent: " + e.getMessage());
            return Optional.empty();
        }
    }

    // descative a compte responsbale
    public boolean setAgentAccountStatus(int userID){
        try {
            Optional<Agent> agent = getAgentById(userID);
            if (agent.isPresent()) {
                return agentRepository.setAgentAccountStatus(userID);
            }
            return false;
        } catch (Exception e){
            System.out.println(e.getMessage());
            return false;
        }
    }

    // fin agents by department:
    public List<Agent> finAgentByDepartement(String departementName) {
        try {
            List<Agent> agents = agentRepository.findByDepartement(departementName);
            if (agents != null && !agents.isEmpty()) {
                return (agents);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return new ArrayList<>();
    }

    // Get agent by full full_name
    public Agent getAgentByFullName(String name) {
        try {
            List<Agent> agents = agentRepository.findAll();
            for (Agent agent : agents) {
                String fullName = agent.getFirstName() + " " + agent.getLastName();
                if (fullName.equalsIgnoreCase(name)) {
                    return agent;
                }
            }
            System.out.println("Agent: " + name + " not found");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    // validation
    private boolean isValidAgent(Agent agent) {
        if (agent == null ||
                !Validation.isValideName(agent.getFirstName()) ||
                !Validation.isValideName(agent.getLastName()) ||
                !Validation.isValidEmail(agent.getEmail()) ||
                !Validation.isValidPassword(agent.getPassword()) ||
                agent.getTypeAgent() == null) {
            return false;
        }
        return true;
    }

}
