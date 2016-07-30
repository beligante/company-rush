package geneticchallenge;

import company.Company;

import environment.Environment;

public class Market {

    Environment environment;

    private static final String QUALIDADE_STR = "Qtde Clientes Qualidade: %d - { InvOp0: %d, InvOp1: %d }";
    private static final String PRECO_STR = "Qtde Clientes Preço: %d - { PrecoOp0: %d, PrecoOp1: %d }";
    
    public Market(Environment environment) {
        this.environment = environment;
    }

    public void distributeSales(Oponent[] opnents) {

        int qtdeClientes = environment.getRegiaoClienteMap().get(0);
        int economicSituation = environment.getEconimicSituation().getEconomicSituationAtual();
        int advertisingAcc = environment.getAdvertisingAcceptance().getAdvertisingAcc();
        int qtdeCQualidade = (int) ((economicSituation / 100D) * qtdeClientes), qtdeCPreco = qtdeClientes - qtdeCQualidade;
        int vendasOp0 = 0, vendasOp1 = 0;

        Company c0 = opnents[0].getCompany(), c1 = opnents[1].getCompany();

//            System.err.println("Economic Situation: " + economicSituation);
//            System.err.println(String.format(QUALIDADE_STR, qtdeCQualidade, c0.getQualityImpact(), c1.getQualityImpact()));
//            System.err.println(String.format(PRECO_STR, qtdeCPreco, c0.getProductPrice(), c1.getProductPrice()));
            
        if (c0.getProductPrice() < c1.getProductPrice()) {
            vendasOp0 = qtdeCPreco;
        } else if (c0.getProductPrice() == c1.getProductPrice()) {
            vendasOp0 = (int) (qtdeCPreco / 2D);
            vendasOp1 = (int) (qtdeCPreco / 2D);
        } else {
            vendasOp1 = qtdeCPreco;
        }
   
        if (c0.getQualityImpact() > c1.getQualityImpact()) {
            vendasOp0 += qtdeCQualidade;
        } else if (c0.getQualityImpact() == c1.getQualityImpact()) {
            vendasOp0 += (qtdeCQualidade / 2D);
            vendasOp1 += (qtdeCQualidade / 2D);
        } else {
            vendasOp1 += qtdeCQualidade;
        }
        
        int qtdeToSubtract = 0;
        if (c0.getAdvertisingImpact() > c1.getAdvertisingImpact()) {
            qtdeToSubtract = (advertisingAcc / 100) * vendasOp1;
            vendasOp1 -= qtdeToSubtract;
            vendasOp0 += qtdeToSubtract;
        } else if (c1.getAdvertisingImpact() > c0.getAdvertisingImpact()) {
            qtdeToSubtract = (advertisingAcc / 100) * vendasOp0;
            vendasOp0 -= qtdeToSubtract;
            vendasOp1 += qtdeToSubtract;
        }

        qtdeToSubtract = 0;
        if (vendasOp0 == 0) {
            qtdeToSubtract += vendasOp1 * 0.2D;
            vendasOp0 += qtdeToSubtract;
            vendasOp1 -= qtdeToSubtract;
        } else if (vendasOp1 == 0) {
            qtdeToSubtract += vendasOp0 * 0.2D;
            vendasOp1 += qtdeToSubtract;
            vendasOp0 -= qtdeToSubtract;
        }

        int consumo0 = opnents[0].getCompany().getStock() - vendasOp0;

        if (consumo0 < 0) {
            vendasOp1 += Math.abs(consumo0);
        }

        int consumo1 = opnents[1].getCompany().getStock() - vendasOp1;

        if (consumo1 < 0) {
            vendasOp0 += Math.abs(consumo1);
        }

//        System.out.println("Vendas 0p0: " + vendasOp0);
//        System.out.println("Vendas 0p1: " + vendasOp1);
//
//        System.out.println("Fitness0: " + c0.getFitness());
//        System.out.println("Fitness1: " + c1.getFitness());
        
        try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
        
        opnents[0].roundSales(vendasOp0, new Environment(environment));
        opnents[1].roundSales(vendasOp1, new Environment(environment));

        environment.randomVariables();

    }
}
