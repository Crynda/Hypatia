package gui.controladores;

import gui.config.GestorConfiguracion;
import gui.util.SwitchToggle;
import gui.util.export.ConfiguracionExportacion;
import gui.util.export.ExportController;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

public class ExportMenuController {

    @FXML
    private AnchorPane root;

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
    	
    	GestorConfiguracion.guardarExportacion(config); 
    	ExportController.exportarPDF(ventana, Grafica, limiteDecimales); //Modificar para pasar los argumentos desde la ventana anterior, y modificar
    	// exportar pdf para que no abra las ventanas y mejor tome todos los datos desde la clase del menu de exportaciones y los datos
    	Stage stage = (Stage) root.getScene().getWindow(); 
    	stage.close(); 
    	
    	}
    
}

