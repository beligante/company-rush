package company;

public class ProductPrice implements CompanyVariable {

    final private static int size = ProductPriceEnum.values().length;
    final public static String PRODUCT_PRICE = "PRODUCT_PRICE";
    private ProductPriceEnum value;

    @Override
    public int cost() {
        int percent;
        
        switch(value) {
            case VERY_INEXPENSIVE:
                percent = 300;
                break;
            case INEXPENSIVE:
                percent = 350;
                break;
            case MEDIUM:
                percent = 400;
                break;
            case EXPENSIVE:
                percent = 450;
                break;
            case VERY_EXPENSIVE:
                percent = 500;
                break;
            default:
                percent = 0;
        }
        
        return percent;
    }

    @Override
    public TYPE_VAR getTypeVar() {
        return CompanyVariable.TYPE_VAR.PRODUCTION;
    }

    public enum ProductPriceEnum {

        VERY_INEXPENSIVE, // - 100% -
        INEXPENSIVE, //      - 150%
        MEDIUM, //           - 200%
        EXPENSIVE, //        - 250%
        VERY_EXPENSIVE //   - 300%

    }

    @Override
    public void setValueByInt(int randInt) {
        switch (randInt) {
            case 0:
                value = ProductPriceEnum.VERY_INEXPENSIVE;
                break;
            case 1:
                value = ProductPriceEnum.INEXPENSIVE;
                break;
            case 2:
                value = ProductPriceEnum.MEDIUM;
                break;
            case 3:
                value = ProductPriceEnum.EXPENSIVE;
                break;
            case 4:
                value = ProductPriceEnum.VERY_EXPENSIVE;
        }
    }

    @Override
    public String getName() {
        return PRODUCT_PRICE;
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
        ProductPrice productPrice = new ProductPrice();
        productPrice.value = this.value;
        return productPrice;
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public int getValueAsInt() {
        int value = 0;
		switch (this.value) {
	        case VERY_INEXPENSIVE:
	            value = 0;
	            break;
	        case INEXPENSIVE:
	            value = 1;
	            break;
	        case MEDIUM:
	            value = 2;
	            break;
	        case EXPENSIVE:
	            value = 3;
	            break;
	        case VERY_EXPENSIVE:
	            value = 4;
	            break;
		}
        return value;
    }
    
}
