package environment;

public class EconimicSituation implements EnvironmentVariable{

    private int economicSituationAtual;
    
    @Override
    public void setValueByInt(int value) {
        economicSituationAtual = value;
    }

    @Override
    public int getSize() {
        return 100;
    } 

    public int getEconomicSituationAtual() {
        return economicSituationAtual;
    }

    @Override
    public int getValueAsInt() {
        return economicSituationAtual;
    }
    
}
