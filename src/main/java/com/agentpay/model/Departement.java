package main.java.com.agentpay.model;

import java.util.ArrayList;

public class Departement {
    private int departementID;
    private String name;
    // private Agent responsable;
    // private ArrayList<Agent> agents;

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



    @Override
    public String toString() {
        return "Departement{" +
                "departementID=" + departementID +
                ", name='" + name + '\'' +
                '}';
    }
}
