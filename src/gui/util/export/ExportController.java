package gui.util.export;

import estadistica.regresion.RegresionLineal;

public class ExportController {

    public static void exportarPDF(
            double[] x,
            double[] y,
            RegresionLineal regresion,
            int decimales) {

        PDFExport.exportar(x, y, regresion, decimales);
    }

    public static void exportarJSON(
            double[] x,
            double[] y,
            RegresionLineal regresion,
            int decimales) {

        JSONExport.exportar(x, y, regresion, decimales);
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
    
    public static void exportarHTML(
            double[] x,
            double[] y,
            RegresionLineal regresion,
            int decimales) {

    	HTMLExport.exportarHTML(x, y, regresion, decimales);
    }

}