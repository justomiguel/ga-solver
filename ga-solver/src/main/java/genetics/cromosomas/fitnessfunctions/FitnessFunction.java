/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package genetics.cromosomas.fitnessfunctions;

import com.frre.cemami.utils.DefaultLogguer;
import genetics.individuos.Individuo;
import genetics.individuos.IndividuosFactory;
import genetics.productos.Producto;
import genetics.productos.ProductosFactory;
import genetics.productos.exceptions.NoMateriaPrimaAddedException;
import genetics.productos.exceptions.ProductCreationException;
import java.util.LinkedList;

/**
 *
 * @author Justo Vargas
 */
public final class FitnessFunction {

    public static DefaultLogguer logguer = DefaultLogguer.getLogger();
    public static final int MIN_DISTANCE_BETWEEN_MAX = 50;

    public static double getMaxFitnessValue() throws NoMateriaPrimaAddedException {
        double fitnessValue = 0.0;

        
        int products = 4;
        for (int i = 1; i < products+1; i++) {
            Producto producto = null;
            try {
                producto = ProductosFactory.getProducto(i);
            }
            catch (ProductCreationException ex) {
                logguer.logError(logguer, "Can not get product", ex);
            }
            int maxProductSize = IndividuosFactory.getInstance().maxQuantityOfProductToBeCreated(i);
            double productValue = producto.getProfitValue();
            fitnessValue += maxProductSize * productValue;
        }

        return fitnessValue;
    }

    public static double getFitnessValue(Individuo individuo) throws NoMateriaPrimaAddedException {
        double fitnessValue = 0.0;

        int products = individuo.getTotalDiferrentProducts() + 1;
        for (int i = 1; i < products; i++) {
            Producto producto = null;
            try {
                producto = ProductosFactory.getProducto(i);
            }
            catch (ProductCreationException ex) {
                logguer.logError(logguer, "Can not get product", ex);
            }
            int productsSize = individuo.getProductsSize(i);
            int maxProductSize = IndividuosFactory.getInstance().maxQuantityOfProductToBeCreated(i);
            double productValue = producto.getProfitValue();

            fitnessValue += maxProductSize * productValue - productsSize * productValue;

        }

        //manejo los desperdicios
        boolean hasSobrantes = individuo.getMateriaPrimaSobrante() > 0;

        fitnessValue += (hasSobrantes)? 25*fitnessValue/100 :0;

        return fitnessValue;
    }
}
