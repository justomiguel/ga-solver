/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package genetics.functions.managers;

import com.frre.cemami.utils.DefaultLogguer;
import com.frre.cemami.utils.MathUtils;
import genetics.functions.mutations.AddMutation;
import genetics.functions.mutations.IMutator;
import genetics.functions.mutations.MaxMutation;
import genetics.functions.mutations.RandomMutation;
import genetics.functions.mutations.ZeroMutation;
import genetics.individuos.Individuo;
import genetics.productos.exceptions.NoMateriaPrimaAddedException;
import genetics.productos.exceptions.ProductCreationException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Set;

/**
 *
 * @author Justo Vargas
 */
public class MutatorManager {

    public static enum Mutators {

        ZERO, 
        RANDOM,
        MAXIMIZER,
        ADDER
    };

    public int DEFAULT_SURVIVORS_BY_MUTATORS_METHODS = 40;
    
    private HashMap<Mutators, IMutator> mutatorClasses;
    static DefaultLogguer logguer = DefaultLogguer.getLogger();

    public MutatorManager() {
        mutatorClasses = new HashMap<Mutators, IMutator>();
        mutatorClasses.put(Mutators.ZERO, new ZeroMutation());
        mutatorClasses.put(Mutators.RANDOM, new RandomMutation());
        mutatorClasses.put(Mutators.ADDER, new AddMutation());
        mutatorClasses.put(Mutators.MAXIMIZER, new MaxMutation());
    }

    public LinkedList<Individuo> doMutation(LinkedList<Individuo> poblacionOriginal, HashMap<Mutators, Integer> coverageMethods) {

        Set<Mutators> keys = coverageMethods.keySet();

        LinkedList<Individuo> newPopulation = new LinkedList<Individuo>();

        if (DEFAULT_SURVIVORS_BY_MUTATORS_METHODS == 0){
            return newPopulation;
        }
        
        for (Mutators mutatorMethod : keys) {
            int getCoverageOfMethod = coverageMethods.get(mutatorMethod);
            if (getCoverageOfMethod > 0) {
                IMutator mutator = mutatorClasses.get(mutatorMethod);

                // total habitantes
                int numberOfPopulation = poblacionOriginal.size();

                // porcentage a pasar por este metodo
                int percentageOfCopiesToBeGeneratedByThisMethod = getCoverageOfMethod * DEFAULT_SURVIVORS_BY_MUTATORS_METHODS / 100;

                // total de copias de acuerdo al porcentaje anterior
                int numberOfCopiesForThisMethod = percentageOfCopiesToBeGeneratedByThisMethod * numberOfPopulation / 100;

                // genero las individuos que necesito
                while (numberOfCopiesForThisMethod > 0) {
                    int chosenOne = MathUtils.getRandomNumber(0, numberOfPopulation-1);
                    try {
                        Individuo son = mutator.doMutation(poblacionOriginal.get(chosenOne));
                        boolean alreadyThere = false;
                        for (Individuo individuo : poblacionOriginal) {
                            if (individuo.equalsTo(son)){
                                alreadyThere = true;
                                break;
                            }
                        }
                        if (!alreadyThere){
                            numberOfCopiesForThisMethod--;
                            newPopulation.add(son);
                        }
                    }  catch (NoMateriaPrimaAddedException ex) {
                       logguer.logError(this, ex.getMessage(), ex);
                    } catch (ProductCreationException ex) {
                        // logguer.warning(this, "Can not make the cruza", ex);
                    }
                }
            }
        }

        return newPopulation;
    }
    
    public void setPercentage(int percentage){
        this.DEFAULT_SURVIVORS_BY_MUTATORS_METHODS = percentage;
    }
}
