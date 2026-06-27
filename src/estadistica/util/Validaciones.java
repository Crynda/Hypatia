package estadistica.util;

public final class Validaciones {

	/**
	 *Esta clase se encarga de: 
	 *Evitar divisiones entre 0
	 *Arrays vacios
	 *errores null
	 *tamannios incompatibles
	 *errores matematicos
	 *
	 */
	
	private Validaciones() {
		
	}
	
	public static boolean arregloVacio(double[] datos) {
		return datos == null || datos.length == 0; 
	}
	
	public static boolean longitudMinima(double[] datos, int minimo) {
		return datos != null && datos.length >= minimo;
	}
	
	public static boolean mismaLongitud(double[] a, double[] b) {
		return a != null && b != null
				&& a.length == b.length;
	}
	
	public static boolean contieneNegativos(double[] datos) {
		
		for (double dato : datos) {
			
			if (dato < 0) {
				return true;
			}
		}
		
		return false;
	}
	
}
