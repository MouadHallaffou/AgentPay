package main.java.com.agentpay.service.interfaces;

import java.util.List;
import java.util.Optional;

import main.java.com.agentpay.exceptions.EmailDejaUtiliseException;
import main.java.com.agentpay.model.Agent;
import main.java.com.agentpay.model.Paiement;

public interface AgentService{
    boolean createAgent(Agent agent) throws EmailDejaUtiliseException;

    boolean updateAgent(Agent agent) ;

    boolean deleteAgent(int agentId);

    List<Agent> getAllAgents();

    Optional<Agent> getAgentById(int id);

    Optional<Agent> findByEmail(String email);

    boolean setAgentAccountStatus(int userID);

    List<Agent> finAgentByDepartement(String departementName);

    Agent getAgentByFullName(String name);

    List<Paiement> getPaiementsByEmail(String email);

}
