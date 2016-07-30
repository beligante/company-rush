package company;

public class QualitySpend implements CompanyVariable {

    final private static int size = QualitySpendEnum.values().length;
    final public static String QUALIDADE_SPEND = "QUALIDADE_SPEND";
    private QualitySpendEnum value;

    @Override
    public int cost() {
        int cost;

        switch (value) {
            case NONE:
                cost = 0;
                break;
            case VERY_LITTLE:
                cost = 200;
                break;
            case LITTLE:
                cost = 500;
                break;
            case MEDIUM:
                cost = 1000;
                break;
            case BIG:
                cost = 1500;
                break;
            case VERY_BIG:
                cost = 3000;
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

    public enum QualitySpendEnum {
        NONE,
        VERY_LITTLE, 
        LITTLE, 
        MEDIUM, 
        BIG, 
        VERY_BIG
    }

    @Override
    public void setValueByInt(int randInt) {
        switch (randInt) {
            case 0:
                value = QualitySpendEnum.VERY_LITTLE;
                break;
            case 1:
                value = QualitySpendEnum.LITTLE;
                break;
            case 2:
                value = QualitySpendEnum.MEDIUM;
                break;
            case 3:
                value = QualitySpendEnum.BIG;
                break;
            case 4:
                value = QualitySpendEnum.VERY_BIG;
            case 5: 
                value = QualitySpendEnum.NONE;
        }
    }

    @Override
    public String getName() {
        return QUALIDADE_SPEND;
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
        QualitySpend advertisingSpend = new QualitySpend();
        advertisingSpend.value = this.value;
        return advertisingSpend;
    }

    @Override
    public int getSize() {
        return size;
    }
    
    public int impact() {
        int impact;

        switch (value) {
            case NONE:
                impact = 0;
                break;            
            case VERY_LITTLE:
                impact = 2;
                break;
            case LITTLE:
                impact = 5;
                break;
            case MEDIUM:
                impact = 10;
                break;
            case BIG:
                impact = 15;
                break;
            case VERY_BIG:
                impact = 25;
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
        case VERY_LITTLE:
            value = 0;
            break;
        case LITTLE:
            value = 1;
            break;
        case MEDIUM:
            value = 2;
            break;
        case BIG:
            value = 3;
            break;
        case VERY_BIG:
            value = 4;
            break;
        case NONE: 
            value = 5;
            break;
		}
        return value;
	}

}
