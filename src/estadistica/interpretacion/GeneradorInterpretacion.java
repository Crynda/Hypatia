package estadistica.interpretacion;

import estadistica.regresion.RegresionLineal;
import estadistica.util.Operaciones;
import gui.status.EstadoSesion;

public final class GeneradorInterpretacion {

    private GeneradorInterpretacion() {

    }

    public static InterpretacionRegresion generar(EstadoSesion estado, int decimales) {

        InterpretacionRegresion interpretacion = new InterpretacionRegresion();

        // =========================
        // Datos de la sesión
        // =========================

        RegresionLineal regresion = estado.getRegresion();

        double[] x = estado.getX();
        double[] y = estado.getY();

        String nombreX = estado.getNombreX();
        String nombreY = estado.getNombreY();

        // =========================
        // Variables auxiliares
        // =========================

        
        double r2 = regresion.getR2() * 100;
        
        String r2Pct = String.format("%." + decimales + "f", r2);

        double seRel = (regresion.getSe() / Math.abs(regresion.getPromedioY())) * 100;

        String seRelPct = String.format("%." + decimales + "f", seRel);
        
        double rAbs = Math.abs(regresion.getR());

        // =========================
        // Semáforo
        // =========================

        SemaforoInterpretacion semaforo;

        if (regresion.getR2() >= 0.80) {

            semaforo = SemaforoInterpretacion.VERDE;

        } else if (regresion.getR2() >= 0.50) {

            semaforo = SemaforoInterpretacion.AMARILLO;

        } else {

            semaforo = SemaforoInterpretacion.ROJO;

        }

        interpretacion.setSemaforo(semaforo);

        // =========================
        // Porcentajes
        // =========================

        interpretacion.setR2Pct(r2Pct);
        interpretacion.setSeRelPct(seRelPct);

        return interpretacion;

    }

}