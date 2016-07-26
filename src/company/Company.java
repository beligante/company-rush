package company;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Company implements FitnessWeightable<Company> {

    private CompanyVariablesContainers companyVariablesContainers;
    public int cashOfCompany;
    private int stock;
    private int fitnessValue = -1;
    private int vendas;
    
    //Variaveis de Historico
    private int gain = -1;
    private int productionCapacityOriginal;
    private List<Company> historico;

    public List<Company> getHistorico() {
        return historico;
    }

    public Company(Company company) {
        this.companyVariablesContainers = new CompanyVariablesContainers(company.companyVariablesContainers);
        this.cashOfCompany = company.cashOfCompany;
        this.stock = company.stock;
        this.historico = new ArrayList<>(company.historico);
        this.historico.add(company);
    }

    public Company(int inicialInvestiment) {
        companyVariablesContainers = new CompanyVariablesContainers();
        companyVariablesContainers.add(new BuildingSize());
        companyVariablesContainers.add(new AdvertisingSpend());
        companyVariablesContainers.add(new ProductType());
        companyVariablesContainers.add(new ProductPrice());
        companyVariablesContainers.add(new Workers());
        companyVariablesContainers.add(new QualitySpend());
        companyVariablesContainers.add(new MaintanceSpend());
        this.cashOfCompany = inicialInvestiment;
        this.historico = new ArrayList<Company>();

        randomVariables();

    }

    public ArrayList<CompanyVariable> getVariables() {
        return companyVariablesContainers.getVariables();
    }

    public CompanyVariable getVariableByName(String name) {
        return companyVariablesContainers.getVariableByName(name);
    }

    public void setCashOfCompany(int cashOfCompany) {
        this.cashOfCompany = cashOfCompany;
    }

    public int getCashOfCompany() {
        return cashOfCompany;
    }

    public int cost() {
        int cost = 0;
        ArrayList<CompanyVariable> vars = companyVariablesContainers.getVariables();
        for (int i = 0; i < vars.size(); ++i) {
            CompanyVariable var = vars.get(i);
            if (var.getTypeVar().equals(CompanyVariable.TYPE_VAR.INVESTIMENT)) {
                cost += var.cost();
            }
        }
        return cost;
    }

    public int getTotalToGain() {
        return (productionCapacity() * getProductPrice())
                - (productionCapacity() * getProductCost());
    }

    public int getMonetaryProductionCapacity() {
        return (Math.max(0, getCashOfCompany() - cost() / getProductCost()));
    }

    public int productionCapacity() {
        return Math.min(Math.max(getStockCapacity() - stock, 0), Math.min(getMonetaryProductionCapacity(), getWorkersProductionPumpedCapacity()));
    }

    public int getAdvertisingCost() {
        return getVariableByName(AdvertisingSpend.ADVERTISING_SPEND).cost();
    }

    public int getProductPrice() {
        return (int) (((getProductGainPercent() / 100D) * getProductCost())
                + getProductCost());
    }

    public int getMaxSalesValue() {
        return getProductPrice() * (productionCapacity() + getStock());
    }

    public int getProductCost() {
        return getVariableByName(ProductType.PRODUCT_TYPE).cost();
    }

    public int getProductGainPercent() {
        return getVariableByName(ProductPrice.PRODUCT_PRICE).cost();
    }

    public int getStockCapacity() {
        return ((BuildingSize) getVariableByName(BuildingSize.BUILDING_SIZE)).stock();
    }

    public int getWorkersProductionCapacity() {
        return ((Workers) getVariableByName(Workers.WORKERS)).productionCapacity();
    }

    public int getWorkersProductionPumpedCapacity() {
        return (int) (getWorkersProductionCapacity() * (1 + (getProductionImpact() / 100D)));
    }

    public int getAdvertisingImpact() {
        return ((AdvertisingSpend) getVariableByName(AdvertisingSpend.ADVERTISING_SPEND)).impact();
    }

    public int getQualityImpact() {
        return ((QualitySpend) getVariableByName(QualitySpend.QUALIDADE_SPEND)).impact();
    }

    public int getProductionImpact() {
        return ((MaintanceSpend) getVariableByName(MaintanceSpend.MAINTANCE_SPEND)).productionImpact();
    }

    public boolean isSolucao() {

        int minInvest = (int) (cashOfCompany * (Config.MIN_INVEST / 100D));
        int maxInvest = (int) (cashOfCompany * (Config.MAX_INVEST / 100D));
        //boolean isCapacity = ((stock + productionCapacity()) > getStockCapacity());

//        System.out.println("cash: " + cashOfCompany);
//        System.out.println("min: " + minInvest);
//        System.out.println("max: " + maxInvest);
//        System.out.println("cost: " + cost());
//        
//        System.out.println("cost() >= minInvest: " + Boolean.toString(cost() >= minInvest));
//        System.out.println("cost() <= maxInvest: " + Boolean.toString(cost() <= maxInvest));
//        System.out.println("cost() >= minInvest && cost() <= maxInvest && !isCapacity: " + Boolean.toString(cost() >= minInvest && cost() <= maxInvest && !isCapacity));
//        
        return cost() >= minInvest && cost() <= maxInvest; //&& !isCapacity;
    }

    public Company randomVariables() {
        Random random = new Random(System.currentTimeMillis());

        for (int i = 0; i < companyVariablesContainers.getVariables().size(); ++i) {
            CompanyVariable companyVariable = companyVariablesContainers.getVariables().get(i);
            int randInt = random.nextInt(companyVariable.getSize());
            companyVariable.setValueByInt(randInt);
        }
        return this;
    }

    public void startRound() {
        incrementStock();
        decrementCash();
    }

    private void incrementStock() {

        if (productionCapacity() + stock > getStockCapacity()) {
            stock = getStockCapacity();
        } else {
            stock += productionCapacity();
        }

    }

    private void decrementCash() {
        cashOfCompany -= cost();
    }

    public void finishRound(int sales) {
        vendas = sales;
        gain = (getProductPrice() * sales);
        unitSales(sales);
        incrementCash(sales);
    }

    public int getVendas() {
        return vendas;
    }

    public int getGain(){
    	return gain;
    }
    
    private void incrementCash(int sales) {
        cashOfCompany += gain;
    }

    private void unitSales(int sales) {
        this.stock -= sales;
    }

    public int getNumberOfVariables() {
        return companyVariablesContainers.getVariables().size();
    }

    public void copyVariablesFromCompany(Company c, int index) {
        for (int i = 0; i <= index; i++) {
            this.companyVariablesContainers
                    .getVariables()
                    .get(i)
                    .setValueByInt(
                            c.companyVariablesContainers
                            .getVariables()
                            .get(i)
                            .getValueAsInt());
        }
    }

    @Override
    public void setFitnessValue(int peso) {

        this.fitnessValue = peso;

    }

    @Override
    public int getFitness() {
        return fitnessValue;
    }

    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();

        sb.append("Company{\n");
        sb.append("  building-size: ").append(getStockCapacity()).append("\n");
        sb.append("  product-price: ").append(getProductPrice()).append("\n");
        sb.append("  production: ").append(productionCapacity()).append("\n");
        sb.append("  cash-of-company: ").append(cashOfCompany).append("\n");
        sb.append("  stock: ").append(stock).append("\n");
        sb.append("\n}");

        return sb.toString();
    }

    public int getStock() {
        return stock;
    }
    
    public int similarityRate(Company company){
    	int similarity = 0;
    	for(int i = 0; i < company.companyVariablesContainers.getVariables().size(); i++){
    		if(this.companyVariablesContainers.getVariables().get(i)
    				.equals(company.companyVariablesContainers.getVariables().get(0))){
    			similarity++;
    		}
    	}
    	
    	return similarity;
    }
}
