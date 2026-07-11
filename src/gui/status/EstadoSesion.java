package gui.status;

import estadistica.regresion.RegresionLineal;
import gui.status.graphics.GraficoSesion;

public class EstadoSesion {

    private double[] x;
    private double[] y;
    
    private String nombreX = "X";
    private String nombreY = "Y";


    private RegresionLineal regresion;
    private GraficoSesion grafico;

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
	
	
	//Metadatos
	
	public void setNombreX(String n) {
		nombreX = n;
	}
	
	public void setNombreY(String n) {
		nombreY = n;
	}
	
	public String getNombreX() {
		return nombreX;
	}
	
	public String getNombreY() {
		return nombreY;
	}
	
	public void imprimirDatos() {

	    System.out.println("========== ESTADO DE SESION ==========");
	    
	    // =========================
	    // DATOS X
	    // =========================

	    System.out.println("\n=== Arreglo X ===");

	    if (x != null) {

	        for (double valor : x) {
	            System.out.println(valor);
	        }

	    } else {
	        System.out.println("X es null");
	    }

	    // =========================
	    // DATOS Y
	    // =========================

	    System.out.println("\n=== Arreglo Y ===");

	    if (y != null) {

	        for (double valor : y) {
	            System.out.println(valor);
	        }

	    } else {
	        System.out.println("Y es null");
	    }

	    // =========================
	    // METADATOS
	    // =========================

	    System.out.println("\n=== Metadatos ===");

	    System.out.println("Nombre X: " + nombreX);
	    System.out.println("Nombre Y: " + nombreY);

	    // =========================
	    // REGRESION
	    // =========================

	    System.out.println("\n=== Regresión ===");

	    if (regresion != null) {

	        System.out.println("Pendiente: " 
	                + regresion.getPendiente());

	        System.out.println("Intercepto: "
	                + regresion.getIntercepto());

	        System.out.println("R²: "
	                + regresion.getR2());

	        System.out.println("R: "
	                + regresion.getR());

	    } else {

	        System.out.println("Regresion es null");

	    }

	    // =========================
	    // GRAFICO
	    // =========================

	    System.out.println("\n=== Grafico Sesion ===");

	    if (grafico != null) {

	        System.out.println("Min X: "
	                + grafico.getMinX());

	        System.out.println("Max X: "
	                + grafico.getMaxX());

	        System.out.println("Min Y: "
	                + grafico.getMinY());

	        System.out.println("Max Y: "
	                + grafico.getMaxY());


	        System.out.println("\n--- Puntos de dispersion ---");


	        if (grafico.getDatos() != null) {

	            for (int i = 0; i < grafico.getDatos().length; i++) {

	                System.out.println(
	                    "Punto " + i +
	                    " -> X: " +
	                    grafico.getDatos()[i].getX() +
	                    " | Y: " +
	                    grafico.getDatos()[i].getY()
	                );
	            }

	        } else {

	            System.out.println("Datos del grafico son null");

	        }



	        System.out.println("\n--- Recta de regresion ---");


	        if (grafico.getInicioRecta() != null 
	                && grafico.getFinRecta() != null) {


	            System.out.println(
	                "Inicio -> X: "
	                + grafico.getInicioRecta().getX()
	                + " | Y: "
	                + grafico.getInicioRecta().getY()
	            );


	            System.out.println(
	                "Fin -> X: "
	                + grafico.getFinRecta().getX()
	                + " | Y: "
	                + grafico.getFinRecta().getY()
	            );

	        } else {

	            System.out.println("Recta no configurada");

	        }


	    } else {

	        System.out.println("Grafico es null");

	    }

	    // =========================
	    // ESTADOS
	    // =========================

	    System.out.println("\n=== Banderas ===");

	    System.out.println(
	        "Datos cargados: " + datosCargados
	    );

	    System.out.println(
	        "Regresion calculada: " + regresionCalculada
	    );

	    System.out.println("\n======================================");
	}
    
	//Graficas
	public GraficoSesion getGrafico() {
	    return grafico;
	}


	public void setGrafico(GraficoSesion grafico) {
	    this.grafico = grafico;
	}
    

}