/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package genetics.functions.selection;

import genetics.functions.managers.SelectionManager;
import genetics.individuos.Individuo;
import java.util.LinkedList;

/**
 *
 * @author Justo Vargas
 */
public class CopyControlSelector implements ISelector {

    public LinkedList<Individuo> doSelection(LinkedList<Individuo> poblacion, double selectionMethodCoverage, double survivors) {

        LinkedList<Individuo> newPopulation = new LinkedList<Individuo>();

        int average = 0;
        int size = poblacion.size();

        for (int i = 0; i < size; i++) {
            Individuo individuo = poblacion.get(i);
            average += individuo.getFitnessValue();
        }
        //get real average
        average = average / size;

        double[] individuosAverage = new double[size];
        for (int i = 0; i < size; i++) {
            individuosAverage[i] = poblacion.get(i).getFitnessValue() / average;
        }

        Double numberOfCopies = (selectionMethodCoverage*survivors/100) * size / 100;
        int numeroDecopias = numberOfCopies.intValue();
        
        for (int i = 0; i < size; i++) {
            Double number = individuosAverage[i];
            int numberOfIndToBeCopied = number.intValue();
            while (numberOfIndToBeCopied > 0) {
                newPopulation.add(poblacion.get(i));
                numberOfIndToBeCopied--;
                numeroDecopias--;
                if (numeroDecopias == 0){
                    return newPopulation;
                }
            }

        }

        return newPopulation;
    }
}
