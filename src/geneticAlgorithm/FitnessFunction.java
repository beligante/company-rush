package geneticAlgorithm;

import java.util.List;

import company.FitnessWeightable;

public interface FitnessFunction<T extends FitnessWeightable<T>> {
    T findFitness(T entity, List<T> population);
    T findFitnessOfACompany(T company);
}
