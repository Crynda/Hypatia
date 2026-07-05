package gui.util.export;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.FileChooser;

public final class ExportUtil {

    private ExportUtil() {
  
    }

    
    public static void exportar(File archivo, String contenido) {

        try (FileWriter writer = new FileWriter(archivo)) {

            writer.write(contenido);

            System.out.println("Archivo exportado correctamente: " + archivo.getAbsolutePath());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    
    // =========================
    // FILE CHOOSER
    // =========================

    public static FileChooser crearFileChooser(
            String titulo,
            String nombreBase,
            String descripcion,
            String extension) {

        FileChooser chooser = new FileChooser();

        chooser.setTitle(titulo);

        chooser.getExtensionFilters().add(
                new FileChooser.ExtensionFilter(
                        descripcion,
                        "*." + extension));

        chooser.setInitialFileName(
                nombrePorDefecto(nombreBase, extension));

        return chooser;
    }

    // =========================
    // NOMBRE POR DEFECTO
    // =========================

    public static String nombrePorDefecto(
            String nombreBase,
            String extension) {

        return nombreBase + "-" + timestamp() + "." + extension;
    }

    // =========================
    // TIMESTAMP
    // =========================

    private static String timestamp() {

        return DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss").format(LocalDateTime.now());
    }
    
    public static File asegurarExtension(
            File archivo,
            String extension) {

        String nombre = archivo.getName();

        if (!nombre.toLowerCase().endsWith("." + extension.toLowerCase())) {

            archivo = new File(
                    archivo.getParentFile(),
                    nombre + "." + extension);
        }

        return archivo;
    }
    
    public static boolean confirmarSobrescritura(File archivo) {

        if (!archivo.exists()) {
            return true;
        }

        Alert alerta = new Alert(Alert.AlertType.CONFIRMATION);

        alerta.setTitle("Sobrescribir archivo");
        alerta.setHeaderText("El archivo ya existe.");
        alerta.setContentText(
                "¿Desea reemplazar \"" +
                archivo.getName() +
                "\"?");

        Optional<ButtonType> resultado = alerta.showAndWait();

        return resultado.isPresent()
                && resultado.get() == ButtonType.OK;
    }

}