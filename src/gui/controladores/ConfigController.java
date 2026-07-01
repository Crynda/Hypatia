package gui.controladores;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import gui.config.Configuracion;
import gui.config.GestorConfiguracion;
import gui.config.GestorTemas;
import gui.util.SwitchToggle;

import javafx.animation.FadeTransition;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.util.Duration;


public class ConfigController {

	@FXML
	private AnchorPane root;
	
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
    private Configuracion config;
    
    @FXML
    private TableController tableController;
    
 // Animaciones activas
    private FadeTransition fadeEntrada;
    private FadeTransition fadeSalida;
    private Timeline tiempoBarra;
    
    // Notificaciones
    @FXML
    private Pane pnlNotificacion;

    @FXML
    private Label lblMensaje;
    
    @FXML
    private Rectangle rectTiempo;

    @FXML
    public void mostrarNotificacion() {

        // Detener cualquier animación anterior
        if (fadeEntrada != null) fadeEntrada.stop();
        if (fadeSalida != null) fadeSalida.stop();
        if (tiempoBarra != null) tiempoBarra.stop();


        // Reiniciar estado
        lblMensaje.setTextFill(Color.web("#003EFF"));
        pnlNotificacion.setVisible(true);
        pnlNotificacion.setOpacity(0);

        rectTiempo.setWidth(600);

        // Fade de entrada
        fadeEntrada = new FadeTransition(Duration.millis(175), pnlNotificacion);
        fadeEntrada.setFromValue(0);
        fadeEntrada.setToValue(1);

        // Barra de tiempo (3 segundos)
        tiempoBarra = new Timeline(
            new KeyFrame(
                Duration.seconds(1.3),
                new KeyValue(rectTiempo.widthProperty(), 0)
            )
        );

        // Al terminar la barra, hacer fade de salida
        tiempoBarra.setOnFinished(e -> {

            fadeSalida = new FadeTransition(Duration.millis(175), pnlNotificacion);
            fadeSalida.setFromValue(1);
            fadeSalida.setToValue(0);

            fadeSalida.setOnFinished(ev -> {
                pnlNotificacion.setVisible(false);
            });

            fadeSalida.play();

        });

        fadeEntrada.play();
        tiempoBarra.play();
    }
    
    
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
    private void aplicarConfiguracion() {

        int valor = limit.getValue();

        config.setDecimales(valor);

        GestorConfiguracion.guardar(config);

        if (tableController != null) {
            tableController.setLimiteDecimales(valor);
        }
        
        mostrarNotificacion();

    }
    
    @FXML
    private void guardaryCerrar() {

        int valor = limit.getValue();

        config.setDecimales(valor);

        GestorConfiguracion.guardar(config);

        if (tableController != null) {
            tableController.setLimiteDecimales(valor);
        }
        
        Stage stage = (Stage) root.getScene().getWindow();
        stage.close();

    }

    // =========================
    // INYECCIÓN
    // =========================

    public void setTableController(TableController tableController) {
        this.tableController = tableController;
    }
}