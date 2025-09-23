package repository.interfaces;

import model.Agent;

public interface IDepartement {
    void ajouteAgent(Agent agent);
    void supprimmeAgent(Agent agentID);
    int totalDepartement();
    double moyenneSalaire();
}
