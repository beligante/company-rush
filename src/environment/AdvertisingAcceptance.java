package environment;

public class AdvertisingAcceptance implements EnvironmentVariable {

    final private static int size = AdvertisingAcceptanceEnum.values().length;
    AdvertisingAcceptanceEnum advertising;

    @Override
    public int getValueAsInt() {
        int value = 0;
        switch(advertising){
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

    enum AdvertisingAcceptanceEnum {
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
                advertising = AdvertisingAcceptanceEnum.VERY_LITTLE;
                break;
            case 1:
                advertising = AdvertisingAcceptanceEnum.LITTLE;
                break;
            case 2:
                advertising = AdvertisingAcceptanceEnum.MEDIUM;
                break;
            case 3:
                advertising = AdvertisingAcceptanceEnum.BIG;
                break;
            case 4:
                advertising = AdvertisingAcceptanceEnum.VERY_BIG;
        }
    }
    
    public int getAdvertisingAcc(){
        
        int acc = 0;
        switch(advertising){
            case VERY_LITTLE:
                acc = 0;
                break;
            case LITTLE:
                acc = 5;
                break;
            case MEDIUM:
                acc = 10;
                break;
            case BIG:
                acc = 15;
                break;
            case VERY_BIG:
                acc = 25;
                break;
        }
        
        return acc;
    }
}
