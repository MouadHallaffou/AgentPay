package main.java.com.agentpay.model;

public class PaiementBonus {
    private boolean conditionvalide;

    public PaiementBonus(boolean conditionvalide) {
        this.conditionvalide = conditionvalide;
    }

    public void setConditionvalide(boolean conditionvalide) {
        this.conditionvalide = conditionvalide;
    }

    public boolean isConditionvalide() {
        return conditionvalide;
    }

}
