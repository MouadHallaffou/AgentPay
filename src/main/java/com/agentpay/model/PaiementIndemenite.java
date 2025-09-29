package main.java.com.agentpay.model;

public class PaiementIndemenite {
    private boolean conditionvalide;

    public void setConditionvalide(boolean conditionvalide) {
        this.conditionvalide = conditionvalide;
    }

    public PaiementIndemenite(boolean conditionvalide) {
        this.conditionvalide = conditionvalide;
    }

    public boolean isConditionvalide() {
        return conditionvalide;
    }

}
