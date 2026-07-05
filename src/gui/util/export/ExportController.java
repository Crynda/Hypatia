package gui.util.export;

import java.io.File;

import estadistica.regresion.RegresionLineal;
import gui.status.EstadoSesion;
import gui.status.GestorSesion;
import javafx.stage.FileChooser;
import javafx.stage.Window;

public class ExportController {

    public static void exportarPDF(
            double[] x,
            double[] y,
            RegresionLineal regresion,
            int decimales) {

        PDFExport.exportar(x, y, regresion, decimales);
    }

    public static void exportarJSON(Window owner, int decimales) {

        EstadoSesion estado = GestorSesion.getEstado();
        FileChooser chooser = ExportUtil.crearFileChooser("Guardar JSON", "hypatia-regresion", "JSON (*.json)", "json");
        File archivo = chooser.showSaveDialog(owner);

        if (archivo == null) {
            return;
        }

        archivo = ExportUtil.asegurarExtension(archivo, "json");

        if (!ExportUtil.confirmarSobrescritura(archivo)) {
            return;
        }

        String json = JSONExport.construirJSON(estado.getX(), estado.getY(), estado.getRegresion(), decimales);

        ExportUtil.exportar(archivo, json);
    }
    
    public static void exportarHTML(Window owner, int decimales) {

        EstadoSesion estado = GestorSesion.getEstado();
        FileChooser chooser = ExportUtil.crearFileChooser("Guardar HTML", "hypatia-regresion", "HTML (*.html)", "html");
        File archivo = chooser.showSaveDialog(owner);

        if (archivo == null) {
            return;
        }

        archivo = ExportUtil.asegurarExtension(archivo, "html");

        if (!ExportUtil.confirmarSobrescritura(archivo)) {
            return;
        }

        String html = HTMLExport.construirHTML(estado.getX(), estado.getY(), estado.getRegresion(), decimales);

        ExportUtil.exportar(archivo, html);
    }

    public static void exportarCSV(
            double[] x,
            double[] y,
            RegresionLineal regresion,
            int decimales) {

        CSVExport.exportar(x, y, regresion, decimales);
    }

    public static void exportarExcel(
            double[] x,
            double[] y,
            RegresionLineal regresion,
            int decimales) {

        ExcelExport.exportar(x, y, regresion, decimales);
    }
    
    

}