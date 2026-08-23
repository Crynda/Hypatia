package gui.util.export;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import estadistica.interpretacion.InterpretacionRegresion;

public final class PDFExport {

    private PDFExport() {

    }

    public static void exportar(File archivo, InterpretacionRegresion interpretacion, BufferedImage grafica) throws IOException {

        ConstructorPDF constructor = new ConstructorPDF(interpretacion, grafica);
        constructor.construir(archivo);
    }

}