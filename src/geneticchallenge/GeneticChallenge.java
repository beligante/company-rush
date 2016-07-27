package geneticchallenge;


import java.util.ArrayList;
import java.util.List;

import company.Company;
import environment.Environment;

public class GeneticChallenge {

    public static void main(String[] args) {
        
        GeneticChallenge geneticChallenge = new GeneticChallenge();
        geneticChallenge.start(args);
    }

    private void start(String[] args) {
        
        args = "cpu cpu 35000".split(" ");
        
        if (args.length != 3) {
            help();
            return;
        }
        List<Company> winners = new ArrayList<Company>();
        Oponent winner;
        while(winners.size() < 30){
        	winner = findWinner(args);
        	if(winner != null){
        		winners.add(winner.getCompany());
        	}
        }
        System.out.println("Finished!");
    }

	private Oponent findWinner(String[] args) {
		String oponentTypeA = args[0];
        String oponentTypeB = args[1];
        oponentTypeA = oponentTypeA.toLowerCase();
        oponentTypeB = oponentTypeB.toLowerCase();
                
        if (!oponentTypeA.equals("cpu") || !oponentTypeB.equals("cpu")) {
            
                help();
                return null;
        }
        
        Oponent[] oponent = new Oponent[2];
        
        Environment environment = new Environment();
        environment.randomVariables();
        oponent[0] = createOponent(oponentTypeA, args, environment);
        oponent[1] = createOponent(oponentTypeB, args, environment);
        
        if (oponent[0] == null || oponent[1] == null) {
            help();
            return null;
        }
        
        int rodadas = 30;
        
        Market mercado = new Market(environment);
        
        while (rodadas > 0) {
        	try {
	        	for (int i = 0; i < oponent.length; ++i) {
	                
	                Oponent op = oponent[i];
	                
	                op.startGame();
	            }
        	} catch (Exception ex) {
        		ex.printStackTrace();
                break;
            }
            mercado.distributeSales(oponent);
            
            System.err.println("Rodada " + rodadas + " Finalizada");
            System.err.println("##########################################");
            System.err.println("\n");
            --rodadas;
        }
        
        if(rodadas != 0){
            System.out.println("Não foi possivel concluir o jogo");
        }
        
        System.out.println(oponent[0]);
        System.out.println(oponent[1]);
        
        Oponent winner = null;
        if(oponent[0].getCompany().getCashOfCompany() > oponent[1].getCompany().getCashOfCompany()){
        	winner = oponent[0];
        	System.out.println("Oponente 0 foi o vencedor!");
        } else if(oponent[1].getCompany().getCashOfCompany() > oponent[0].getCompany().getCashOfCompany()){
        	System.out.println("Oponente 1 foi o vencedor!");
        	winner = oponent[1];
        }else{
        	System.out.println("As empresas empataram!");
        }
        
        return winner;
	}
    
    

    private void help() {
        System.out.println("help.");
        System.out.println("User: GeneticChalenge <typeOponet> <typeOponet> ");
        System.out.println("typeOponet = <cpu> ou <player>");
        
    }

    private Oponent createOponent(String oponentType, String[] args, Environment environment) {
        
        int inicialCash = Integer.parseInt(args[2]);
        
        if (oponentType.equals("cpu")) {
            return new CPUOponent(inicialCash);
        } else if (oponentType.equals("player")) {
            return new PlayerOponent(environment);
        }
        
        return null;
    }
    
}
