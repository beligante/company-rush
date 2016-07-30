package geneticAlgorithm;

import geneticchallenge.Printer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

import company.Company;
import company.Config;

public class GeneticAlgorithm {

	private int populationSize;
	private boolean elitism;
	private double crossoverRate;
	private double mutationRate;
	private int maxGenerations;
	private Printer printer;
	private List<Company> populacao;
	private FitnessFunction<Company> fitnessFunction;
	private static final int MAX_ITERATIONS = 1000000000;

	public GeneticAlgorithm() {
		this.populacao = new ArrayList<>();
		this.fitnessFunction = new FitnessFunctionImpl();
	}

	public Company findSolution(Company company) {
		int generations = 1;
		Company bestOfTheGeneration = findBestSolutionForAGeneration(company);
		Company candidate;
		while(generations <= maxGenerations){
			candidate = findBestSolutionForAGeneration(bestOfTheGeneration);
			if(candidate.getFitness() >= bestOfTheGeneration.getFitness()){
				bestOfTheGeneration = candidate;
			}
			generations++;
		}
		return bestOfTheGeneration;
	}
	
	private Company findBestSolutionForAGeneration(Company company){
		
		generatePopulationOfRound(company);
		if (populacao != null && populacao.size() < populationSize) {
			return null;
		}
		fitnessFunction.findFitness(company, populacao);

		List<Company> sons = generateSonsOfRound(company);
		if (sons.size() < populationSize) {
			return null;
		}

		if (elitism) {
			Collections.sort(sons, new CompanyComparator());
			Collections.sort(populacao, new CompanyComparator());

			if (sons.get(0).getFitness() > populacao.get(0).getFitness()) {
				return sons.get(0);
			}

			return populacao.get(0);
		}
		
		List<Company> fusion = new ArrayList<Company>(sons);
		fusion.addAll(populacao);
		return fusion.get(new Random().nextInt(fusion.size() - 1));
	}

	private List<Company> generateSonsOfRound(Company company) {
		Company son;
		List<Company> sons = new ArrayList<>();
		int iterations = 0;
		while (sons.size() < populationSize && iterations < MAX_ITERATIONS) {
			son = crossOver(company, roulet(), roulet());

			if (son.isSolucao()) {
				sons.add(son);
			}
			iterations++;

			if (iterations >= MAX_ITERATIONS - 10) {
				System.out.println("son");
				System.out.println("cash: " + son.cashOfCompany);
				int minInvest = (int) (son.cashOfCompany * (Config.MIN_INVEST / 100D));
				int maxInvest = (int) (son.cashOfCompany * (Config.MAX_INVEST / 100D));
				System.out.println("min: " + minInvest);
				System.out.println("max: " + maxInvest);
				System.out.println("cost: " + son.cost());
			}
		}

		return sons;
	}

	private void generatePopulationOfRound(Company company) {

		Company c = null;
		int iterations = 0;
		populacao = new ArrayList<>();
		while (populacao.size() < populationSize && iterations < MAX_ITERATIONS) {
			c = new Company(company).randomVariables();
			if (c.isSolucao()) {
				populacao.add(c);
			}
			iterations++;

			if (iterations >= MAX_ITERATIONS - 10) {
				System.out.println("pop");
				System.out.println("cash: " + c.cashOfCompany);
				int minInvest = (int) (c.cashOfCompany * (Config.MIN_INVEST / 100D));
				int maxInvest = (int) (c.cashOfCompany * (Config.MAX_INVEST / 100D));
				System.out.println("min: " + minInvest);
				System.out.println("max: " + maxInvest);
				System.out.println("cost: " + c.cost());
			}

		}
	}

	private Company crossOver(Company father, List<Company> roleta1,
			List<Company> roleta2) {

		int crossOverPoint = new Random()
				.nextInt(father.getNumberOfVariables() - 1);

		Company son = new Company(roleta1.get(new Random().nextInt(roleta1
				.size() - 1)));
		son.copyVariablesFromCompany(
				roleta2.get(new Random().nextInt(roleta2.size() - 1)),
				crossOverPoint);
		return son;

	}

	private List<Company> roulet() {
		Random r = new Random();

		List<Company> choosed = new ArrayList<>();

		Company c1 = populacao.get(r.nextInt(populacao.size() - 1)), c2 = populacao
				.get(r.nextInt(populacao.size() - 1)), c3 = populacao.get(r
				.nextInt(populacao.size() - 1));

		while (c1 == c2) {
			c1 = populacao.get(r.nextInt(populacao.size() - 1));
		}

		while (c2 == c3 || c1 == c3) {
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

	private class CompanyComparator implements Comparator<Company> {

		@Override
		public int compare(Company o1, Company o2) {
			return Integer.compare(o2.getFitness(), o1.getFitness());
		}

	}
}
