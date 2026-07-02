package app;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import estadistica.modelos.Muestra;
import estadistica.regresion.RegresionLineal;
import estadistica.util.*;
import gui.util.export.HTMLExport;
import gui.util.export.JSONExport;

public class Main {

	/**
	 * (Clase para pruebas)
	 * 
	 * Operaciones: Suma, promedio, varianza, desviacion
	 * Estadistica descriptiva: mediana, rango, maximo, minimo
	 * Distribucion normal: calcular Z, densidad
	 * Muestra: tamanio, promedio, varianza
	 * @throws IOException 
	 * 
	 */	
	
	public static void main(String[] args) throws IOException {
	
		double[] datos = {10, 20, 30, 40, 50};

        System.out.println("=== OPERACIONES ===");
        System.out.println("Suma: " + Operaciones.suma(datos));
        System.out.println("Promedio: " + Operaciones.promedio(datos));
        System.out.println("Varianza: " + Operaciones.varianza(datos));
        System.out.println("Desviación estándar: " + Operaciones.desviacionEstandar(datos));


        System.out.println("\n=== ESTADÍSTICA DESCRIPTIVA ===");
        System.out.println("Mediana: " + EstadisticaDescriptiva.mediana(datos));
        System.out.println("Máximo: " + EstadisticaDescriptiva.maximo(datos));
        System.out.println("Mínimo: " + EstadisticaDescriptiva.minimo(datos));
        System.out.println("Rango: " + EstadisticaDescriptiva.rango(datos));



        System.out.println("\n=== DISTRIBUCIÓN NORMAL ===");
        double z = DistribucionNormal.calcularZ(85, 70, 10);
        System.out.println("Valor Z: " + z);

        System.out.println("\n=== DISTRIBUCIÓN T ===");
        double t = DistribucionT.calcularT(75, 70, 8, 25);
        System.out.println("Valor T: " + t);
        System.out.println("Grados de libertad: " + DistribucionT.gradosLibertad(25));


        System.out.println("\n=== MUESTRA ===");
        Muestra muestra = new Muestra(datos);
        System.out.println("Tamaño: " + muestra.tamanio());
        System.out.println("Promedio muestra: " + muestra.promedio());
        System.out.println("Varianza muestra: " + muestra.varianza());
        
        //Estructurar mejor
        System.out.println("Desviacion estandar: "
                + Math.sqrt(muestra.varianza()));

        
        //double[] x = {10, 2, 14, 0, 6, 8, 3, 12, 1, 15, 5, 11, 7, 4, 16};
        //double[] y = {3.2, 7.5, 2, 8.5, 4.8, 4, 6.8, 2.5, 8, 1.8, 5.5, 3, 4.5, 6, 1.5};
        double[] x = {1, 2, 3, 4, 5};
        //double[] y = {2, 5, 4, 8, 7};
        double[] y = {2, 4, 5, 4, 5};
        
        RegresionLineal regresion = new RegresionLineal(x, y);
        regresion.calcular();
        

        System.out.println("\n=== EXPORTANDO HTML ===");

        String html = HTMLExport.exportarHTML(x, y, regresion, 2);

        File file = new File("reporte.html");
        Files.writeString(file.toPath(), html);

       
		
	}

}
