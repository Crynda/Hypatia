package test;

import java.io.IOException;


import estadistica.interpretacion.GeneradorInterpretacion;
import estadistica.interpretacion.InterpretacionRegresion;
import estadistica.modelos.Muestra;
import estadistica.regresion.RegresionLineal;
import estadistica.util.*;
import gui.status.EstadoSesion;

import java.awt.image.BufferedImage;
import java.io.File;

import gui.util.export.PDFExport;



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
	
		double[] datos = {10, 20, 30, 40};

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
        System.out.println("Desviacion estandar: " + Math.sqrt(muestra.varianza()));

        
        //double[] x = {10, 2, 14, 0, 6, 8, 3, 12, 1, 15, 5, 11, 7, 4, 16};
        //double[] y = {3.2, 7.5, 2, 8.5, 4.8, 4, 6.8, 2.5, 8, 1.8, 5.5, 3, 4.5, 6, 1.5};
        double[] x = {1, 2, 3, 4, 5};
        double[] y = {2, 4, 5, 4, 5};
        
        RegresionLineal regresion = new RegresionLineal(x, y);
        regresion.calcular();
        
        
        EstadoSesion estado = new EstadoSesion();
        estado.setX(x);
        estado.setY(y);
        estado.setNombreX("Horas");
        estado.setNombreY("Calificación");
        estado.setRegresion(regresion);

        InterpretacionRegresion interpretacion = GeneradorInterpretacion.generar(estado, 2);

        System.out.println("\n=== INTERPRETACIÓN ===");

        System.out.println("Semáforo: " + interpretacion.getSemaforo());
        System.out.println("Calidad: " + interpretacion.getCalidad());
        System.out.println("Fuerza: " + interpretacion.getFuerza());
        System.out.println("Dirección: " + interpretacion.getDireccion());

        System.out.println("R²: " + interpretacion.getR2Pct() + "%");
        System.out.println("Se relativo: " + interpretacion.getSeRelPct() + "%");

        System.out.println();

        System.out.println("Ejemplo X: " + interpretacion.getEjemploX());
        System.out.println("Ejemplo Y: " + interpretacion.getEjemploY());

        System.out.println("Pendiente dirección: " + interpretacion.getPendienteDir());

        System.out.println("Pendiente valor: " + interpretacion.getPendienteVal());

        System.out.println();

        System.out.println("Pregunta 1: " + interpretacion.getPregunta1().getPregunta());
        System.out.println("Respuesta: " + interpretacion.getPregunta1().getRespuesta());
        System.out.println("Detalle: " + interpretacion.getPregunta1().getDetalle());

        System.out.println();

        System.out.println("Pregunta 2: " + interpretacion.getPregunta2().getPregunta());
        System.out.println("Respuesta: " + interpretacion.getPregunta2().getRespuesta());
        System.out.println("Detalle: " + interpretacion.getPregunta2().getDetalle());

        System.out.println();

        System.out.println("Pregunta 3: " + interpretacion.getPregunta3().getPregunta());
        System.out.println("Respuesta: " + interpretacion.getPregunta3().getRespuesta());
        System.out.println("Detalle: " + interpretacion.getPregunta3().getDetalle());

        System.out.println();

        System.out.println("Conclusión:");
        System.out.println(interpretacion.getConclusion());
        
        // =========================
        // PRUEBA DE EXPORTACIÓN PDF
        // =========================

        BufferedImage grafica = new BufferedImage(800, 500, BufferedImage.TYPE_INT_RGB);
        File archivoPDF = new File("prueba-regresion.pdf");
        //PDFExport.exportar(archivoPDF, interpretacion, grafica);

        System.out.println();
        System.out.println("PDF generado: " + archivoPDF.getAbsolutePath());
       
		
	}

}
