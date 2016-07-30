package geneticchallenge;

import company.Company;
import environment.Environment;

public interface Oponent {
    void startGame() throws Exception;
    void roundSales(int i, Environment environment);
    Company getCompany();
}
