/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package genetics.functions.managers;

import com.frre.cemami.utils.DefaultLogguer;
import com.frre.cemami.utils.MathUtils;
import genetics.functions.mutations.IMutator;
import genetics.functions.mutations.MaxMutation;
import genetics.functions.mutations.MinimumMutation;
import genetics.functions.mutations.RandomMutation;
import genetics.functions.mutations.ZeroMutation;
import genetics.individuos.Individuo;
import genetics.productos.Producto;
import genetics.productos.ProductosFactory;
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

    public double getPercentage() {
        return DEFAULT_SURVIVORS_BY_MUTATORS_METHODS;
    }

    public static enum Mutators {

        ZERO, 
        RANDOM,
        MAXIMIZER,
        MINIMUM
    };

    public double DEFAULT_SURVIVORS_BY_MUTATORS_METHODS = 40;
    
    private HashMap<Mutators, IMutator> mutatorClasses;
    static DefaultLogguer logguer = DefaultLogguer.getLogger();

    public MutatorManager() {
        mutatorClasses = new HashMap<Mutators, IMutator>();
        mutatorClasses.put(Mutators.ZERO, new ZeroMutation());
        mutatorClasses.put(Mutators.RANDOM, new RandomMutation());
        mutatorClasses.put(Mutators.MINIMUM, new MinimumMutation());
        mutatorClasses.put(Mutators.MAXIMIZER, new MaxMutation());
    }

    public LinkedList<Individuo> doMutation(LinkedList<Individuo> poblacionOriginal, HashMap<Mutators, Integer> coverageMethods) {

        Set<Mutators> keys = coverageMethods.keySet();

        LinkedList<Individuo> newPopulation = new LinkedList<Individuo>();

        if (DEFAULT_SURVIVORS_BY_MUTATORS_METHODS == 0){
            return newPopulation;
        }
        
         //obtengo los productos base y sus restricciones dentro de ellos
        LinkedList<Producto> productosBase = new LinkedList<Producto>();
        for (int i = 0; i < 4; i++) {
            Producto producto = null;
            try {
                producto = ProductosFactory.getProducto(i + 1);
            }
            catch (ProductCreationException ex) {
            }
            productosBase.add(producto);
        }

        for (Mutators mutatorMethod : keys) {
            double getCoverageOfMethod = coverageMethods.get(mutatorMethod);
            if (getCoverageOfMethod > 0) {
                IMutator mutator = mutatorClasses.get(mutatorMethod);

                // total habitantes
                int numberOfPopulation = poblacionOriginal.size();

                // porcentage a pasar por este metodo
                double percentageOfCopiesToBeGeneratedByThisMethod = getCoverageOfMethod * DEFAULT_SURVIVORS_BY_MUTATORS_METHODS / 100;

                // total de copias de acuerdo al porcentaje anterior
                Double numberOfCopiesForThisMethod = percentageOfCopiesToBeGeneratedByThisMethod * numberOfPopulation / 100;

                int individuosToGenerate = numberOfCopiesForThisMethod.intValue();
                
                 // genero las individuos que necesito
                int intentosToGenerate = 3;
                
                // genero las individuos que necesito
                while (individuosToGenerate > 0) {
                    int chosenOne = MathUtils.getRandomNumber(0, numberOfPopulation-1);
                    try {
                        Individuo son = mutator.doMutation(poblacionOriginal.get(chosenOne), productosBase);
                        boolean alreadyThere = false;
                        for (Individuo individuo : poblacionOriginal) {
                            if (individuo.equalsTo(son)){
                                alreadyThere = true;
                                break;
                            }
                        }
                        
                        if (!alreadyThere || intentosToGenerate == 0){
                            individuosToGenerate--;
                            intentosToGenerate = 3;
                            newPopulation.add(son);
                        } else {
                            intentosToGenerate--;
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
