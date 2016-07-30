package redeneural;

import environment.Environment;
import geneticchallenge.CPUOponent;
import geneticchallenge.CPUOponent.EstrategiaSelecao;
import geneticchallenge.Market;
import geneticchallenge.Oponent;

import java.util.ArrayList;
import java.util.List;

import org.neuroph.core.data.DataSetRow;

import company.Company;

public class TreinarRedeMain {
    
	public static void main(String[] args) {

		TreinarRedeMain main = new TreinarRedeMain();
        main.start(args);
    }

    private void start(String[] args) {

        args = "cpu cpu 35000".split(" ");

        List<Company> winners = new ArrayList<Company>();
        Oponent winner;
        while (winners.size() < 100) {
            winner = findWinner(args);
            if (winner != null) {
                winners.add(winner.getCompany());
            }
            
            System.out.println("Rushs: " + winners.size());
        }

        List<DataSetRow> l = new ArrayList<DataSetRow>();
        int lastIndice = -1;
        for (Company w : winners) {
        	lastIndice = w.getHistorico().size() - 1;
        	for(int i = 0; i <= lastIndice; i += 2){
        		if(i != lastIndice){
	        		l.add(new DataSetRow(
	        				RedeNeuralMarshaller.marshalToInput(w.getHistorico().get(i)), 
	        				RedeNeuralMarshaller.marshallToOutput(w.getHistorico().get(i+1))));
        		}
        	}
        	System.out.println("size: " + l.size());
        }

        RedeNeural rede = new RedeNeural();
        rede.training(l);

        System.out.println("Finished!");

    }

    private Oponent findWinner(String[] args) {
        String oponentTypeA = args[0];
        String oponentTypeB = args[1];
        oponentTypeA = oponentTypeA.toLowerCase();
        oponentTypeB = oponentTypeB.toLowerCase();

        Oponent[] oponent = new Oponent[2];

        Environment environment = new Environment();
        environment.randomVariables();
        oponent[0] = createOponent(oponentTypeA, args, environment);
        oponent[1] = createOponent(oponentTypeB, args, environment);

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
            --rodadas;
        }

        if (rodadas != 0) {
            System.out.println("Não foi possivel concluir o jogo");
        }

        Oponent winner = null;
        if (oponent[0].getCompany().getCashOfCompany() > oponent[1].getCompany().getCashOfCompany()) {
            winner = oponent[0];
            System.out.println("Oponente 0 foi o vencedor!");
        } else if (oponent[1].getCompany().getCashOfCompany() > oponent[0].getCompany().getCashOfCompany()) {
            System.out.println("Oponente 1 foi o vencedor!");
            winner = oponent[1];
        } else {
            System.out.println("As empresas empataram!");
        }

        return winner;
    }

    private Oponent createOponent(String oponentType, String[] args, Environment environment) {

        int inicialCash = Integer.parseInt(args[2]);

        if (oponentType.equals("cpu")) {
            return new CPUOponent(inicialCash, EstrategiaSelecao.GENETICO);
        }

        return null;
    }

    
}
