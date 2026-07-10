package estadistica.regresion;

import estadistica.modelos.ModeloEstadistico;
import estadistica.util.Operaciones;

public class RegresionLineal extends ModeloEstadistico {

	private double[] x;
	private double[] y;

	// Calculos
	private int n;
	private double promedioX;
	private double promedioY;
	private double b;
	private double a;
	private double r;
	private double r2;
	private double se;

	// Sumatorias
	private double sumaX;
	private double sumaY;
	private double sumaXY;
	private double sumaX2;
	private double sumaY2;
	
	//Parametros contextuales
	private String nombreX;
	private String nombreY;

	public RegresionLineal(double[] x, double[] y) {

		this.x = x;
		this.y = y;

	}
	
	public void calcularN() {
		n = x.length;
		System.out.println("n = " + n);
	}

	// Obtener promedios
	public void calcularPromedios() {
		promedioX = Operaciones.promedio(x);
		System.out.println("prom x = " + promedioX);

		promedioY = Operaciones.promedio(y);
		System.out.println("prom y = " + promedioY);
	}

	// Sumatorias
	public void calcularSumatorias() {

		sumaX = Operaciones.suma(x);
		sumaY = Operaciones.suma(y);

		for (int i = 0; i < x.length; i++) {

			sumaXY += x[i] * y[i];
			sumaX2 += Math.pow(x[i], 2);
			sumaY2 += Math.pow(y[i], 2);

		}

		System.out.println("Sumatoria X= " + sumaX);
		System.out.println("Sumatoria Y= " + sumaY);
		System.out.println("Sumatoria XY= " + sumaXY);
		System.out.println("Sumatoria X2= " + sumaX2);
		System.out.println("Sumatoria Y2= " + sumaY2);

	}

	public void calcularPendienteIntercepto() {

		double numerador = 0;
		double denominador = 0;

		for (int i = 0; i < x.length; i++) {

			numerador += (x[i] - promedioX) * (y[i] - promedioY);

			denominador += Math.pow(x[i] - promedioX, 2);
		}

		b = numerador / denominador; // b
		
		a = promedioY - (b * promedioX);
		

		System.out.println("b = " + b);
		System.out.println("a = " + a);
	}
	
	
	public void calcularR() {

	    double numerador = (n * sumaXY) - (sumaX * sumaY);

	    double denominador = Math.sqrt(((n * sumaX2) - Math.pow(sumaX, 2)) * ((n * sumaY2) - Math.pow(sumaY, 2)));

	    r = numerador / denominador; //Esto puede salir negativo?
	    
	    if (r < 0) { r = r * -1;}
	    
	    System.out.println("r = " + r);
	}

	public void calcularR2() {

	    r2 = Math.pow(r, 2);
	    System.out.println("r2 = " + r2);
	}

	public void calcularSe() {//Error estandar de estimacion
		se = Math.sqrt( ((sumaY2) - (a * sumaY) - (b * sumaXY)) / (n - 2));
		System.out.println("se = " + se);
	}
	
	@Override
	public void calcular() {

        System.out.println("\n=== Regresion Lineal (PRUEBA DE CONSOLA) ===");
        
		calcularN();
		calcularPromedios();
		
		System.out.println("Sumatorias");
		calcularSumatorias();

		calcularPendienteIntercepto();
		calcularR();
		calcularR2();
		calcularSe();
	}
	
	//Getters

	public double[] getX() {
		return x;
	}

	public double[] getY() {
		return y;
	}

	public int getN() {
		return n;
	}

	public double getPromedioX() {
		return promedioX;
	}

	public double getPromedioY() {
		return promedioY;
	}

	public double getPendiente() {
		return b;
	}

	public double getIntercepto() {
		return a;
	}

	public double getR() {
		return r;
	}

	public double getR2() {
		return r2;
	}

	public double getSe() {
		return se;
	}

	public double getSumaX() {
		return sumaX;
	}

	public double getSumaY() {
		return sumaY;
	}

	public double getSumaXY() {
		return sumaXY;
	}

	public double getSumaX2() {
		return sumaX2;
	}

	public double getSumaY2() {
		return sumaY2;
	}
	
	//Contextuales
	
	public void setNombres(String nombreX, String nombreY){
	    this.nombreX = nombreX;
	    this.nombreY = nombreY;
	}
	
	public String getNombreX() {
		return nombreX;
	}
	
	public String getNombreY() {
		return nombreY;
	}
	
	

	

}
