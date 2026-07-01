package gui.controladores.menubar.export;

import estadistica.regresion.RegresionLineal;

public class ExportController {

    public static void exportarPDF(
            double[] x,
            double[] y,
            RegresionLineal regresion,
            int decimales) {

        PDFExportController.exportar(x, y, regresion, decimales);
    }

    public static void exportarJSON(
            double[] x,
            double[] y,
            RegresionLineal regresion,
            int decimales) {

        JSONExportController.exportar(x, y, regresion, decimales);
    }

    public static void exportarCSV(
            double[] x,
            double[] y,
            RegresionLineal regresion,
            int decimales) {

        CSVExportController.exportar(x, y, regresion, decimales);
    }

    public static void exportarExcel(
            double[] x,
            double[] y,
            RegresionLineal regresion,
            int decimales) {

        ExcelExportController.exportar(x, y, regresion, decimales);
    }
    
    public static void exportarHTML(
            double[] x,
            double[] y,
            RegresionLineal regresion,
            int decimales) {

    	HTMLExportController.exportarHTML(x, y, regresion, decimales);
    }

}