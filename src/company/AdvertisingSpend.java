package company;

public class AdvertisingSpend implements CompanyVariable {

    final private static int size = AdvertisingSpendEnum.values().length;
    final public static String ADVERTISING_SPEND = "ADVERTISING_SPEND";
    private AdvertisingSpendEnum value;

    @Override
    public int cost() { 
        int cost;

        switch (value) {
            case NONE:
                cost = 0;	
                break;
            case VERY_LITTLE:
                cost = 500;
                break;
            case LITTLE:
                cost = 1000;
                break;
            case MEDIUM:
                cost = 2000;
                break;
            case BIG:
                cost = 5000;
                break;
            case VERY_BIG:
                cost = 15000;
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

    public enum AdvertisingSpendEnum {
        NONE,
        VERY_LITTLE, //- 500   melhora em 2%
        LITTLE, //- 1000  melhora em 5%
        MEDIUM, //- 2000  melhora em 10%
        BIG, //- 5000  melhora em 15%
        VERY_BIG    //- 15000 melhora em 25%
    }

    @Override
    public void setValueByInt(int randInt) {
        switch (randInt) {
            case 0:
                value = AdvertisingSpendEnum.VERY_LITTLE;
                break;
            case 1:
                value = AdvertisingSpendEnum.LITTLE;
                break;
            case 2:
                value = AdvertisingSpendEnum.MEDIUM;
                break;
            case 3:
                value = AdvertisingSpendEnum.BIG;
                break;
            case 4:
                value = AdvertisingSpendEnum.VERY_BIG;
            case 5: 
                value = AdvertisingSpendEnum.NONE;
        }
    }

    @Override
    public String getName() {
        return ADVERTISING_SPEND;
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
        AdvertisingSpend advertisingSpend = new AdvertisingSpend();
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
