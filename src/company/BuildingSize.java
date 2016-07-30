package company;

public class BuildingSize implements CompanyVariable {

    final private static int size = BuildingSizeEnum.values().length;
    final public static String BUILDING_SIZE = "BUILDING_SYZE";
    private BuildingSizeEnum value;

    @Override
    public int cost() {

        int cost;
        switch (value) {
            case LITTLE:
                cost = 5000;
                break;
            case MEDIUM:
                cost = 10000;
                break;
            case BIG:
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

    int stock() {
        int stock;
        switch (value) {
            case LITTLE:
                stock = 10000;
                break;
            case MEDIUM:
                stock = 24000;
                break;
            case BIG:
                stock = 37000;
                break;
            default:
                stock = 0;
        }

        return stock;
    }

    public enum BuildingSizeEnum {

        LITTLE,
        MEDIUM,
        BIG
    }

    public BuildingSize() {
    }

    @Override
    public void setValueByInt(int randInt) {
        switch (randInt) {
            case 0:
                value = BuildingSizeEnum.LITTLE;
                break;
            case 1:
                value = BuildingSizeEnum.MEDIUM;
                break;
            case 2:
                value = BuildingSizeEnum.BIG;
        }
    }

    @Override
    public Object getValue() {
        return value;
    }

    @Override
    public String getName() {
        return BUILDING_SIZE;
    }

    @Override
    public boolean equals(CompanyVariable other) {
        return other.getName().equals(getName()) && other.getValue().equals(value);
    }

    @Override
    public CompanyVariable clone() {
        BuildingSize buildingSyze = new BuildingSize();
        buildingSyze.value = this.value;
        return buildingSyze;
    }

    @Override
    public int getSize() {
        return size;
    }

	@Override
	public int getValueAsInt() {
        int value = 0;
		switch (this.value) {
        case LITTLE:
            value = 0;
            break;
        case MEDIUM:
            value = 1;
            break;
        case BIG:
            value = 2;
            break;
		}
        return value;
	}

}
