package gui.util.export;

import estadistica.regresion.RegresionLineal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import estadistica.util.Formateador;

public class HTMLExport {

    public static String exportarHTML(
            double[] x,
            double[] y,
            RegresionLineal resultado,
            int decimales
    ) {

        String fmt = "%." + decimales + "f";

        String fecha = LocalDateTime.now()
                .format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"));

        String chartSVG = SVGExport.generarSVG(x, y, resultado);

        StringBuilder html = new StringBuilder();

        html.append("<!DOCTYPE html>")
            .append("<html lang='es'>")
            .append("<head>")
            .append("<meta charset='UTF-8'/>")
            .append("<meta name='viewport' content='width=device-width, initial-scale=1.0'/>")
            .append("<title>Reporte — Regresión Lineal — Hypatia</title>");

        // ================= CSS =================
        html.append("<style>")
        .append("* { box-sizing: border-box; margin: 0; padding: 0; }")

        .append("body {")
        .append("font-family: 'Segoe UI', system-ui, sans-serif;")
        .append("background: #0f1117;")
        .append("color: #e0e0e0;")
        .append("padding: 40px 20px;")
        .append("min-height: 100vh;")
        .append("}")

        .append(".container { max-width: 860px; margin: 0 auto; }")

        // HEADER
        .append("header {")
        .append("display: flex;")
        .append("align-items: flex-end;")
        .append("justify-content: space-between;")
        .append("border-bottom: 1px solid #2d2d3a;")
        .append("padding-bottom: 18px;")
        .append("margin-bottom: 32px;")
        .append("}")

        .append("header h1 {")
        .append("font-size: 2rem;")
        .append("font-weight: 800;")
        .append("letter-spacing: .25em;")
        .append("color: #a699d1;")
        .append("}")

        .append("header p { font-size: .75rem; color: #555; }")

        // SECCIONES
        .append(".section {")
        .append("background: #1a1a2e;")
        .append("border: 1px solid #2d2d3a;")
        .append("border-radius: 10px;")
        .append("padding: 24px;")
        .append("margin-bottom: 24px;")
        .append("}")

        .append(".section h2 {")
        .append("font-size: .7rem;")
        .append("text-transform: uppercase;")
        .append("letter-spacing: .15em;")
        .append("color: #a699d1;")
        .append("margin-bottom: 16px;")
        .append("font-weight: 600;")
        .append("}")

        // ECUACIÓN
        .append(".equation {")
        .append("font-family: 'Courier New', monospace;")
        .append("font-size: 1.3rem;")
        .append("color: #6fa8ff;")
        .append("text-align: center;")
        .append("padding: 16px;")
        .append("background: #12121f;")
        .append("border-radius: 8px;")
        .append("margin-bottom: 8px;")
        .append("}")

        // GRID RESPONSIVE
        .append(".grid-2 {")
        .append("display: grid;")
        .append("grid-template-columns: 1fr 1fr;")
        .append("gap: 24px;")
        .append("}")

        .append("@media (max-width: 600px) {")
        .append(".grid-2 { grid-template-columns: 1fr; }")
        .append("}")

        // TABLAS
        .append("table {")
        .append("width: 100%;")
        .append("border-collapse: collapse;")
        .append("font-size: .85rem;")
        .append("}")

        .append("th {")
        .append("background: #12121f;")
        .append("color: #888;")
        .append("font-weight: 600;")
        .append("font-size: .7rem;")
        .append("text-transform: uppercase;")
        .append("letter-spacing: .08em;")
        .append("padding: 8px 12px;")
        .append("text-align: left;")
        .append("}")

        .append("td {")
        .append("padding: 7px 12px;")
        .append("border-bottom: 1px solid #1e1e30;")
        .append("color: #ccc;")
        .append("}")

        .append("tr:last-child td { border-bottom: none; }")

        // HOVER
        .append("tr:hover td {")
        .append("background: #252542;")
        .append("transition: background 0.15s ease;")
        .append("}")

        .append(".val {")
        .append("font-family: 'Courier New', monospace;")
        .append("color: #a699d1;")
        .append("text-align: right;")
        .append("}")

        // CHART
        .append(".chart-wrap {")
        .append("background: #12121f;")
        .append("border-radius: 8px;")
        .append("padding: 16px;")
        .append("overflow: hidden;")
        .append("}")

        // SVG TEXT FIX 
        .append("svg text {")
        .append("font-family: 'Segoe UI', system-ui, sans-serif;")
        .append("}")
        
        // SVG
        .append("svg {")
        .append("width: 100%;")
        .append("max-height: 340px;")
        .append("display: block;")
        .append("}")

        // FOOTER
        .append("footer {")
        .append("text-align: center;")
        .append("font-size: .7rem;")
        .append("color: #333;")
        .append("margin-top: 40px;")
        .append("padding-top: 16px;")
        .append("border-top: 1px solid #1e1e30;")
        .append("}")
        
        .append(".center-text {")
        .append("text-align: center;")
        .append("font-size: .78rem;")
        .append("color: #555;")
        .append("margin-top: 8px;")
        .append("}")

        .append("</style>");
        html.append("</head><body><div class='container'>");

        // ================= HEADER =================
        html.append("<header>")
            .append("<div><h1>HYPATIA</h1>")
            .append("<p>REGRESIÓN LINEAL</p></div>")
            .append("<p>Generado: ").append(fecha).append("</p>")
            .append("</header>");

        // ================= ECUACIÓN =================
        html.append("<div class='section'>")
        	.append("<h2>Ecuación de regresión</h2>")
        	.append("<div class='equation'>ŷ = ")
        	.append(String.format(fmt, resultado.getIntercepto()))
        	.append(" + ")
        	.append(String.format(fmt, resultado.getPendiente()))
        	.append("x</div>")
        	.append("<p class='center-text'>Coeficiente de correlación r = ")
        	.append(String.format(fmt, resultado.getR() * 100))
        	.append("% |  Determinación r² = ")
        	.append(String.format(fmt, resultado.getR2() * 100))
        	.append("%</p>")
        	.append("</div>");

        // ================= SVG =================
        html.append("<div class='section'>")
            .append("<h2>Diagrama de dispersión y recta de regresión</h2>")
            .append("<div class='chart-wrap'>")
            .append(chartSVG)
            .append("</div>")
            .append("</div>");

        // ================= DATOS =================
        html.append("<div class='grid-2'>");

        html.append("<div class='section'><h2>Datos (X, Y)</h2><table><thead><tr><th>#</th><th>X</th><th>Y</th></tr></thead><tbody>");

        for (int i = 0; i < x.length; i++) {
            html.append("<tr><td>")
                .append(i + 1)
                .append("</td><td class='val'>")
                .append(Formateador.formatear(x[i], 2))
                .append("</td><td class='val'>")
                .append(Formateador.formatear(y[i], 2))
                .append("</td></tr>");
        }

        html.append("</tbody></table></div>");

        // ================= SUMATORIAS =================
        html.append("<div class='section'><h2>Sumatorias</h2><table>")
        	.append("<thead><tr><th>Símbolo</th><th>Valor</th></tr></thead><tbody>")
        	.append("<tr><td>Σx</td><td class='val'>")
        	.append(Formateador.formatear(resultado.getSumaX(), 2))
        	.append("</td></tr>")
        	.append("<tr><td>Σy</td><td class='val'>")
        	.append(Formateador.formatear(resultado.getSumaY(), 2))
        	.append("</td></tr>")
        	.append("<tr><td>Σxy</td><td class='val'>")
        	.append(Formateador.formatear(resultado.getSumaXY(), 2))
        	.append("</td></tr>")
        	.append("<tr><td>Σx²</td><td class='val'>")
        	.append(Formateador.formatear(resultado.getSumaX2(), 2))
        	.append("</td></tr>")
        	.append("<tr><td>Σy²</td><td class='val'>")
        	.append(Formateador.formatear(resultado.getSumaY2(), 2))
        	.append("</td></tr>")
        	.append("</table></div>");

        html.append("</div>");
        
        // ================= CALCULOS =================
        html.append("<div class='section'>")
        	.append("<h2>Cálculos</h2>")
        	.append("<table>")
        	.append("<thead><tr><th>Parámetro</th><th>Descripción</th><th>Valor</th></tr></thead>")
        	.append("<tbody>")

        	.append("<tr><td>n</td><td>Número de muestras</td><td class='val'>")
        	.append(resultado.getN())
        	.append("</td></tr>")

        	.append("<tr><td>x̄</td><td>Promedio de X</td><td class='val'>")
        	.append(Formateador.formatear(resultado.getPromedioX(), 2))
        	.append("</td></tr>")

        	.append("<tr><td>ȳ</td><td>Promedio de Y</td><td class='val'>")
        	.append(Formateador.formatear(resultado.getPromedioY(), 2))
        	.append("</td></tr>")

        	.append("<tr><td>b</td><td>Pendiente</td><td class='val'>")
        	.append(Formateador.formatear(resultado.getPendiente(), 2))
        	.append("</td></tr>")

        	.append("<tr><td>a</td><td>Intercepto (ordenada al origen)</td><td class='val'>")
        	.append(Formateador.formatear(resultado.getIntercepto(), 2))
        	.append("</td></tr>")

        	.append("<tr><td>Se</td><td>Error estándar de estimación</td><td class='val'>")
        	.append(Formateador.formatear(resultado.getSe(), 2))
        	.append("</td></tr>")

        	.append("<tr><td>r²</td><td>Coeficiente de determinación</td><td class='val'>")
        	.append(Formateador.formatear(resultado.getR2() * 100, 2))
        	.append("%</td></tr>")

        	.append("<tr><td>r</td><td>Coeficiente de correlación</td><td class='val'>")
        	.append(Formateador.formatear(resultado.getR() * 100, 2))
        	.append("%</td></tr>")

        	.append("</tbody>")
        	.append("</table>")
        	.append("</div>");

        // ================= FOOTER =================
        html.append("<footer style='text-align:center;margin-top:40px;color:#333;font-size:.7rem;'>")
            .append("Hypatia v 1.0.0 — Herramienta de Estadística")
            .append("</footer>");

        html.append("</div></body></html>");

        return html.toString();
        
        
    }
}