/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package genetics.cromosomas.external;

import genetics.individuos.Individuo;
import genetics.productos.Producto;
import java.util.LinkedList;

/**
 *
 * @author Justo Vargas
 */
public class ExternalData {

    private int[] productS;
    private LinkedList<LinkedList<Integer>> materiaPrima;

    public ExternalData(Individuo individuo) {
        int productsSize = individuo.getTotalDiferrentProducts();
        productS = new int[productsSize];
        for (int i = 0; i < productsSize; i++) {
            productS[i] = individuo.getProductsSize(i + 1);
        }
        //armo una lista donde voy a guardar las configuraciones por producto
        materiaPrima = new LinkedList<LinkedList<Integer>>();
        for (int i = 0; i < 4; i++) {
            LinkedList<Integer> linkedList = new LinkedList<Integer>();
            materiaPrima.add(linkedList);
        }

        for (int i = 0; i < productS.length; i++) {
            int j = productS[i];
            if (j > 0) {
                Producto theProduct = individuo.getProductAt(i+1);
                if (theProduct != null) {
                    for (int k = 0; k < 8; k++) {
                        int restriccionMinima = theProduct.getRestriccionesUsed()[k];
                        materiaPrima.get(i).add(k, restriccionMinima);
                    }
                }
            }
        }
    }

    public int[] getProductS() {
        return productS;
    }

    public void setProductS(int[] productS) {
        this.productS = productS;
    }

    public LinkedList<LinkedList<Integer>> getMateriaPrima() {
        return materiaPrima;
    }

    public void setMateriaPrima(LinkedList<LinkedList<Integer>> materiaPrima) {
        this.materiaPrima = materiaPrima;
    }
}
