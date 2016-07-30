package main;

import environment.Environment;
import geneticchallenge.CPUOponent;
import geneticchallenge.CPUOponent.EstrategiaSelecao;
import geneticchallenge.Market;
import geneticchallenge.Oponent;

public class Main {

    public static void main(String[] args) {

        Main main = new Main();
        main.start(args);
    }

    
    private void start(String[] args) {

        args = "cpu cpu 35000 G R".split(" ");
        findWinner(args);
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
        oponent[0] = createOponent(oponentTypeA, args, environment, EstrategiaSelecao.getAsString(args[3]));
        oponent[1] = createOponent(oponentTypeB, args, environment, EstrategiaSelecao.getAsString(args[4]));

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

        if (rodadas != 0) {
            System.out.println("Não foi possivel concluir o jogo");
        }

        System.out.println(oponent[0]);
        System.out.println(oponent[1]);

        Oponent winner = null;
        if (oponent[0].getCompany().getCashOfCompany() 
        		> oponent[1].getCompany().getCashOfCompany()) {
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

    private Oponent createOponent(String oponentType, String[] args, Environment environment, EstrategiaSelecao e) {

        int inicialCash = Integer.parseInt(args[2]);

        if (oponentType.equals("cpu")) {
            return new CPUOponent(inicialCash, e);
        }

        return null;
    }

}
