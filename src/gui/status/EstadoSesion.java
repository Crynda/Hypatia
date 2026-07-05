package gui.status;

import estadistica.regresion.RegresionLineal;

public class EstadoSesion {

    private double[] x;
    private double[] y;

    private RegresionLineal regresion;

    private boolean datosCargados;
    private boolean regresionCalculada;
    
    
	public double[] getX() {
		return x;
	}
	public void setX(double[] x) {
		this.x = x;
	}
	public double[] getY() {
		return y;
	}
	public void setY(double[] y) {
		this.y = y;
	}
	public RegresionLineal getRegresion() {
		return regresion;
	}
	public void setRegresion(RegresionLineal regresion) {
		this.regresion = regresion;
	}
	public boolean getDatosCargados() {
		return datosCargados;
	}
	public void setDatosCargados(boolean datosCargados) {
		this.datosCargados = datosCargados;
	}
	public boolean getRegresionCalculada() {
		return regresionCalculada;
	}
	public void setRegresionCalculada(boolean regresionCalculada) {
		this.regresionCalculada = regresionCalculada;
	}
	
	public void imprimirDatos() {

	    System.out.println("=== Arreglo X ===");
	    if (x != null) {
	        for (double valor : x) {
	            System.out.println(valor);
	        }
	    } else {
	        System.out.println("X es null");
	    }

	    System.out.println("\n=== Arreglo Y ===");
	    if (y != null) {
	        for (double valor : y) {
	            System.out.println(valor);
	        }
	    } else {
	        System.out.println("Y es null");
	    }
	}
    
    

}