package model;

import model.enums.TypeAgent;

public class Agent extends User {
    private TypeAgent typeAgent;
    private Departement departement;

    public Agent(){}

    public Agent(int userID, String firstName, String lastName, String email, String password, TypeAgent typeAgent, Departement departement) {
        super(userID, firstName, lastName, email, password);
        this.typeAgent = typeAgent;
    }

    @Override
    public boolean authentifier(String email, String password) {
        return email.equals(getEmail()) && password.equals(getPassword());
    }

    public TypeAgent getTypeAgent() {
        return typeAgent;
    }

    public void setTypeAgent(TypeAgent typeAgent) {
        this.typeAgent = typeAgent;
    }

    public Departement getDepartement() {
        return departement;
    }

    public void setDepartement(Departement departement) {
        this.departement = departement;
    }
    @Override
    public String toString() {
        return "Agent{" +
                "typeAgent=" + typeAgent +
                ", departement=" + departement +
                "} " + super.toString();
    }
    
}
