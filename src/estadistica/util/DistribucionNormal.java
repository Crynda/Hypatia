package estadistica.util;

public final class DistribucionNormal {

	private DistribucionNormal() {
		
	}
	
	public static double calcularZ(double x, double media, double desviacion) {//Calcula el numero de desviaciones estandar de un valor que hay respecto a la media
		return (x - media) / desviacion;
	}
	
	
	public static double densidad(double x, double media, double desviacion) {//Calcula la funcion de densidad de probabilidad de una distribucion normal
		
		double exponente =  - Math.pow(x - media, 2) / (2 * Math.pow(desviacion, 2));
		
		return (1 / (desviacion * Math.sqrt(2 * Math.PI))) * Math.exp(exponente);
	}
	
}
