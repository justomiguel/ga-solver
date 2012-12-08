/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package genetics.functions.selection;

import genetics.individuos.Individuo;
import java.util.LinkedList;
import java.util.Random;

/**
 *
 * @author Justo Vargas
 */
public class RankingSelector implements ISelector {

    protected Random random = new Random();

    public LinkedList<Individuo> doSelection(LinkedList<Individuo> poblacion, double selectionMethodCoverage, double survivors) {
        
        LinkedList<Individuo> newPopulation = new LinkedList<Individuo>();

        int populationSize = poblacion.size();
        boolean haveAllSurvivors = false;
        while (!haveAllSurvivors) {

            newPopulation.clear();
            
            double rMin = random.nextDouble();
            double[] copiesNumber = new double[populationSize];
            for (int i = 0; i < poblacion.size(); i++) {
                double indCopiesToBeSelected = rMin + ((2 * (populationSize - (i + 1)) * (1 - rMin)) / (populationSize - 1));
                copiesNumber[i] = indCopiesToBeSelected;
            }

            Double localSurvivors = ((selectionMethodCoverage*survivors/100) * populationSize) / 100;

            int sobrevivientes = localSurvivors.intValue();
            
            for (int i = 0; i < copiesNumber.length; i++) {
                Double copiesNumberOfInd = copiesNumber[i];
                int z = copiesNumberOfInd.intValue();
                if (z == 1) {
                    newPopulation.add(poblacion.get(i));
                    sobrevivientes--;
                    if (sobrevivientes == 0) {
                        haveAllSurvivors = true;
                        break;
                    }
                }
            }
        }

        return newPopulation;

    }
}
