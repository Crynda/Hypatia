package estadistica.util;

public final class DistribucionT {

	/**
	 * Por agregar:
	 * Tabla T
	 * integrales
	 * gamma
	 * aproximaciones
	 */
	
	private DistribucionT() {
		
	}
	
	public static double calcularT(double mediaMuestral, double mediaPoblacional, double desviacionMuestral, int n) {
		return (mediaMuestral - mediaPoblacional) / (desviacionMuestral / Math.sqrt(n));
		/**
		 * Calcula el Estadistico T para una muestra
		 * (se usa en pruebas de hipotesis cuando la desviacion poblaiconal se desconoce)
		 */
	}
	
	public static int gradosLibertad(int n) {//Calcula los grados de libertad de una muestra
		return n - 1;
	}
	
	/**
	 * Posibles metodos a agregar:
	 * valorCriticoT
	 * probabilidad
	 */
	
}
