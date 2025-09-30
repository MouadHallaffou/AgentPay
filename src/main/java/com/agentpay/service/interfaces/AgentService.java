package main.java.com.agentpay.service.interfaces;

public interface AgentService {
    void createAgent();

    void updateAgent();

    void deleteAgent();

    java.util.List<Object> getAllAgents();

    Object getAgentById(Long id);

    void setAgentAccountStatus(Long agentId, boolean status);

    java.util.List<Object> finAgentByDepartement(String departement);

    java.util.List<Object> getAgentByFullName(String fullName);

    boolean isValidAgent(Long agentId);
}
