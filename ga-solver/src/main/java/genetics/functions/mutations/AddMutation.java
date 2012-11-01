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

/**
 *
 * @author Justo Vargas
 */
public class AddMutation implements IMutator {

    public Individuo doMutation(Individuo individuo) throws ProductCreationException, NoMateriaPrimaAddedException {
        Individuo mutatedInd = null;

        int[] productos = new int[individuo.getTotalDiferrentProducts()];

        int totalProducts = productos.length;

        int genNumber1 = MathUtils.getRandomNumber(0, totalProducts-1);

         for (int i = 0; i < totalProducts; i++) {
            int productsSize = 0;
            if (genNumber1==(i)){
                productsSize = individuo.getProductsSize(i+1)+1;
            } else {
                productsSize = individuo.getProductsSize(i+1);
            }
           productos[i] = productsSize;
        }
        mutatedInd = IndividuosFactory.getInstance().createIndividuo(productos);
        return mutatedInd;
    }
}
