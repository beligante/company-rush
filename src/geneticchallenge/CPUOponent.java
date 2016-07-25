package geneticchallenge;

import geneticAlgorithm.GeneticAlgorithm;

import company.Company;

public class CPUOponent implements Oponent {

    private Company company;
    
    CPUOponent(int inicialInvestiment) {
        company = new Company(inicialInvestiment);
        
        
        while (!company.isSolucao()) {
            company = generateRandomCompany(company);
        }
    }
    
    @Override
    public void startGame() throws Exception{
        GeneticAlgorithm geneticAlgorithm = new GeneticAlgorithm();
        
        geneticAlgorithm.setPopulationSize(300);
        geneticAlgorithm.setElitism(false);
        geneticAlgorithm.setCrossoverRate(0.6d);
        geneticAlgorithm.setMutationRate(0.03d);
        geneticAlgorithm.setMaxGenerations(100);
        geneticAlgorithm.setPrinter(new Printer() {
            @Override
            public void print(Object o) {
                System.out.print(o.toString());
            }
        });
        
        company = geneticAlgorithm.findSolution(company);
        
        if(company == null){
            throw new Exception("A Companhia não é capaz de evoluir");
        }
        
        company.startRound();
        
    }

    private Company generateRandomCompany(Company company) {
        return new Company(company).randomVariables();
    }

    @Override
    public String toString() {
        return company.toString();
    }

	@Override
	public void roundSales(int i) {
            int realSales = i;
            if(company.getStock() < i){
                realSales = company.getStock();
            }
            company.finishRound(realSales);
	}

    @Override
    public Company getCompany() {
        return this.company;
    }
    
}
