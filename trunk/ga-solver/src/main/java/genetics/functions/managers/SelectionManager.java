/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package genetics.functions.managers;

import com.frre.cemami.utils.DefaultLogguer;
import genetics.functions.selection.BestSelector;
import genetics.functions.selection.CopyControlSelector;
import genetics.functions.selection.ISelector;
import genetics.functions.selection.RankingSelector;
import genetics.individuos.Individuo;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Set;

/**
 *
 * @author Justo Vargas
 */
public class SelectionManager {

    public static enum Selectors {

        RANKING_SELECTOR, COPY_CONTROL_SELECTOR, BEST_SELECTOR
    };
    public int DEFAULT_SURVIVORS_BY_SELECTIONS_METHODS = 20;
    private HashMap<Selectors, ISelector> selectorClasses;
    static DefaultLogguer logguer = DefaultLogguer.getLogger();

    public SelectionManager() {
        selectorClasses = new HashMap<Selectors, ISelector>();
        selectorClasses.put(Selectors.RANKING_SELECTOR, new RankingSelector());
        selectorClasses.put(Selectors.COPY_CONTROL_SELECTOR, new CopyControlSelector());
        selectorClasses.put(Selectors.BEST_SELECTOR, new BestSelector());
    }

    public LinkedList<Individuo> doSelection(LinkedList<Individuo> poblacionOriginal, HashMap<Selectors, Integer> coverageMethods) {
        Collections.sort(poblacionOriginal);
        Set<Selectors> keys = coverageMethods.keySet();

        LinkedList<Individuo> newPopulation = new LinkedList<Individuo>();

        if (DEFAULT_SURVIVORS_BY_SELECTIONS_METHODS == 0) {
            return newPopulation;
        }

        for (Selectors selectorMethod : keys) {
            int getCoverageOfMethod = coverageMethods.get(selectorMethod);
            if (getCoverageOfMethod > 0) {
                ISelector selector = selectorClasses.get(selectorMethod);
                //hay que ver si necesito clonarlos posta
                //LinkedList<Individuo> cloneIndividuos = PoblacionFactory.getInstance().cloneInitialPopulation(poblacionOriginal);
                newPopulation.addAll(selector.doSelection(poblacionOriginal, getCoverageOfMethod, this.DEFAULT_SURVIVORS_BY_SELECTIONS_METHODS));
            }
        }

        return newPopulation;
    }

    public void setPercentage(int percentage) {
        this.DEFAULT_SURVIVORS_BY_SELECTIONS_METHODS = percentage;
    }
}
