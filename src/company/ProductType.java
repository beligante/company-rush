package company;

public class ProductType implements CompanyVariable {

    final private static int size = ProductTypeEnum.values().length;
    final public static String PRODUCT_TYPE = "PRODUCT_TYPE";
    private ProductTypeEnum value;

    @Override
    public int cost() {
        int cost;
        switch (value) {
            case TOOTH_PASTE_BASIC:
                cost = 2;
                break;
            case TOOTH_PASTE_MINT:
                cost = 3;
                break;
            case TOOTH_PASTE_PEPPERMINT:
                cost = 4;
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

    public enum ProductTypeEnum {

        TOOTH_PASTE_BASIC, //      - CUSTO = 2
        TOOTH_PASTE_MINT, //      - CUSTO = 3
        TOOTH_PASTE_PEPPERMINT // - CUSTO = 4
    }

    @Override
    public void setValueByInt(int randInt) {
        switch (randInt) {
            case 0:
                value = ProductTypeEnum.TOOTH_PASTE_BASIC;
                break;
            case 1:
                value = ProductTypeEnum.TOOTH_PASTE_MINT;
                break;
            case 2:
                value = ProductTypeEnum.TOOTH_PASTE_PEPPERMINT;
        }
    }

    @Override
    public String getName() {
        return PRODUCT_TYPE;
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
        ProductType productType = new ProductType();
        productType.value = this.value;
        return productType;
    }

    @Override
    public int getSize() {
        return size;
    }
    
    @Override
    public int getValueAsInt() {
        int value = 0;
		switch (this.value) {
	        case TOOTH_PASTE_BASIC:
	            value = 0;
	            break;
	        case TOOTH_PASTE_MINT:
	            value = 1;
	            break;
	        case TOOTH_PASTE_PEPPERMINT:
	            value = 2;
	            break;
		}
        return value;
    }

}
