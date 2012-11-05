/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package genetics.functions.cruzas;

import com.frre.cemami.utils.MathUtils;
import genetics.individuos.Individuo;
import genetics.individuos.IndividuosFactory;
import genetics.productos.Producto;
import genetics.productos.exceptions.NoMateriaPrimaAddedException;
import genetics.productos.exceptions.ProductCreationException;
import java.util.LinkedList;
import java.util.Random;

/**
 *
 * @author Justo Vargas
 */
public class CruzaBinomial implements ICruzator {

    protected Random random = new Random();

    public Individuo makeCruza(Individuo father, Individuo mother) throws ProductCreationException, NoMateriaPrimaAddedException {

        int[] productos = new int[father.getTotalDiferrentProducts()];

        int totalProducts = productos.length;

        double MAX_GENES_OF_FATHER = MathUtils.getRandomNumber(0, 100);

        //armo una lista donde voy a guardar las configuraciones por producto
        LinkedList<LinkedList<Integer>> materiaPrima = new LinkedList<LinkedList<Integer>>();
        for (int i = 0; i < 4; i++) {
            LinkedList<Integer> linkedList = new LinkedList<Integer>();
            materiaPrima.add(linkedList);
        }

        for (int i = 0; i < totalProducts; i++) {
            double randomNumber = random.nextDouble() * 100;

            int[] rest;
            if (randomNumber >= 0 && randomNumber <= MAX_GENES_OF_FATHER) {
                productos[i] = father.getProductsSize(i + 1);
                Producto p = father.getProductAt(i + 1);
                if (p != null) {
                    rest = p.getRestriccionesUsed();
                    for (int j = 0; j < rest.length; j++) {
                        materiaPrima.get(i).add(j, rest[j]);
                    }
                }
            } else {
                productos[i] = mother.getProductsSize(i + 1);
                Producto p = mother.getProductAt(i + 1);
                if (p != null) {
                    rest = p.getRestriccionesUsed();
                    for (int j = 0; j < rest.length; j++) {
                        materiaPrima.get(i).add(j, rest[j]);
                    }
                }
            }

        }

        Individuo individuo = IndividuosFactory.getInstance().createIndividuo(productos, materiaPrima);
        return individuo;
    }
}
