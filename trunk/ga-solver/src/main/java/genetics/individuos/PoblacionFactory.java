package genetics.individuos;

import com.frre.cemami.utils.DefaultLogguer;
import com.frre.cemami.utils.MathUtils;
import genetics.productos.Producto;
import genetics.productos.ProductosFactory;
import genetics.productos.exceptions.NoMateriaPrimaAddedException;
import genetics.productos.exceptions.ProductCreationException;
import java.util.LinkedList;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PoblacionFactory {

    static DefaultLogguer logguer = DefaultLogguer.getLogger();
    private static PoblacionFactory instance;

    private PoblacionFactory() {
    }

    public static PoblacionFactory getInstance() {
        if (instance == null) {
            instance = new PoblacionFactory();
        }
        return instance;
    }

    public LinkedList<Individuo> createInitialRandomPopulation(Poblacion p, int initialPopulation, LinkedList<Integer> materiasPrimas) throws NoMateriaPrimaAddedException {

        LinkedList<Individuo> initialPop = new LinkedList<Individuo>();

        IndividuosFactory.getInstance().setMateriasPrimas(materiasPrimas);

        //ass= this is the first step when creating a population I clear the materias primas collection
        //to get the new numbers 
        IndividuosFactory.getInstance().clearMateriasPrimas();

        LinkedList<Integer> maxQuantities = new LinkedList<Integer>();
        for (int i = 0; i < 4; i++) {
            int number = IndividuosFactory.getInstance().maxQuantityOfProductToBeCreated(i + 1);
            logguer.logInfo("Max of " + i + " " + number);
            maxQuantities.add(number);
        }

        int[] productos = new int[4];


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

        //armo una lista donde voy a guardar las configuraciones por producto
        LinkedList<LinkedList<Integer>> materiaPrima = new LinkedList<LinkedList<Integer>>();
        for (int i = 0; i < 4; i++) {
            LinkedList<Integer> linkedList = new LinkedList<Integer>();
            materiaPrima.add(linkedList);
        }

        Random r = new Random();
        int currentSize = 0;
        while (currentSize < initialPopulation) {
            productos[0] = getProductSize(0, maxQuantities, r);
            productos[1] = getProductSize(1, maxQuantities, r);
            productos[2] = getProductSize(2, maxQuantities, r);
            productos[3] = getProductSize(3, maxQuantities, r);

            for (int i = 0; i < productos.length; i++) {
                int j = productos[i];
                if (j > 0) {
                    for (int k = 0; k < 8; k++) {
                        Producto theProduct = productosBase.get(i);
                        int restriccionMinima = theProduct.getRestriccionesMin()[k];
                        int restriccionMaxima = theProduct.getRestriccionesMax()[k];
                        materiaPrima.get(i).add(k, MathUtils.getRandomNumber(restriccionMinima, restriccionMaxima));
                    }
                }
            }

            try {
                Individuo individuo = IndividuosFactory.getInstance().createIndividuo(productos);
                boolean alreadyThere = false;
                for (Individuo muchachin : initialPop) {
                    if (muchachin.equalsTo(individuo)) {
                        alreadyThere = true;
                        break;
                    }
                }
                if (!alreadyThere) {
                    initialPop.add(individuo);
                    currentSize++;
                    int percetageOfSucces = currentSize * 100 / initialPopulation;
                    p.updateUIProgress(5 + percetageOfSucces * 40 / 100);
                }
            }
            catch (ProductCreationException ex) {
                //logguer.logError(this, "No pudo crear producto por que materia prima insuficiente", ex);
            }
        }

        return initialPop;
    }

    public LinkedList<Individuo> cloneInitialPopulation(LinkedList<Individuo> initialPop) {
        LinkedList<Individuo> ind = new LinkedList<Individuo>();
        for (int i = 0; i < initialPop.size(); i++) {
            Individuo individuo = null;
            try {
                individuo = (Individuo) initialPop.get(i).clone();
            }
            catch (CloneNotSupportedException ex) {
                logguer.logError(this, "Can Not Clone Element due to ", ex);
            }
            ind.add(individuo);
        }
        return ind;
    }

    private int getProductSize(int productNumber, LinkedList<Integer> maxQuantities, Random r) {
        double number = r.nextDouble();
        if (number < 0.25) {
            return maxQuantities.get(productNumber);
        } else if (number < 0.50) {
            return MathUtils.getRandomNumber(0, maxQuantities.get(productNumber) / 2);
        } else if (number < 0.75) {
            return MathUtils.getRandomNumber(maxQuantities.get(productNumber) / 2, maxQuantities.get(productNumber));
        } else {
            return 0;
        }
    }
}
