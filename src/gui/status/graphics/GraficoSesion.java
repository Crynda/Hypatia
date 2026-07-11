package gui.status.graphics;

import estadistica.regresion.RegresionLineal;
import estadistica.util.EstadisticaDescriptiva;

public class GraficoSesion {

    private double[] x;
    private double[] y;

    private double minX;
    private double maxX;

    private double minY;
    private double maxY;

    private PuntoGrafico[] datos;

    private PuntoGrafico inicioRecta;
    private PuntoGrafico finRecta;


    public GraficoSesion(double[] x, double[] y, RegresionLineal regresion) {

        this.x = x;
        this.y = y;

        minX = EstadisticaDescriptiva.minimo(x);
        maxX = EstadisticaDescriptiva.maximo(x);

        minY = EstadisticaDescriptiva.minimo(y);
        maxY = EstadisticaDescriptiva.maximo(y);


        // Puntos de dispersión
        datos = new PuntoGrafico[x.length];

        for(int i = 0; i < x.length; i++) {

            datos[i] = new PuntoGrafico(
                    x[i],
                    y[i]
            );
        }


        // Recta de regresión

        double y1 =
                regresion.getIntercepto()
                + (regresion.getPendiente() * minX);


        double y2 =
                regresion.getIntercepto()
                + (regresion.getPendiente() * maxX);


        inicioRecta = new PuntoGrafico(minX, y1);
        finRecta = new PuntoGrafico(maxX, y2);
    }


    public double getMinX() {
        return minX;
    }

    public double getMaxX() {
        return maxX;
    }

    public double getMinY() {
        return minY;
    }

    public double getMaxY() {
        return maxY;
    }


    public PuntoGrafico[] getDatos() {
        return datos;
    }


    public PuntoGrafico getInicioRecta() {
        return inicioRecta;
    }


    public PuntoGrafico getFinRecta() {
        return finRecta;
    }

}