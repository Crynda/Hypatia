package estadistica.util;

public final class EstadisticaDescriptiva {
	//Clase hecha para el analisis estadistico descriptivo de las operaciones
	
	private EstadisticaDescriptiva() {
		
	}
	
	public static double mediana(double[] datos) {
		
		double[] copia = datos.clone();
		java.util.Arrays.sort(copia);
		int n = copia.length;
		
		if (n % 2 == 0) {
			return (copia[n / 2 - 1] + copia[n / 2]) /2.0;
		}
		
		return copia[n / 2];
		
	}
	
	public static double rango(double[] datos) {//Obtiene el rango de un conjunto (diferencia entre el valor max y min)
		return maximo(datos) - minimo(datos);
	}
	
	
	public static double maximo(double[] datos) {//Obtiene el valor maximo dentro del array
		
		double max = datos[0];
		
		for (double dato : datos) {
			
			if (dato > max) {
				max = dato;
			}
		}
		return max;
	}
	
	public static double minimo(double[] datos) {//Obtiene el valor minimo dentro del array
		
		double min = datos[0];

		for (double dato : datos) {
			
			if (dato < min) {
				min = dato;
			}
		}
		return min;
		
	}
	
	public static double coeficienteVariacion(double[] datos) {//Mide la dispersion relativa respecto a la media
		return (Operaciones.desviacionEstandar(datos) / Operaciones.promedio(datos) * 100);
	}
	
}
