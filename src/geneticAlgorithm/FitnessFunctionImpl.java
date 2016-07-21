package geneticAlgorithm;

import company.Company;
import company.Config;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        
        List<CustoBeneficio> cbs = new ArrayList<>();
        CustoBeneficio cbsAtual;
        for (Company cy : population) {
            cbsAtual = new CustoBeneficio(custoMap);
            
            cbsAtual
                    .calculatePercentualLucro(cy.cost(), cy.getMaxSalesValue());
            int f = cbsAtual.getCustoBeneficio();
            
            if (!company.getHistorico().isEmpty() && (company.productionCapacity() >= ((Company) (company.getHistorico().get(company.getHistorico().size() - 1))).getStock())){
                f -= 0.2 * f;                
            }
            
//            if (!company.getHistorico().isEmpty() && (company.productionCapacity() + company.getStock() >= ((Company) (company.getHistorico().get(company.getHistorico().size() - 1))).getStock())){
//                f -= 0.5 * f;                
//            }
            
            cy.setFitnessValue(f);
            
        }
        
        return company;
    }
    
    private class CustoBeneficio{
        
        Map<CustoBeneficioEnum, Integer> custoMap;
        public CustoBeneficio(Map<CustoBeneficioEnum, Integer> custoMap){
            this.custoMap = custoMap;
        }
        
        //percentual de lucro sobre as vendas
        // 1 - (Custo / VendaTota)
        double percentualLucro;
        double percentualCusto;
        
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
