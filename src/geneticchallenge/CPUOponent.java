package geneticchallenge;

import redeneural.RedeNeural;
import redeneural.RedeNeuralMarshaller;
import geneticAlgorithm.GeneticAlgorithm;
import company.Company;
import environment.Environment;

public class CPUOponent implements Oponent {

    private Company company;
    
    private EstrategiaSelecao algoritmo;
    
    public CPUOponent(int inicialInvestiment, EstrategiaSelecao e) {
        company = new Company(inicialInvestiment);
        this.algoritmo = e;
        
        while (!company.isSolucao()) {
            company = generateRandomCompany(company);
        }
    }
    
    @Override
    public void startGame() throws Exception{
        
    	switch (algoritmo) {
			case GENETICO:
				company = findSolutionByGenetico(company);
			break;
			case REDE_NEURAL:
				company = findSolutionByRedeNeural(company);
				if(company != null && !company.isSolucao()){
					System.err.printf("Não foi encontrada uma solução pela rede!\n", company);
					//TODO REVISAR
					company = findSolutionByGenetico(company);
				}
			break;	
		}
        
        if(company == null){
            throw new Exception("A Companhia não é capaz de evoluir");
        }
        
        company.startRound();
        
    }
    
    private Company findSolutionByRedeNeural(Company atual) {
    	RedeNeural r = new RedeNeural();
    	double[] input = RedeNeuralMarshaller.marshalToInput(atual);
		return RedeNeuralMarshaller
				.unmarshall(r.consult(input), 
						atual.cashOfCompany, 
						atual.getHistorico());
	}
    
    private Company findSolutionByGenetico(Company atual){
    	GeneticAlgorithm geneticAlgorithm = new GeneticAlgorithm();
        
        geneticAlgorithm.setPopulationSize(200);
        geneticAlgorithm.setElitism(true);
        geneticAlgorithm.setMaxGenerations(200);
        
        return geneticAlgorithm.findSolution(atual);
    }

    private Company generateRandomCompany(Company company) {
        return new Company(company).randomVariables();
    }

    @Override
    public String toString() {
        return company.toString();
    }

	@Override  
	public void roundSales(int i, Environment enviroment) {
            int realSales = i;
            if(company.getStock() < i){
                realSales = company.getStock();
            }
            company.finishRound(realSales, enviroment);
	}

    @Override
    public Company getCompany() {
        return this.company;
    }
    
    public enum EstrategiaSelecao{
    	GENETICO, REDE_NEURAL;
    	
    	public static EstrategiaSelecao getAsString(String s){
    		switch (s) {
				case "G":
					return GENETICO;
				case "R":
					return REDE_NEURAL;
				default:
					return GENETICO;
			}
    	}
    }
}
