package estadistica.modelos;

import estadistica.util.Operaciones;

public class Muestra {

	private double[] datos;
	
	public Muestra(double[] datos) {
		this.datos = datos;
	}
	
	public double[] getDatos() {
		return datos;
	}
	
	public void setDatos(double[] datos) {
		this.datos = datos;
	}
	
	public int tamanio() {
		return datos.length;
	}
	
	public double promedio() {
		return Operaciones.promedio(datos);
	}
	
	public double varianza() {
		return Operaciones.varianza(datos);
	}
	
}
