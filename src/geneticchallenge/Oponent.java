package geneticchallenge;

import company.Company;

public interface Oponent {
    void startGame() throws Exception;
    void roundSales(int i);
    Company getCompany();
}
