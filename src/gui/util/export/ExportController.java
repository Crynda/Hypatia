package gui.util.export;

import java.io.File;

import estadistica.regresion.RegresionLineal;
import gui.status.EstadoSesion;
import gui.status.GestorSesion;
import javafx.scene.Node;
import javafx.scene.chart.LineChart;
import javafx.stage.FileChooser;
import javafx.stage.Window;

public class ExportController {

    

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

        String json = JSONExport.construirJSON(estado.getRegresion(), decimales);

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
    
    public static void exportarImagen(Window owner, Node grafica) {

        FileChooser chooser = ExportUtil.crearFileChooser("Guardar imagen", "hypatia-grafica", "PNG (*.png)", "png");

        File archivo = chooser.showSaveDialog(owner);

        if (archivo == null) {
            return;
        }

        archivo = ExportUtil.asegurarExtension(archivo, "png");

        if (!ExportUtil.confirmarSobrescritura(archivo)) {
            return;
        }

        ImageExport.exportar(grafica, archivo);
    }


    
    

}