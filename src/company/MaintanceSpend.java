package company;

public class MaintanceSpend implements CompanyVariable {

    final private static int size = MaintanceSpendEnum.values().length;
    final public static String MAINTANCE_SPEND = "MAINTANCE_SPEND";
    private MaintanceSpendEnum value;

    @Override
    public int cost() {
        int cost;

        switch (value) {
            case NONE:
                cost = 0;
                break;
            case MEDIOCRE:
                cost = 500;
                break;
            case ADVANCED:
                cost = 1000;
                break;
            default:
                cost = 0;

        }
        
        return cost;
    }

    @Override
    public TYPE_VAR getTypeVar() {
        return CompanyVariable.TYPE_VAR.INVESTIMENT;
    }

    public enum MaintanceSpendEnum {
        NONE,
        MEDIOCRE,
        ADVANCED
    }

    @Override
    public void setValueByInt(int randInt) {
        switch (randInt) {
            case 0:
                value = MaintanceSpendEnum.NONE;
                break;
            case 1:
                value = MaintanceSpendEnum.MEDIOCRE;
                break;
            case 2:
                value = MaintanceSpendEnum.ADVANCED;
                break;
        }
    }

    @Override
    public String getName() {
        return MAINTANCE_SPEND;
    }

    @Override
    public Object getValue() {
        return value;
    }

    @Override
    public boolean equals(CompanyVariable other) {
        return other.getName().equals(getName()) && other.getValue().equals(value);
    }

    @Override
    public CompanyVariable clone() {
        MaintanceSpend advertisingSpend = new MaintanceSpend();
        advertisingSpend.value = this.value;
        return advertisingSpend;
    }

    @Override
    public int getSize() {
        return size;
    }
    
    public int productionImpact() {
        int impact;

        switch (value) {
            case NONE:
                impact = 0;
                break;            
            case MEDIOCRE:
                impact = 2;
                break;
            case ADVANCED:
                impact = 5;
                break;
            default:
                impact = 0;

        }
        
        return impact;
    }

    @Override
    public int getValueAsInt() {
        int value = 0;
		switch (this.value) {
        case NONE:
            value = 0;
            break;
        case MEDIOCRE:
            value = 1;
            break;
        case ADVANCED:
            value = 2;
            break;
		}
        return value;
    	
    }
    
}
