package main.java.com.agentpay.model;


public class Departement {
    private int departementID;
    private String name;
    // private Agent responsable;
    // private ArrayList<Agent> agents;
    private String responsable;
    private int totalAgents;

    public Departement() {
    }

    public Departement(int departementID, String name) {
        this.departementID = departementID;
        this.name = name;
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

    public String getResponsable() {
        return responsable;
    }

    public void setResponsable(String responsable) {
        this.responsable = responsable;
    }

    public int getTotalAgents() {
        return totalAgents;
    }

    public void setTotalAgents(int totalAgents) {
        this.totalAgents = totalAgents;
    }

    @Override
    public String toString() {
        return "Departement{" +
                "departementID=" + departementID +
                ", name='" + name + '\'' +
                '}';
    }
}
