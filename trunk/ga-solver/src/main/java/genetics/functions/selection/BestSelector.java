/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package genetics.functions.selection;

import genetics.individuos.Individuo;
import java.util.Collections;
import java.util.LinkedList;

/**
 *
 * @author Justo Vargas
 */
public class BestSelector implements ISelector {

    public LinkedList<Individuo> doSelection(LinkedList<Individuo> poblacion, double selectionMethodCoverage, double survivors) {
        LinkedList<Individuo> newPopulation = new LinkedList<Individuo>();
        double size = poblacion.size();

        Collections.sort(poblacion);

        double numberOfCopies = (selectionMethodCoverage*survivors/100) * size / 100;
        for (int i = 0; i < numberOfCopies; i++) {
            newPopulation.add(poblacion.get(i));
        }
        return newPopulation;
    }

}
