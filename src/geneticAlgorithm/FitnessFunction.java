package geneticAlgorithm;

import company.Company;
import company.FitnessWeightable;
import java.util.List;

public interface FitnessFunction<T extends FitnessWeightable> {
    T findFitness(T entity, List<T> population);
}
