package service;

import java.util.Scanner;

import model.Agent;
import model.enums.TypeAgent;
import repository.interfaces.AgentRepository;

public class AgentService {
    private final AgentRepository agentRepository;
    private Scanner scanner;

    public AgentService(AgentRepository agentRepository) {
        this.agentRepository = agentRepository;
        this.scanner = new Scanner(System.in);
    }

    public void registerAgent(Agent agent) {
        System.out.println("Enter Client name: ");
        String name = scanner.nextLine();
        System.out.println("Enter Client Email: ");
        String email = scanner.nextLine();
        System.out.println("Enter Client Password: ");
        String password = scanner.nextLine();
        System.out.println("Enter Client Type (AGENT/CLIENT): ");
        String type = scanner.nextLine();

        Agent newAgent = new Agent();
        newAgent.setFirstName(name);
        newAgent.setEmail(email);
        newAgent.setPassword(password);
        newAgent.setTypeAgent(TypeAgent.valueOf(type.toUpperCase()));

        agentRepository.insert(newAgent);
    }
}
