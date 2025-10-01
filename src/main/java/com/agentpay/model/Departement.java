package main.java.com.agentpay.model;

public class Departement {
    private int departementID;
    private String name;
    private int totalAgents;
    private String responsableName;
    public int getTotalAgents() {
        return totalAgents;
    }

    public void setTotalAgents(int totalAgents) {
        this.totalAgents = totalAgents;
    }

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

    public String getResponsableName() {
        return responsableName;
    }

    public void setResponsableName(String responsableName) {
        this.responsableName = responsableName;
    }

    @Override
    public String toString() {
        return "Departement{" +
                "departementID=" + departementID +
                ", name='" + name + '\'' +
                '}';
    }
}
