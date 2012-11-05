/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package genetics.functions.mutations;

import com.frre.cemami.utils.MathUtils;
import genetics.individuos.Individuo;
import genetics.individuos.IndividuosFactory;
import genetics.productos.Producto;
import genetics.productos.exceptions.NoMateriaPrimaAddedException;
import genetics.productos.exceptions.ProductCreationException;
import java.util.LinkedList;

/**
 *
 * @author Justo Vargas
 */
public class ZeroMutation implements IMutator {

    public Individuo doMutation(Individuo individuo, LinkedList<Producto> productosBase) throws ProductCreationException, NoMateriaPrimaAddedException {
        Individuo mutatedInd = null;

        int[] productos = new int[individuo.getTotalDiferrentProducts()];

        int totalProducts = productos.length;

        int genNumber1 = MathUtils.getRandomNumber(0, totalProducts - 1);

        for (int i = 0; i < totalProducts; i++) {
            int productsSize = 0;
            if (genNumber1 == ( i )) {
                productsSize = 0;
            } else {
                productsSize = individuo.getProductsSize(i + 1);
            }
            productos[i] = productsSize;
        }

        //armo una lista donde voy a guardar las configuraciones por producto
        LinkedList<LinkedList<Integer>> materiaPrima = new LinkedList<LinkedList<Integer>>();
        for (int i = 0; i < 4; i++) {
            LinkedList<Integer> linkedList = new LinkedList<Integer>();
            materiaPrima.add(linkedList);
        }

        for (int i = 0; i < productos.length; i++) {
            int j = productos[i];
            if (j > 0) {
                Producto theProduct = productosBase.get(i);
                Producto myProduct = individuo.getProductAt(i + 1);
                for (int k = 0; k < 8; k++) {
                    int restriccionMaxima = theProduct.getRestriccionesMax()[k];
                    int restriccionMinima = theProduct.getRestriccionesMin()[k];
                    int restriccionToBeUsed = 0;
                    if (( Math.random() > 0.5 )) {
                        restriccionToBeUsed = restriccionMinima;
                    } else {
                        if (myProduct != null) {
                            restriccionToBeUsed = myProduct.getRestriccionesUsed()[k];
                        } else {
                            restriccionToBeUsed = MathUtils.getRandomNumber(restriccionMinima, restriccionMaxima);
                        }
                    }
                    materiaPrima.get(i).add(k, restriccionToBeUsed);
                }
            }
        }

        mutatedInd = IndividuosFactory.getInstance().createIndividuo(productos, materiaPrima);
        return mutatedInd;
    }
}
