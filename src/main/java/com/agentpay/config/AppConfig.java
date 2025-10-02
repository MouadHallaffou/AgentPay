package main.java.com.agentpay.config;

import main.java.com.agentpay.controller.*;
import main.java.com.agentpay.repository.interfaces.*;
import main.java.com.agentpay.repository.interfacesImp.*;
import main.java.com.agentpay.service.*;
import main.java.com.agentpay.service.interfaces.*;
import main.java.com.agentpay.view.*;

public class AppConfig {

    private final AgentController agentController;
    private final AuthController authController;
    private final DepartementController departementController;
    private final PaiementController paiementController;

    public AppConfig() {
        AgentRepository agentRepo = new AgentRepositoryImp();
        DepartementRepository depRepo = new DepartementRepositoryImp(ConfigConnection.getConnection());
        PaiementRepository paiementRepository = new PaiementRepositoryImp(ConfigConnection.getConnection(), agentRepo);

        AgentService agentService = new AgentServiceImpl(agentRepo);
        DepartementService departementService = new DepartementServiceImpl(depRepo);
        PaiementService paiementService = new PaiementServiceImpl(paiementRepository);
        AuthServiceImpl authServiceImpl = new AuthServiceImpl(agentService);

        AgentView agentView = new AgentView(agentService);

        this.agentController = new AgentController(agentService, agentView, departementService, paiementService);
        this.departementController = new DepartementController(departementService, agentView);
        this.paiementController = new PaiementController(paiementService, agentView, agentService);
        this.authController = new AuthController(authServiceImpl, agentController, departementController, paiementController);
    }

    public AgentController getAgentController() {
        return agentController;
    }

    public AuthController getAuthController() {
        return authController;
    }

    public DepartementController getDepartementController() {
        return departementController;
    }

    public PaiementController getPaiementController() {
        return paiementController;
    }
}