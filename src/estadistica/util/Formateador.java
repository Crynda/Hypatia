package estadistica.util;

import java.math.BigDecimal;

public class Formateador {

    public static String formatear(double valor, int decimales) {

        String texto = Double.toString(valor);

        if (texto.contains("E") || texto.contains("e")) {
            BigDecimal bd = new BigDecimal(texto);
            texto = bd.toPlainString();
        }

        int punto = texto.indexOf('.');

        if (punto == -1) {
            return texto;
        }

        int fin = punto + decimales + 1;

        if (fin < texto.length()) {
            texto = texto.substring(0, fin);
        }

        while (texto.endsWith("0")) {
            texto = texto.substring(0, texto.length() - 1);
        }

        if (texto.endsWith(".")) {
            texto = texto.substring(0, texto.length() - 1);
        }

        return texto;
    }
}