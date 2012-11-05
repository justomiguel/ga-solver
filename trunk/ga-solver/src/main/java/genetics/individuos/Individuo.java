package genetics.individuos;

import com.frre.cemami.utils.DefaultLogguer;
import genetics.cromosomas.ICromosoma;
import genetics.productos.Producto;
import java.util.LinkedList;

public class Individuo implements ICromosoma, Comparable<Individuo>, Cloneable {

    private static int DEFAULT_DIFFERENT_PRODUCTS = 4;
    private double fitnessValue;
    private LinkedList<LinkedList<Producto>> productos;

    public Individuo() {
        this(DEFAULT_DIFFERENT_PRODUCTS);
    }

    public Individuo(int productSize) {
        productos = new LinkedList<LinkedList<Producto>>();
        for (int i = 0; i < productSize; i++) {
            productos.add(i, new LinkedList<Producto>());
        }
    }
    
    public double getProfit(){
        double profit = 0;
        int products = this.getTotalDiferrentProducts()+1;
        for (int i = 1; i < products; i++) {
            int productsSize = this.getProductsSize(i);
            double productValue = 0;
            Producto p = this.getProductAt(i);
            if (p!= null){
                productValue = p.getProfitValue();
            }
            profit += productsSize*productValue ;
        }
        return profit;
    }

    public void add(int index, Producto element) {
        productos.get(index).add(element);
    }

    public int getProductsSize(int productNumber) {
        return productos.get(productNumber - 1).size();
    }

    public LinkedList<Producto> getProductsAt(int productNumber) {
        return productos.get(productNumber - 1);
    }

    public Producto getProductAt(int productNumber) {
        if (productos.get(productNumber - 1).size() > 0) {
            return productos.get(productNumber - 1).get(0);
        }
        return null;
    }

    public int getTotalDiferrentProducts() {
        return productos.size();
    }

    @Override
    public Double getFitnessValue() {
        // TODO Auto-generated method stub
        return fitnessValue;
    }

    @Override
    public void mutate() {
        // TODO Auto-generated method stub
    }
    
    public boolean equalsTo(Individuo another){
        int products = this.getTotalDiferrentProducts()+1;
        for (int i = 1; i < products; i++) {
            int myProductsSize = this.getProductsSize(i);
            int theirProductSize = another.getProductsSize(i);
            if (another.getProductsSize(i) != myProductsSize){
                return false;
            }
            if (myProductsSize!=0 && theirProductSize!=0){
                Producto myProduct = this.getProductAt(i);
                Producto theirProduct = another.getProductAt(i);
                int[] myRest = myProduct.getRestriccionesUsed();
                int[] theRest = theirProduct.getRestriccionesUsed();
                int size = myRest.length;
                for (int j = 0; j < size; j++) {
                    if (myRest[j] != theRest[j]){
                        return false;
                    }
                }
            }
        }
        DefaultLogguer.getLogger().logError("Por materias primas iguales");
        return true;
    }

    @Override
    public ICromosoma doCruza(ICromosoma cromosoma) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void setFitnessValue(double fitnessValue) {
        this.fitnessValue = fitnessValue;
    }


    @Override
    public Object clone() throws CloneNotSupportedException {
        Individuo clone = (Individuo) super.clone();
        LinkedList<LinkedList<Producto>> newProductS = new LinkedList<LinkedList<Producto>>();
        for (LinkedList<Producto> productList : productos) {
            LinkedList<Producto> newProductList = new LinkedList<Producto>();
            for (Producto producto : productList) {
                Producto cloneProduct = (Producto) producto.clone();
                newProductList.add(producto);
            }
            newProductS.add(productList);
        }
        clone.productos = newProductS;
        return clone;
    }

    /*
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((productos == null) ? 0 : productos.hashCode());
        return result;
    }*/

    @Override
    public int compareTo(Individuo o) {
        // TODO Auto-generated method stub
        if (o.getFitnessValue() > this.fitnessValue) {
            return -1;
        } else if (o.getFitnessValue() < this.fitnessValue) {
            return 1;
        } else {
            return 0;
        }
        // return o.getFitnessValue().compareTo();
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(" Ganancia = ");
        builder.append(getProfit());
        builder.append(" \n  Aptitud = ");
        builder.append(fitnessValue);
        
        int productsNumber = productos.size();
        for (int i = 0; i < productsNumber; i++) {
            builder.append("\n  Producto ");
            builder.append(i+1);
            builder.append(": ");
            builder.append(productos.get(i).size());
            builder.append("\n");
            Producto p = this.getProductAt(i+1);
            if (p!= null){
                builder.append("     ");
                builder.append(p);
            }
        }
        return  builder.toString();
    }
}
