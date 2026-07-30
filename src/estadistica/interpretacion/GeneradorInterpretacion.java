package estadistica.interpretacion;

import estadistica.regresion.RegresionLineal;
import gui.status.EstadoSesion;

public final class GeneradorInterpretacion {

    private GeneradorInterpretacion() {

    }

    public static InterpretacionRegresion generar(EstadoSesion estado, int decimales) {

        InterpretacionRegresion interpretacion = new InterpretacionRegresion();

        RegresionLineal regresion = estado.getRegresion();

        String nombreX = estado.getNombreX();
        String nombreY = estado.getNombreY();

        String r2Pct = String.format("%." + decimales + "f", regresion.getR2() * 100);

        double seRel = (regresion.getSe() / Math.abs(regresion.getPromedioY())) * 100;

        String seRelPct = String.format("%." + decimales + "f", seRel);

        double rAbs = regresion.getR();

        generarSemaforo(interpretacion, regresion);

        interpretacion.setR2Pct(r2Pct);
        interpretacion.setSeRelPct(seRelPct);

        generarCalidad(interpretacion, regresion);

        generarRelacion(interpretacion, regresion, rAbs);

        generarPreguntas(interpretacion, regresion, nombreX, nombreY, r2Pct, seRel, seRelPct, decimales);

        generarEjemplos(
                interpretacion,
                regresion,
                nombreX,
                nombreY,
                decimales);

        generarConclusion(
                interpretacion,
                regresion,
                nombreX,
                nombreY);

        return interpretacion;

    }

    // ============================================================
    // Semáforo
    // ============================================================

    private static void generarSemaforo(
            InterpretacionRegresion interpretacion,
            RegresionLineal regresion) {

        if (regresion.getR2() >= 0.80) {

            interpretacion.setSemaforo(SemaforoInterpretacion.VERDE);

        } else if (regresion.getR2() >= 0.50) {

            interpretacion.setSemaforo(SemaforoInterpretacion.AMARILLO);

        } else {

            interpretacion.setSemaforo(SemaforoInterpretacion.ROJO);

        }

    }

    // ============================================================
    // Calidad
    // ============================================================

    private static void generarCalidad(
            InterpretacionRegresion interpretacion,
            RegresionLineal regresion) {

        String calidad;

        if (regresion.getR2() >= 0.95) {

            calidad = "Excelente";

        } else if (regresion.getR2() >= 0.80) {

            calidad = "Muy bueno";

        } else if (regresion.getR2() >= 0.65) {

            calidad = "Bueno";

        } else if (regresion.getR2() >= 0.50) {

            calidad = "Regular";

        } else {

            calidad = "Débil";

        }

        interpretacion.setCalidad(calidad);

    }

    // ============================================================
    // Relación
    // ============================================================

    private static void generarRelacion(
            InterpretacionRegresion interpretacion,
            RegresionLineal regresion,
            double rAbs) {

        String fuerza;

        if (rAbs >= 0.90) {

            fuerza = "muy fuerte";

        } else if (rAbs >= 0.75) {

            fuerza = "fuerte";

        } else if (rAbs >= 0.55) {

            fuerza = "moderada";

        } else {

            fuerza = "débil";

        }

        interpretacion.setFuerza(fuerza);

        interpretacion.setDireccion(
                regresion.getPendiente() >= 0
                        ? "positiva"
                        : "negativa");

    }

    // ============================================================
    // Preguntas
    // ============================================================

    private static void generarPreguntas(
            InterpretacionRegresion interpretacion,
            RegresionLineal regresion,
            String nombreX,
            String nombreY,
            String r2Pct,
            double seRel,
            String seRelPct,
            int decimales) {

        double rAbs = regresion.getR();

        // -----------------------
        // Pregunta 1
        // -----------------------

        String respuesta1;

        if (rAbs >= 0.85) {

            respuesta1 = "Sí, claramente.";

        } else if (rAbs >= 0.65) {

            respuesta1 = "Sí, bastante.";

        } else if (rAbs >= 0.45) {

            respuesta1 = "Más o menos.";

        } else {

            respuesta1 = "Poco o nada.";

        }

        String detalle1;

        if (regresion.getPendiente() >= 0) {

            detalle1 = "Cuando "
                    + nombreX
                    + " crece, "
                    + nombreY
                    + " también crece. La relación es "
                    + interpretacion.getFuerza()
                    + ".";

        } else {

            detalle1 = "Cuando "
                    + nombreX
                    + " crece, "
                    + nombreY
                    + " baja. La relación es "
                    + interpretacion.getFuerza()
                    + ".";

        }

        interpretacion.getPregunta1().setRespuesta(respuesta1);
        interpretacion.getPregunta1().setDetalle(detalle1);

        // -----------------------
        // Pregunta 2
        // -----------------------

        String detalle2;

        if (regresion.getR2() >= 0.80) {

            detalle2 =
                    "De cada 10 predicciones, aproximadamente "
                            + Math.round(regresion.getR2() * 10)
                            + " serán muy cercanas al valor real.";

        } else if (regresion.getR2() >= 0.50) {

            detalle2 =
                    "Predice la tendencia general, aunque existe un margen de error considerable.";

        } else {

            detalle2 =
                    "Las predicciones son únicamente orientativas; existen muchos factores que el modelo no explica.";

        }

        interpretacion.getPregunta2().setRespuesta(
                "El modelo explica el "
                        + r2Pct
                        + "% de la variabilidad de los datos.");

        interpretacion.getPregunta2().setDetalle(detalle2);

        // -----------------------
        // Pregunta 3
        // -----------------------

        String respuesta3;

        if (seRel < 5) {

            respuesta3 = "Muy confiable.";

        } else if (seRel < 15) {

            respuesta3 = "Confiable.";

        } else if (seRel < 30) {

            respuesta3 = "Aceptable con cautela.";

        } else {

            respuesta3 = "Usarlo solo como referencia.";

        }

        interpretacion.getPregunta3().setRespuesta(respuesta3);

        interpretacion.getPregunta3().setDetalle(
                "El margen de error típico es ±"
                        + String.format("%." + decimales + "f",
                                regresion.getSe())
                        + " unidades ("
                        + seRelPct
                        + "% del promedio de "
                        + nombreY
                        + ").");

    }

    // ============================================================
    // Ejemplos
    // ============================================================

    private static void generarEjemplos(
            InterpretacionRegresion interpretacion,
            RegresionLineal regresion,
            String nombreX,
            String nombreY,
            int decimales) {

        interpretacion.setEjemploX(nombreX);
        interpretacion.setEjemploY(nombreY);

        interpretacion.setPendienteDir(
                regresion.getPendiente() >= 0
                        ? "aumenta"
                        : "disminuye");

        interpretacion.setPendienteVal(
                String.format("%." + decimales + "f",
                        Math.abs(regresion.getPendiente())));

    }

    // ============================================================
    // Conclusión
    // ============================================================

    private static void generarConclusion(
            InterpretacionRegresion interpretacion,
            RegresionLineal regresion,
            String nombreX,
            String nombreY) {

        String conclusion;

        if (regresion.getR2() >= 0.80) {

            conclusion =
                    "Los datos presentan una relación "
                            + interpretacion.getFuerza()
                            + " y el modelo es confiable para realizar estimaciones.";

        } else if (regresion.getR2() >= 0.50) {

            conclusion =
                    "Existe una relación "
                            + interpretacion.getFuerza()
                            + " entre "
                            + nombreX
                            + " y "
                            + nombreY
                            + ", aunque conviene complementarla con más información.";

        } else {

            conclusion =
                    "La relación observada es "
                            + interpretacion.getFuerza()
                            + ". Este modelo únicamente proporciona una idea general del comportamiento de los datos.";

        }

        interpretacion.setConclusion(conclusion);

    }

}