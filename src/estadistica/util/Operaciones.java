package estadistica.util;

public final class Operaciones {

	/**
	 * Estructura y funcionamiento general:
	 * @param datos (obtiene arreglos numericos)
	 * @return suma, promedio, etc
	 */
	
	private Operaciones() {
		
	}
	
	public static double suma(double[] datos) { //Calcula la suma de todos los elementos
		
		double suma = 0;
		
		for (double dato : datos) {
			suma += dato;
		}
		
		return suma;
	}
	
	public static double promedio(double[] datos) {//Calcula el promedio de un conjunto
		
		return suma(datos) / datos.length;
	}
	
	public static double sumaCuadrados(double[] datos) {
		
		double suma = 0;
		
		for (double dato : datos) {
			suma += Math.pow(dato, 2);
		}
		
		return suma;
	}
	
	public static double varianza(double[] datos) { //Calcula la varianza poblacional
		
		double promedio = promedio(datos);
		double suma = 0;
		
		for (double dato : datos) {
			suma += Math.pow(dato - promedio, 2);
		}
		
		return suma / datos.length;
	}
	
	public static double desviacionEstandar(double[] datos) { //Calcula la poblacion estandar poblacional
		
		return Math.sqrt(varianza(datos));
	}
	
	//Metodos Muestrales
	
	public static double varianzaMuestral(double[] datos) {
		
		double promedio = promedio(datos);
		double suma = 0;
		
		for (double dato : datos) {
			suma += Math.pow(dato - promedio, 2);
		}
		
		return suma / (datos.length - 1);
	}
	
	public static double desviacionEstandarMuestral(double[] datos) {
		return Math.sqrt(varianzaMuestral(datos));
	}
}
