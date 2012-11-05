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

/**
 *
 * @author Justo Vargas
 */
public class CruzaMultipunto implements ICruzator {

    public Individuo makeCruza(Individuo father, Individuo mother) throws ProductCreationException, NoMateriaPrimaAddedException {

        int[] productos = new int[father.getTotalDiferrentProducts()];

        int totalProducts = productos.length;

        int getPosition = MathUtils.getRandomNumber(1, totalProducts - 1);

        //armo una lista donde voy a guardar las configuraciones por producto
        LinkedList<LinkedList<Integer>> materiaPrima = new LinkedList<LinkedList<Integer>>();
        for (int i = 0; i < 4; i++) {
            LinkedList<Integer> linkedList = new LinkedList<Integer>();
            materiaPrima.add(linkedList);
        }

        for (int i = 0; i < getPosition; i++) {
            int[] rest;
            productos[i] = father.getProductsSize(i + 1);
            Producto p = father.getProductAt(i + 1);
            if (p != null) {
                rest = p.getRestriccionesUsed();
                for (int j = 0; j < rest.length; j++) {
                    materiaPrima.get(i).add(j, rest[j]);
                }
            }
        }

        for (int i = getPosition; i < totalProducts; i++) {
            int[] rest;
            productos[i] = mother.getProductsSize(i + 1);
            Producto p = mother.getProductAt(i + 1);
            if (p != null) {
                rest = p.getRestriccionesUsed();
                for (int j = 0; j < rest.length; j++) {
                    materiaPrima.get(i).add(j, rest[j]);
                }
            }
        }

        Individuo individuo = IndividuosFactory.getInstance().createIndividuo(productos, materiaPrima);
        return individuo;
    }
}
