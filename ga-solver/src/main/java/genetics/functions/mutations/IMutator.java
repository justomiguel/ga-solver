package genetics.functions.mutations;

import genetics.individuos.Individuo;
import genetics.productos.Producto;
import genetics.productos.exceptions.NoMateriaPrimaAddedException;
import genetics.productos.exceptions.ProductCreationException;
import java.util.LinkedList;

public interface IMutator {

    public Individuo doMutation(Individuo get, LinkedList<Producto> productosBase) throws ProductCreationException, NoMateriaPrimaAddedException;
}
