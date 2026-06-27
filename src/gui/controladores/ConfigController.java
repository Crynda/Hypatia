package gui.controladores;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;

import gui.config.Configuracion;
import gui.config.GestorConfiguracion;
import gui.config.GestorTemas;
import gui.util.SwitchToggle;

public class ConfigController {

    @FXML
    private Pane switchPane;

    @FXML
    private Pane switchPaneTT;

    @FXML
    private Circle switchCircle;

    @FXML
    private Circle switchTooltips;

    @FXML
    private Spinner<Integer> limit;

    @FXML
    private Button Aplicar;

    private Configuracion config;

    private TableController tableController;

    // =========================
    // INIT
    // =========================

    @FXML
    public void initialize() {

        config = GestorConfiguracion.cargar();

        SpinnerValueFactory<Integer> valueFactory =
                new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 6, 0);

        limit.setValueFactory(valueFactory);
        limit.setEditable(false);

        // sincronización explícita con config
        limit.getValueFactory().setValue(config.getDecimales());

        // =========================
        // SWITCH TEMA
        // =========================

        SwitchToggle.configurarSwitch(
                switchPane,
                switchCircle,
                "oscuro".equalsIgnoreCase(config.getTema()),
                false,
                estado -> {

                    config.setTema(estado ? "oscuro" : "claro");

                    GestorConfiguracion.guardar(config);
                    GestorTemas.aplicarDesdeConfig(config);
                });

        // =========================
        // SWITCH TOOLTIPS
        // =========================

        SwitchToggle.configurarSwitch(
                switchPaneTT,
                switchTooltips,
                config.isTooltips(),
                true,
                estado -> {

                    config.setTooltips(estado);
                    GestorConfiguracion.guardar(config);
                });
    }

    // =========================
    // DECIMALES
    // =========================

    @FXML
    private void aplicarLimite() {

        int valor = limit.getValue();

        config.setDecimales(valor);

        GestorConfiguracion.guardar(config);

        if (tableController != null) {
            tableController.setLimiteDecimales(valor);
        }

        System.out.println("Decimales: " + valor);
    }

    // =========================
    // INYECCIÓN
    // =========================

    public void setTableController(TableController tableController) {
        this.tableController = tableController;
    }
}