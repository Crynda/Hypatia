package gui.controladores;

import gui.config.GestorConfiguracion;
import gui.util.SwitchToggle;
import gui.util.export.ConfiguracionExportacion;
import gui.util.export.ExportController;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.stage.Window;

public class ExportMenuController {

    @FXML
    private AnchorPane root;
    
    @FXML
    private TextField titulo;

    @FXML
    private Pane switchPaneFecha;

    @FXML
    private Circle switchFecha;

    @FXML
    private Pane switchPaneIC;

    @FXML
    private Circle switchIC;

    @FXML
    private Pane switchPaneCreditos;

    @FXML
    private Circle switchCreditos;


    private ConfiguracionExportacion config;
    
    private Window ventana;
    private Node grafica;
    private int limiteDecimales;
    
    public void configurarExportacion(Window ventana, Node grafica, int limiteDecimales) {

        this.ventana = ventana;
        this.grafica = grafica;
        this.limiteDecimales = limiteDecimales;
    }


    // =========================
    // INIT
    // =========================

    @FXML
    public void initialize() {

        config = GestorConfiguracion.cargarExportacion();


        // =========================
        // SWITCH FECHA
        // =========================

        SwitchToggle.configurarSwitch(
                switchPaneFecha,
                switchFecha,
                config.isFecha(),
                true,
                estado -> {

                    config.setFecha(estado);

                    GestorConfiguracion.guardarExportacion(config);
                });


        // =========================
        // SWITCH INFORMACIÓN COMPLEMENTARIA
        // =========================

        SwitchToggle.configurarSwitch(
                switchPaneIC,
                switchIC,
                config.isInformacionComplementaria(),
                true,
                estado -> {

                    config.setInformacionComplementaria(estado);

                    GestorConfiguracion.guardarExportacion(config);
                });


        // =========================
        // SWITCH CRÉDITOS
        // =========================

        SwitchToggle.configurarSwitch(
                switchPaneCreditos,
                switchCreditos,
                config.isCreditos(),
                true,
                estado -> {

                    config.setCreditos(estado);

                    GestorConfiguracion.guardarExportacion(config);
                });
    }


    // =========================
    // CERRAR
    // =========================

    @FXML
    private void cerrar() {
        Stage stage = (Stage) root.getScene().getWindow();
        stage.close();
    }
    

    @FXML
    private void aceptar() {

        // Guardar configuración actual
        GestorConfiguracion.guardarExportacion(config);

        //Obtener nombre
        String nombreReporte = titulo.getText().trim();

        if (nombreReporte.isEmpty()) {

            Alert alerta = new Alert(Alert.AlertType.WARNING);
            alerta.setTitle("Título requerido");
            alerta.setHeaderText("Falta el título del reporte");
            alerta.setContentText("Introduce un título para poder exportar el PDF.");

            alerta.showAndWait();

            return;
        }
        
        // Continuar con la exportación
        ExportController.exportarPDF(ventana, grafica, limiteDecimales, nombreReporte, config);

        Stage stage = (Stage) root.getScene().getWindow();
        stage.close();
    }

    
}

