package environment;

public class Demand implements EnvironmentVariable {

    final private static int size = DemandEnum.values().length;
    DemandEnum demand;
    public enum DemandEnum {
        VERY_LITTLE,
        LITTLE,
        MEDIUM,
        BIG,
        VERY_BIG
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public void setValueByInt(int value) {
        switch (value) {
            case 0:
                demand = DemandEnum.VERY_LITTLE;
                break;
            case 1:
                demand = DemandEnum.LITTLE;
                break;
            case 2:
                demand = DemandEnum.MEDIUM;
                break;
            case 3:
                demand = DemandEnum.BIG;
                break;
            case 4:
                demand = DemandEnum.VERY_BIG;
        }
    }
}
