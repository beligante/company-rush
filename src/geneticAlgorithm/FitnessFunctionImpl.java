package geneticAlgorithm;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import company.Company;
import company.Config;

public class FitnessFunctionImpl implements FitnessFunction<Company>{

    private Map<CustoBeneficioEnum, Integer> custoMap;
    
    public FitnessFunctionImpl(){
        custoMap = new HashMap<>();
        
        for(CustoBeneficioEnum cbe : CustoBeneficioEnum.values()){
            custoMap.put(cbe, (Config.MAX_POINTS * (cbe.getPercentual() / 100)));
        }
    }
    
    @Override
    public Company findFitness(Company company, List<Company> population) {
        CustoBeneficio cbsAtual;
        for (Company cy : population) {
            cbsAtual = new CustoBeneficio(custoMap);
            
            cbsAtual
                    .calculatePercentualLucro(cy.cost(), cy.getMaxSalesValue());
            int f = cbsAtual.getCustoBeneficio();
            
            if(isProduceMoreThatCanKeep(cy)){
            	//System.out.println("passou");
            	f -= 0.2 * f; 
            }
            
            if(isSimilarToWorstCompanyInHistory(cy)){
            	f -= 0.2 * f; 
            }
            
//            if (!company.getHistorico().isEmpty() 
//            		&& (company.productionCapacity() 
//            				>= (company.getHistorico().get(company.getHistorico().size() - 1)).getStock())){
//                f -= 0.2 * f;                
//            }
            
            
            
            cy.setFitnessValue(f);
            
        }
        
        return company;
    }
    
    /**
     * Retorna verdadeiro se a compania passada como parametro 
     * se assemelha em termos de valor de variaveis a compania
     * que teve o pior historico de lucro.
     * 
     * @param atual
     * @return retorna verdadeiro se a compania se assemelha a pior compania no historico
     */
    private boolean isSimilarToWorstCompanyInHistory(Company atual){
    	Company worst = findWorstInHistory(atual);
    	if(worst == null){ return false;}
    	int similarity = worst.similarityRate(atual);
    	return (similarity >= ((atual.getVariables().size() / 2) + 1));
    }
    
    private Company findWorstInHistory(Company atual){
    	
    	if(atual.getHistorico().isEmpty()){
    		return null;
    	}
    	
    	List<Company> toSearch =  atual.getHistorico()
    			.stream()
    			.filter(h -> h.getGain() > 0)
    			.collect(Collectors.toList());

		if(toSearch != null && toSearch.size() <= 1){
			return null;
		}
		Company worst = toSearch.get(0);
    	for(int i = 1; i < toSearch.size(); i++){
    		if(toSearch.get(i).getGain() < worst.getGain()){
    			worst = toSearch.get(i);
    		}
    	}
    	
    	return worst;
    }
    
    private boolean isProduceMoreThatCanKeep(Company atual){
    	return atual.getStockCapacity() < atual.productionCapacity() + atual.getStock();
    }
    
    public Company findFitnessOfACompany(Company company){
    	return findFitness(company, Arrays.asList(company));
    }
    
    private class CustoBeneficio{
        
        Map<CustoBeneficioEnum, Integer> custoMap;
        public CustoBeneficio(Map<CustoBeneficioEnum, Integer> custoMap){
            this.custoMap = custoMap;
        }
        
        //percentual de lucro sobre as vendas
        // 1 - (Custo / VendaTota)
        double percentualLucro;
        
        public CustoBeneficio calculatePercentualLucro(int custo, int vendaTotal){
            percentualLucro = 1 - (custo / (double) vendaTotal);
            return this;
        }
        
        private double getValueByCustoEnum(CustoBeneficioEnum cb){
            switch(cb){
                case PERCENTUAL_LUCRO: {
                    return percentualLucro;                    
                }
                default:
                    return 0;
            }
            
        }
        
        public int getCustoBeneficio(){
            
            double custoAtual;
            int cbFinal = 0;
            for(CustoBeneficioEnum cbEnum : CustoBeneficioEnum.values()){
                custoAtual = getValueByCustoEnum(cbEnum);
                cbFinal += custoMap.get(cbEnum) * custoAtual;
            }
            
            return cbFinal;
        }
    }
    
    private enum CustoBeneficioEnum {
    
        PERCENTUAL_LUCRO(100);
        
        int percentual;

        private CustoBeneficioEnum(int percentual) {
            this.percentual = percentual; 
        }
        
        public int getPercentual(){return percentual;}
    }
    
}
