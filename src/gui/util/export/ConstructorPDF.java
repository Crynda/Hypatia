package gui.util.export;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.openpdf.text.Document;
import org.openpdf.text.Paragraph;
import org.openpdf.text.pdf.PdfWriter;

import estadistica.interpretacion.InterpretacionRegresion;

public final class ConstructorPDF {

    private final InterpretacionRegresion interpretacion;
    private final BufferedImage grafica;

    public ConstructorPDF(InterpretacionRegresion interpretacion, BufferedImage grafica) {

        this.interpretacion = interpretacion;
        this.grafica = grafica;
    }

    public void construir(File archivo) throws IOException {

        Document documento = new Document();

        try {

            PdfWriter.getInstance(documento, new FileOutputStream(archivo));
            documento.open();
            documento.add(new Paragraph("HYPATIA"));
            documento.add(new Paragraph("Informe de regresión lineal"));
            documento.add(new Paragraph("Este documento contiene los resultados " + "del análisis de regresión."));
            
        } finally {

            documento.close();

        }
    }

    public InterpretacionRegresion getInterpretacion() {
        return interpretacion;
    }

    public BufferedImage getGrafica() {
        return grafica;
    }
}