package geneticAlgorithm;

import company.Company;
import company.Config;
import environment.Environment;
import geneticchallenge.Printer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

public class GeneticAlgorithm {

    private int populationSize;
    private boolean elitism;
    private double crossoverRate;
    private double mutationRate;
    private int maxGenerations;
    private Printer printer;
    final private Environment environment;
    private List<Company> populacao;
    private FitnessFunction<Company> fitnessFunction;
    private static final int MAX_ITERATIONS = 1000000000;
    private static final int ARR_SIZE = 300;
    
    public GeneticAlgorithm(Environment environment) {
        this.environment = environment;
        this.populacao = new ArrayList<>();
        this.fitnessFunction = new FitnessFunctionImpl();
    }
    
    public Company findSolution(Company company) {
        
        Company c = null;
        int iterations = 0;
        populacao = new ArrayList<>();
        while(populacao.size() < ARR_SIZE && iterations < MAX_ITERATIONS){
          c = new Company(company).randomVariables();
          if(c.isSolucao()){
              populacao.add(c);
          }
          iterations++;
          
          if(iterations >= MAX_ITERATIONS - 10){
        	System.out.println("pop");
            System.out.println("cash: " + c.cashOfCompany);
            int minInvest = (int) (c.cashOfCompany * (Config.MIN_INVEST / 100D));
            int maxInvest = (int) (c.cashOfCompany * (Config.MAX_INVEST / 100D));
            System.out.println("min: " + minInvest);
            System.out.println("max: " + maxInvest);
            System.out.println("cost: " + c.cost());
          }
          
        }
        
        if(populacao.size() < ARR_SIZE){
            return null;
        }
        
        fitnessFunction.findFitness(company, populacao);
                
        Company son;
        List<Company> sons = new ArrayList<>();
        iterations = 0;
        while(sons.size() < ARR_SIZE && iterations < MAX_ITERATIONS){
         son = crossOver(company, roulet());

         if(son.isSolucao()){
             sons.add(son);
         }
         iterations++;
         
         if(iterations >= MAX_ITERATIONS - 10){
        	 System.out.println("son");
             System.out.println("cash: " + son.cashOfCompany);
             int minInvest = (int) (son.cashOfCompany * (Config.MIN_INVEST / 100D));
             int maxInvest = (int) (son.cashOfCompany * (Config.MAX_INVEST / 100D));
             System.out.println("min: " + minInvest);
             System.out.println("max: " + maxInvest);
             System.out.println("cost: " + son.cost());
           }
        }
        
        if(sons.size() < ARR_SIZE){
            return null;
        }
        
        Collections.sort(sons, new CompanyComparator());
        Collections.sort(populacao, new CompanyComparator());
        
        
        if(sons.get(0).getFitness() > populacao.get(0).getFitness()){
            return sons.get(0);
        }
        
        return populacao.get(0);
    }
    
    private Company crossOver(Company father, List<Company> roleta){
        
        int crossOverPoint = new Random().nextInt(father.getNumberOfVariables() - 1);
        
        //System.out.println("crossOverPoint: " + crossOverPoint);
        //System.out.println("roleta1" + roleta.get(1));
        //System.out.println("roleta2" + roleta.get(0));
        Company son = new Company(roleta.get(1));
        son.copyVariablesFromCompany(roleta.get(0), crossOverPoint);
        //System.out.println("son" + son);
        return son;
        
    }
    
    private List<Company> roulet(){
        Random r = new Random();
        
        List<Company> choosed = new ArrayList<>();
        
        Company c1 = populacao.get(r.nextInt(populacao.size() - 1)), 
                c2 = populacao.get(r.nextInt(populacao.size() - 1)),
                c3 = populacao.get(r.nextInt(populacao.size() - 1));
        
        while(c1 == c2){
            c1 = populacao.get(r.nextInt(populacao.size() - 1));
        }
        
        while(c2 == c3 || c1 == c3){
            c3 = populacao.get(r.nextInt(populacao.size() - 1));
        }
        
        choosed.add(c1);
        choosed.add(c2);
        choosed.add(c3);
        return choosed;
    }

    public void setPopulationSize(int size) {
        populationSize = size;
    }

    public void setElitism(boolean b) {
        elitism = b;
    }

    public void setCrossoverRate(double d) {
        crossoverRate = d;
    }

    public void setMutationRate(double d) {
        mutationRate = d;
    }

    public void setMaxGenerations(int i) {
        maxGenerations = i;
    }

    public void setPrinter(Printer printer) {
        this.printer = printer;
    }
    private class CompanyComparator implements Comparator<Company>{

        @Override
        public int compare(Company o1, Company o2) {
            return Integer.compare(o2.getFitness(), o1.getFitness());
        }
        
    }
}
