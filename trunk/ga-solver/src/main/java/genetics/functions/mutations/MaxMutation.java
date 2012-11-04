/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package genetics.functions.mutations;

import com.frre.cemami.utils.MathUtils;
import genetics.individuos.Individuo;
import genetics.individuos.IndividuosFactory;
import genetics.productos.exceptions.NoMateriaPrimaAddedException;
import genetics.productos.exceptions.ProductCreationException;
import java.util.LinkedList;

/**
 *
 * @author Justo Vargas
 */
public class MaxMutation implements IMutator {

    public Individuo doMutation(Individuo individuo) throws ProductCreationException, NoMateriaPrimaAddedException {
        Individuo mutatedInd = null;

        int[] productos = new int[individuo.getTotalDiferrentProducts()];

        int totalProducts = productos.length;

        int genNumber1 = MathUtils.getRandomNumber(0, totalProducts-1);

        LinkedList<Integer> maxQuantities = new LinkedList<Integer>();
        for (int i = 0; i < 4; i++) {
            int number = IndividuosFactory.getInstance().maxQuantityOfProductToBeCreated(i+1);
            maxQuantities.add(number);
        }
        
         for (int i = 0; i < totalProducts; i++) {
            int productsSize;
            if (genNumber1==(i)){
                int number = maxQuantities.get(i); 
                productsSize = MathUtils.getRandomNumber(number/2, number);
            } else {
                productsSize = individuo.getProductsSize(i+1);
            }
           productos[i] = productsSize;
        }
        mutatedInd = IndividuosFactory.getInstance().createIndividuo(productos);
        return mutatedInd;
    }
}
