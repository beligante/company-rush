package company;

public interface CompanyVariable {
    public enum TYPE_VAR {
        INVESTIMENT,
        PRODUCTION
    }
    
    public String getName();
    public Object getValue();
    boolean equals(CompanyVariable other);
    public CompanyVariable clone();
    public int getSize();
    public void setValueByInt(int randInt);
    public int getValueAsInt();
    public int cost();
    public TYPE_VAR getTypeVar();    
    
}
