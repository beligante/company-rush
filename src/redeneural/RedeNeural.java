/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package redeneural;

import java.util.List;

import org.neuroph.core.NeuralNetwork;
import org.neuroph.core.data.DataSet;
import org.neuroph.core.data.DataSetRow;
import org.neuroph.nnet.MultiLayerPerceptron;
import org.neuroph.nnet.learning.BackPropagation;

/**
 *
 * @author Lucas
 */
public final class RedeNeural {

    private final MultiLayerPerceptron neuralNetwork;
    final String caminhoDaRede = "company.rede";    

    public RedeNeural() {
        neuralNetwork = new MultiLayerPerceptron(12, 25, 7);
    }
    
    public void training(List<DataSetRow> l) {
        neuralNetwork.randomizeWeights();
        BackPropagation lr = new BackPropagation();
        lr.setMaxIterations(1000);
        lr.setMaxError(0.0001);
        lr.setLearningRate(0.8);
        DataSet trainingSet;
        trainingSet = new DataSet(12, 7);        
        for (DataSetRow l1 : l) {
            trainingSet.addRow(l1);            
        }

        neuralNetwork.learn(trainingSet, lr);
        neuralNetwork.save(caminhoDaRede);
        System.out.print("Salvou");
    }

    @SuppressWarnings("rawtypes")
	public double[] consult(double[] input) {  
    	
    	System.out.print("En: ");
    	for (double d : input) {
			System.out.print(d + ", ");
		}
    	System.out.println();
    	
        @SuppressWarnings({ "rawtypes" })
		NeuralNetwork neuralNetworkLoad;
        neuralNetworkLoad = NeuralNetwork.createFromFile(caminhoDaRede);
        neuralNetworkLoad.setInput(input);
        neuralNetworkLoad.calculate();

        double[] saida = neuralNetworkLoad.getOutput();
        System.out.print("S: ");
        for (double d : saida) {
			System.out.print(d + ", ");
		}
        System.out.println();
        return saida;
    }
   

}
