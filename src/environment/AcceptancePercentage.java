package environment;

public class AcceptancePercentage implements EnvironmentVariable {

    final private static int size = AcceptancePercentageEnum.values().length;
    AcceptancePercentageEnum acceptance;

    @Override
    public int getValueAsInt() {
        //TODO Implementar corretamente
        return 1;
    }

    enum AcceptancePercentageEnum {
        LITTLE,
        MEDIUM,
        BIG
    }

    @Override
    public void setValueByInt(int value) {
        switch (value) {
            case 0:
                acceptance = AcceptancePercentageEnum.LITTLE;
                break;
            case 1:
                acceptance = AcceptancePercentageEnum.MEDIUM;
                break;
            case 2:
                acceptance = AcceptancePercentageEnum.BIG;
        }
    }

    @Override
    public int getSize() {
        return size;
    }
}
