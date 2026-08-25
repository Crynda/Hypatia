package gui.util.export;

import java.awt.image.BufferedImage;
import java.io.File;

import estadistica.interpretacion.GeneradorInterpretacion;
import estadistica.interpretacion.InterpretacionRegresion;
import gui.status.EstadoSesion;
import javafx.scene.Node;

public final class PDFExport {

    private PDFExport() {

    }

    public static void exportar(File archivo, EstadoSesion estado, Node grafica, int decimales) {

        // Interpretacion
        InterpretacionRegresion interpretacion = GeneradorInterpretacion.generar(estado, decimales);

        // Grafica
        BufferedImage imagen =ImageExport.generar(grafica);

        // Construccion del PDF
        ConstructorPDF constructor = new ConstructorPDF(interpretacion, imagen);
        constructor.construir(archivo);
    }

}