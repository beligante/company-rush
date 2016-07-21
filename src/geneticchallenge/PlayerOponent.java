package geneticchallenge;

import company.Company;
import environment.Environment;

public class PlayerOponent implements Oponent {

    final private Environment environment;
    PlayerOponent(Environment environment) {
        this.environment = environment;
    }

    @Override
    public void startGame() {
        
    }

	@Override
	public void roundSales(int i) {
		// TODO Auto-generated method stub
		
	}

    @Override
    public Company getCompany() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
