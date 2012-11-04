package genetics.productos.types;

import genetics.productos.Producto;

public class Producto1 extends Producto {
	
	static {
		restriccionesMin[0] = 80;
		restriccionesMin[1] = 0;
		restriccionesMin[2] = 44;
		restriccionesMin[3] = 72;
		restriccionesMin[4] = 0;
		restriccionesMin[5] = 55;
		restriccionesMin[6] = 22;
		restriccionesMin[7] = 0;
	}
        
        static {
		restriccionesMax[0] = 95;
		restriccionesMax[1] = 0;
		restriccionesMax[2] = 65;
		restriccionesMax[3] = 88;
		restriccionesMax[4] = 0;
		restriccionesMax[5] = 77;
		restriccionesMax[6] = 50;
		restriccionesMax[7] = 0;
	}

	public Producto1(){
		setProfitValue(90);
	}
}
