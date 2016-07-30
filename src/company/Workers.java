package company;

public class Workers implements CompanyVariable {

    final private static int size = WorkersEnum.values().length;
    final public static String WORKERS = "WORKERS";
    private WorkersEnum value;

    @Override
    public int cost() {
        int cost;

        switch (value) {
            case VERY_LITTLE:
                cost = 5000;
                break;
            case LITTLE:
                cost = 10000;
                break;
            case MEDIUM:
                cost = 15000;
                break;
            case BIG:
                cost = 20000;
                break;
            case VERY_BIG:
                cost = 25000;
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

    public enum WorkersEnum {

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
                value = WorkersEnum.VERY_LITTLE;
                break;
            case 1:
                value = WorkersEnum.LITTLE;
                break;
            case 2:
                value = WorkersEnum.MEDIUM;
                break;
            case 3:
                value = WorkersEnum.BIG;
                break;
            case 4:
                value = WorkersEnum.VERY_BIG;
        }
    }

    @Override
    public String getName() {
        return WORKERS;
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
        Workers workers = new Workers();
        workers.value = this.value;
        return workers;
    }

    @Override
    public int getSize() {
        return size;
    }

    public int productionCapacity(){
        int production;

        switch (value) {
            case VERY_LITTLE:
                production = 10000;
                break;
            case LITTLE:
                production = 15000;
                break;
            case MEDIUM:
                production = 20000;
                break;
            case BIG:
                production = 35000;
                break;
            case VERY_BIG:
                production = 55000;
                break;
            default:
                production = 0;
        }
        
        return production;
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
		}
        return value;
    }
    
}
