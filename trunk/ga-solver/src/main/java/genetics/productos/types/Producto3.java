package genetics.productos.types;

import genetics.productos.Producto;

public class Producto3 extends Producto {

	static {
		restriccionesMin[0] = 0;
		restriccionesMin[1] = 84;
		restriccionesMin[2] = 0;
		restriccionesMin[3] = 34;
		restriccionesMin[4] = 62;
		restriccionesMin[5] = 62;
		restriccionesMin[6] = 43;
		restriccionesMin[7] = 0;
	}
        static {
		restriccionesMax[0] = 0;
		restriccionesMax[1] = 99;
		restriccionesMax[2] = 0;
		restriccionesMax[3] = 55;
		restriccionesMax[4] = 78;
		restriccionesMax[5] = 70;
		restriccionesMax[6] = 65;
		restriccionesMax[7] = 0;
	}

	public Producto3(){
		setProfitValue(120);
	}
}
