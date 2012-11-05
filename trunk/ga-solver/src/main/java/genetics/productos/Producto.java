package genetics.productos;

import genetics.cromosomas.IGen;

public abstract class Producto implements IGen, Cloneable {

    protected int[] materiasPrimas;
    protected double profitValue;
    protected final int[] restriccionesMin = new int[8];
    protected final int[] restriccionesMax = new int[8];
    
     protected int[] restriccionesUsed = new int[8];

    public Producto() {
        materiasPrimas = new int[8];
    }

    public int[] getMateriasPrimas() {
        return materiasPrimas;
    }

    public void setMateriasPrimas(int[] materiasPrimas) {
        this.materiasPrimas = materiasPrimas;
    }

    public double getProfitValue() {
        return profitValue;
    }

    public void setProfitValue(double profitValue) {
        this.profitValue = profitValue;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    public int[] getRestriccionesMin() {
        return restriccionesMin;
    }

    public int[] getRestriccionesMax() {
        return restriccionesMax;
    }

    public int[] getRestriccionesUsed() {
        return restriccionesUsed;
    }

    public void setRestriccionesUsed(int[] restriccionesUsed) {
        this.restriccionesUsed = restriccionesUsed;
    }

    @Override
    public String toString() {
        String response = "Ganancia del Producto por Unidad = " + profitValue + "\n" ;
        for (int i = 0; i < restriccionesUsed.length; i++) {
            int j = restriccionesUsed[i];
            response += "           Materia Utilizada por Unidad "+(i+1)+": "+j+"\n";
        }
        return response;
    }

    


}
