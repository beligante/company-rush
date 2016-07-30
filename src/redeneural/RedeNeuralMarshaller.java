package redeneural;

import java.util.ArrayList;
import java.util.List;

import company.Company;

public class RedeNeuralMarshaller {
	
	public static double[] marshalToInput(Company c) {
        double[] d = new double[12];

        d[0] = c.getEnviroment() == null
        		? 0
        		: c.getEnviroment().getAdvertisingAcceptance().getAdvertisingAcc() / 100D;
        d[1] = c.getEnviroment() == null
        		? 0
        		: c.getEnviroment().getEconimicSituation().getEconomicSituationAtual() / 100D;
        d[2] = c.getGain() < 0 ? 0D : c.getGain() / 10000000D;
        d[3] = c.getStock() / 37000D;
        d[4] = c.getCashOfCompany() / 10000000D;

        for (int i = 5; i < d.length; i++) {
            d[i] = c.getVariables().get(i - 5).getValueAsInt() / 10D;
        }

        return d;
    }
	
    public static double[] marshallToOutput(Company c) {
        double[] d = new double[7];

        for (int i = 0; i < d.length; i++) {
            d[i] = c.getVariables().get(i).getValueAsInt() / 10D;
        }

        return d;
    }
    
    public static Company unmarshall(double[] variables, int atualChash, List<Company> historico){
    	
    	Company c = new Company(atualChash);
    	for(int i = 0; i < variables.length; i++){
    		
    		System.out.print(Math.round(variables[i] * 10) + ", ");
    		
    		c.getVariables().get(i).setValueByInt((int) Math.round(variables[i] * 10));
    	}
    	System.out.println();
    	List<Company> cloneHistorico = new ArrayList<Company>();
    	cloneHistorico.addAll(historico);
    	c.setHistorico(cloneHistorico);
    	
    	return c;
    }
	
}
