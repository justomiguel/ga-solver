package genetics.individuos;

import com.frre.cemami.utils.DefaultLogguer;
import genetics.cromosomas.fitnessfunctions.FitnessFunction;
import genetics.productos.Producto;
import genetics.productos.ProductosFactory;
import genetics.productos.exceptions.NoMateriaPrimaAddedException;
import genetics.productos.exceptions.ProductCreationException;
import java.util.HashMap;
import java.util.LinkedList;

public class IndividuosFactory {

    static DefaultLogguer logguer = DefaultLogguer.getLogger();
    private static IndividuosFactory instance;
    private HashMap<Integer, Integer> maxQuantitiesOfProduct;
    private LinkedList<Integer> materiasPrimas;

    private IndividuosFactory() {
        maxQuantitiesOfProduct = new HashMap<Integer, Integer>();
        materiasPrimas = new LinkedList<Integer>();
    }

    public static IndividuosFactory getInstance() {
        if (instance == null) {
            instance = new IndividuosFactory();
        }
        return instance;
    }

    @SuppressWarnings("unchecked")
    public Individuo createIndividuo(int[] productos, LinkedList<LinkedList<Integer>> materiaPrima) throws ProductCreationException, NoMateriaPrimaAddedException {

        Individuo individuo = new Individuo();
        if (materiasPrimas.isEmpty()) {
            throw new NoMateriaPrimaAddedException("No se puede crear Individuo por que no se setearon las masterias primas");
        }
        LinkedList<Integer> materiasPrimasClone = (LinkedList<Integer>) materiasPrimas.clone();

        LinkedList<LinkedList<Producto>> bestProduction = new LinkedList<LinkedList<Producto>>();
        for (int i = 0; i < 4; i++) {
            bestProduction.add(new LinkedList<Producto>());
        }

        for (int i = 0; i < productos.length; i++) {
            int quantityOfProduct = productos[i];
            while (quantityOfProduct > 0) {
                Producto producto = ProductosFactory.getProducto(i + 1);
                int[] restriccionesMaxProducto = producto.getRestriccionesMax();
                int[] restriccionesMinProducto = producto.getRestriccionesMin();
                int[] restriccionesMinToBeUSed = new int[8];
                for (int j = 0; j < restriccionesMinProducto.length; j++) {
                    int montoMateriaPrimaMinimo = restriccionesMinProducto[j];
                    if (montoMateriaPrimaMinimo != 0) {
                        int montoMateriaPrimaMaximo = restriccionesMaxProducto[j];
                        int montoMateriaPrimaAUsar = materiaPrima.get(i).get(j);
                        restriccionesMinToBeUSed[j] = montoMateriaPrimaAUsar;
                        if (montoMateriaPrimaAUsar >= montoMateriaPrimaMinimo && montoMateriaPrimaAUsar <= montoMateriaPrimaMaximo) {
                            int materiaPrimaDisponible = materiasPrimasClone.get(j);
                            materiaPrimaDisponible = materiaPrimaDisponible - montoMateriaPrimaAUsar;
                            
                            if (materiaPrimaDisponible < 0) {
                                String errorDesc = "No hay suficiente materia prima para esta configuracion";
                                throw new ProductCreationException(errorDesc);
                            } else {
                                materiasPrimasClone.set(j, materiaPrimaDisponible);
                            }
                        } else {
                            String errorDesc = "Materia Prima Incorrecta";
                            throw new ProductCreationException(errorDesc);
                        }
                    }
                }
                quantityOfProduct--;
                producto.setRestriccionesUsed(restriccionesMinToBeUSed);
                individuo.add(i, producto);
                
            }
        }
        
        int sobrante = 0;
        for (Integer inte : materiasPrimasClone) {
            sobrante += inte;
        }
        
        individuo.setMateriaPrimaSobrante(sobrante);
        
        double fitnessValue = FitnessFunction.getFitnessValue(individuo);
        individuo.setFitnessValue(fitnessValue);
        
        if (individuo.getProfit() == 0) {
            throw new ProductCreationException("No permito insectos");
        }
        return individuo;
    }

    public LinkedList<Integer> getMateriasPrimas() {
        return materiasPrimas;
    }

    public void setMateriasPrimas(LinkedList<Integer> materiasPrimas) {
        this.materiasPrimas = materiasPrimas;
    }

    public HashMap<Integer, Integer> getMaxQuantitiesOfProduct() {
        return maxQuantitiesOfProduct;
    }

    public void setMaxQuantitiesOfProduct(HashMap<Integer, Integer> maxQuantitiesOfProduct) {
        this.maxQuantitiesOfProduct = maxQuantitiesOfProduct;
    }
    

    public int maxQuantityOfProductToBeCreated(int product) throws NoMateriaPrimaAddedException {
        Integer number = maxQuantitiesOfProduct.get(product);
        if (number == null) {
            if (materiasPrimas.isEmpty()) {
                throw new NoMateriaPrimaAddedException("No se puede crear Individuo por que no se setearon las masterias primas");
            }
            try {
                Producto producto = ProductosFactory.getProducto(product);
                int[] restricciones = producto.getRestriccionesMin();
                LinkedList<Integer> materiasPrimasClone = (LinkedList<Integer>) materiasPrimas.clone();
                number = Integer.MAX_VALUE;
                for (int i = 0; i < restricciones.length; i++) {
                    int materiaRequerida = restricciones[i];
                    if (materiaRequerida != 0) {
                        int materiaPrimaDisponible = materiasPrimasClone.get(i);
                        int diff = materiaPrimaDisponible / materiaRequerida;
                        if (diff < number) {
                            number = diff;
                        }
                    }
                }
            }
            catch (ProductCreationException ex) {
                logguer.logError(this, "Error when creating product", ex);
            }
            maxQuantitiesOfProduct.put(product, number);
        }
        return number;
    }

    public void clearMateriasPrimas() {
        this.maxQuantitiesOfProduct.clear();
    }
}
