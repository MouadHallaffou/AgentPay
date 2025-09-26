package main.java.com.agentpay.model;

import java.util.ArrayList;

public class Departement {
    private int departementID;
    private String name;
    private Agent responsable;
    private ArrayList<Agent> agents;

    public Departement() {
    }

    public Departement(int departementID, String name, Agent responsable, ArrayList<Agent> agents) {
        this.departementID = departementID;
        this.name = name;
        this.responsable = responsable;
        this.agents = agents;
    }

    public int getDepartementID() {
        return departementID;
    }

    public void setDepartementID(int departementID) {
        this.departementID = departementID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Agent getResponsable() {
        return responsable;
    }

    public void setResponsable(Agent responsable) {
        this.responsable = responsable;
    }

    public ArrayList<Agent> getAgents() {
        return agents;
    }

    public void setAgents(ArrayList<Agent> agents) {
        this.agents = agents;
    }

    @Override
    public String toString() {
        return "Departement{" +
                "departementID=" + departementID +
                ", name='" + name + '\'' +
                ", responsable=" + responsable +
                ", agents=" + agents +
                '}';
    }
}
