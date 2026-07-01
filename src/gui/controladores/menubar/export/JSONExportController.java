package gui.controladores.menubar.export;

import estadistica.regresion.RegresionLineal;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class JSONExportController {

    public static void exportar(
            double[] x,
            double[] y,
            RegresionLineal r,
            int decimales
    ) {

        try {

            String json = construirJSON(x, y, r, decimales);

            String nombre = "hypatia-regresion-" + timestamp() + ".json";

            FileWriter writer = new FileWriter(nombre);
            writer.write(json);
            writer.close();

            System.out.println("JSON exportado correctamente: " + nombre);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // =========================
    // CONSTRUCCIÓN DEL JSON
    // =========================

    private static String construirJSON(
            double[] x,
            double[] y,
            RegresionLineal r,
            int decimales
    ) {

        StringBuilder sb = new StringBuilder();

        sb.append("{\n");

        sb.append("  \"generado\": \"")
          .append(LocalDateTime.now())
          .append("\",\n");

        sb.append("  \"version\": \"1.0.0\",\n");

        // ================= DATOS =================
        sb.append("  \"datos\": {\n");

        sb.append("    \"x\": ").append(arrayToJson(x)).append(",\n");
        sb.append("    \"y\": ").append(arrayToJson(y)).append(",\n");

        sb.append("    \"pares\": [");

        for (int i = 0; i < x.length; i++) {
            sb.append("{\"x\":").append(x[i])
              .append(",\"y\":").append(y[i]).append("}");
            if (i < x.length - 1) sb.append(",");
        }

        sb.append("]\n  },\n");

        // ================= CÁLCULOS =================
        sb.append("  \"calculos\": {\n");
        sb.append("    \"n\": ").append(r.getN()).append(",\n");
        sb.append("    \"promedioX\": ").append(r.getPromedioX()).append(",\n");
        sb.append("    \"promedioY\": ").append(r.getPromedioY()).append(",\n");
        sb.append("    \"pendiente_b\": ").append(r.getPendiente()).append(",\n");
        sb.append("    \"intercepto_a\": ").append(r.getIntercepto()).append(",\n");
        sb.append("    \"errorEstandar_Se\": ").append(r.getSe()).append(",\n");
        sb.append("    \"correlacion_r\": ").append(r.getR()).append(",\n");
        sb.append("    \"determinacion_r2\": ").append(r.getR2()).append("\n");
        sb.append("  },\n");

        // ================= SUMATORIAS =================
        sb.append("  \"sumatorias\": {\n");
        sb.append("    \"sumaX\": ").append(r.getSumaX()).append(",\n");
        sb.append("    \"sumaY\": ").append(r.getSumaY()).append(",\n");
        sb.append("    \"sumaXY\": ").append(r.getSumaXY()).append(",\n");
        sb.append("    \"sumaX2\": ").append(r.getSumaX2()).append(",\n");
        sb.append("    \"sumaY2\": ").append(r.getSumaY2()).append("\n");
        sb.append("  },\n");

        // ================= ECUACIÓN =================
        sb.append("  \"ecuacion\": \"y = ")
          .append(r.getIntercepto())
          .append(" + ")
          .append(r.getPendiente())
          .append("x\"\n");

        sb.append("}\n");

        return sb.toString();
    }

    // =========================
    // UTILIDADES
    // =========================

    private static String arrayToJson(double[] arr) {
        StringBuilder sb = new StringBuilder();
        sb.append("[");

        for (int i = 0; i < arr.length; i++) {
            sb.append(arr[i]);
            if (i < arr.length - 1) sb.append(",");
        }

        sb.append("]");
        return sb.toString();
    }

    private static String timestamp() {
        return DateTimeFormatter
                .ofPattern("yyyy-MM-dd_HH-mm-ss")
                .format(LocalDateTime.now());
    }
}