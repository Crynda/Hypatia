package gui.controladores;

import java.text.DecimalFormat;
import java.util.Optional;

import gui.actions.menubar.MenuActions;
import gui.componentes.CeldaEditableTab;
import estadistica.regresion.RegresionLineal;
import estadistica.util.Validaciones;
import gui.componentes.Dato;
import gui.config.GestorTemas;
import gui.status.EstadoSesion;
import gui.status.GestorSesion;
import gui.util.ConversorTabla;
import gui.util.export.ExportController;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Bounds;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Separator;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.TitledPane;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;

import estadistica.util.EstadisticaDescriptiva;



public class TableController {
	
	//Formatos
	private int limiteDecimales = 2; // default
	
	//Valores 
	private double[] x;
	private double[] y;
	
	
	// =========================
	// NODOS
	// =========================
	
	//Panes
	
	@FXML
	private TitledPane tpX;
	
	@FXML
	private TitledPane tpY;
	
	//Separadores
	
	@FXML
	private Separator separator1;
	
	@FXML
	private Separator separator2;
	
	//Grafica
	@FXML
	private LineChart<Number, Number> Grafica;	
	// =========================
	// TABLA X
	// =========================

	@FXML
	private TableView<Dato> tablaMuestrasX;

	@FXML
	private TableColumn<Dato, String> colMuestraX;

	@FXML
	private TableColumn<Dato, String> colValorX;

	// =========================
	// TABLA Y
	// =========================

	@FXML
	private TableView<Dato> tablaMuestrasY;

	@FXML
	private TableColumn<Dato, String> colMuestraY;

	@FXML
	private TableColumn<Dato, String> colValorY;
	
	// =========================
	// TABLA ESTIMACIÓN
	// =========================

	@FXML
	private TableView<Dato> Estimacion;

	@FXML
	private TableColumn<Dato, String> estimacionX;

	@FXML
	private TableColumn<Dato, String> estimacionY;
	
	//TXT
	
	@FXML
	private TextField txtN;
	
	@FXML
	private TextField txtpromX;
	
	@FXML
	private TextField txtpromY;
	
	@FXML
	private TextField txtB;
	
	@FXML
	private TextField txtA;
	
	@FXML
	private TextField txtSe;
	
	@FXML
	private TextField txtR2;
	
	@FXML
	private TextField txtR;
	
	//TXT sumatorias
	
	@FXML
	private TextField txtsumX;
	
	@FXML
	private TextField txtsumY;
	
	@FXML
	private TextField txtsumXY;
	
	@FXML
	private TextField txtsumX2;
	
	@FXML
	private TextField txtsumY2;
	
	@FXML
	private Button Config;
	
	@FXML
	private Button editY;
	
	@FXML
	private Button editX;
	
	
	
	// =========================
	// MENUBAR
	// =========================
	
	@FXML
	private MenuItem guardar;
	@FXML
	private MenuItem guardarComo;

	@FXML
	private MenuItem exportarPDF;

	@FXML
	private MenuItem exportarExcel;

	@FXML
	private MenuItem exportarHTML;

	@FXML
	private MenuItem exportarJSON;

	// =========================
	// REGRESION
	// =========================

	private RegresionLineal regresion;
	
	//Ventana
	private Window ventana;
	
	//Status
	EstadoSesion estado;


	
	@FXML
	private void validarMenuArchivo() {

		estado = GestorSesion.getEstado();

	    boolean habilitar = estado.getRegresionCalculada();

	    //De momento solo funciona con exportaciones, modificar despues para guardados
	    exportarPDF.setDisable(!habilitar);
	    exportarExcel.setDisable(!habilitar);
	    exportarHTML.setDisable(!habilitar);
	    exportarJSON.setDisable(!habilitar);
	    //Cambiar despues ubicacion
	    ventana = Config.getScene().getWindow();
	    
	}

	@FXML
	public void initialize() {
		
		ImageView imageView = new ImageView(new Image(getClass().getResourceAsStream("/recursos/Config.png")));
		imageView.setFitWidth(30);
		imageView.setFitHeight(30);

				
		Config.setGraphic(imageView);
		Config.setStyle("-fx-background-color: transparent;" + "-fx-border-color: transparent;" + "-fx-padding: 0;");
		
		imageView = new ImageView(new Image(getClass().getResourceAsStream("/recursos/Edit.png")));
		imageView.setFitWidth(18);
		imageView.setFitHeight(18);
		
		ImageView imageView2 = new ImageView(new Image(getClass().getResourceAsStream("/recursos/Edit.png")));
		imageView2.setFitWidth(18);
		imageView2.setFitHeight(18);
		
		editX.setGraphic(imageView);
		editX.setStyle("-fx-background-color: transparent;" + "-fx-border-color: transparent;" + "-fx-padding: 0;");
		
		editY.setGraphic(imageView2);
		editY.setStyle("-fx-background-color: transparent;" + "-fx-border-color: transparent;" + "-fx-padding: 0;");
		
		

		configurarTabla(tablaMuestrasX, colMuestraX, colValorX);

		configurarTabla(tablaMuestrasY, colMuestraY, colValorY);
		
		configurarTablaEstimacion();
		
		estado = GestorSesion.getEstado();
		
		tpX.boundsInParentProperty().addListener((obs, oldBounds, newBounds) -> {
		    actualizarBotones();
		});

		tpY.boundsInParentProperty().addListener((obs, oldBounds, newBounds) -> {
		    actualizarBotones();
		});
		
		
	}

	// =========================
	// CONFIGURACIÓN GENERAL
	// =========================

	private void configurarTabla(TableView<Dato> tabla, TableColumn<Dato, String> colMuestra,
			TableColumn<Dato, String> colValor) {

		ObservableList<Dato> datos = FXCollections.observableArrayList();

		// =========================
		// BINDINGS
		// =========================

		colMuestra.setCellValueFactory(data -> data.getValue().muestraProperty());

		colValor.setCellValueFactory(data -> data.getValue().valorProperty());

		// =========================
		// CONFIGURACIÓN TABLA
		// =========================

		tabla.setEditable(true);

		colMuestra.setSortable(false);
		colValor.setSortable(false);

		// =========================
		// CELDAS EDITABLES
		// =========================

		//colValor.setCellFactory(TextFieldTableCell.forTableColumn());
		colValor.setCellFactory(col -> new CeldaEditableTab<>());

		// =========================
		// EVENTO EDICIÓN
		// =========================

		colValor.setOnEditCommit(event -> {

			String nuevoValor = event.getNewValue();

			// validar número
			if (!nuevoValor.matches("\\d+(\\.\\d+)?")) {

				tabla.refresh();
				return;
			}

			Dato actual = event.getRowValue();

			actual.setValor(nuevoValor);

			// última fila
			if (actual == datos.get(datos.size() - 1)

					&& !nuevoValor.isEmpty()) {

				// enumerar
				actual.setMuestra(String.valueOf(datos.size()));

				// nueva fila vacía
				datos.add(new Dato("", ""));
			}
		});

		// =========================
		// ASIGNAR DATOS
		// =========================

		tabla.setItems(datos);

		// primera fila vacía
		datos.add(new Dato("", ""));
	}
	

	
	private void configurarTablaEstimacion() {

	    ObservableList<Dato> datos =
	            FXCollections.observableArrayList();

	    // =========================
	    // BINDINGS
	    // =========================

	    estimacionX.setCellValueFactory(data ->
	            data.getValue().muestraProperty());

	    estimacionY.setCellValueFactory(data ->
	            data.getValue().valorProperty());

	    // =========================
	    // CONFIG TABLA
	    // =========================

	    Estimacion.setEditable(true);

	    estimacionX.setSortable(false);
	    estimacionY.setSortable(false);

	    // =========================
	    // SOLO X EDITABLE
	    // =========================

	    estimacionX.setCellFactory(
	    		 col -> new CeldaEditableTab<>());
	    // =========================
	    // EVENTO EDICIÓN
	    // =========================

	    estimacionX.setOnEditCommit(event -> {

	        String nuevoValor = event.getNewValue();

	        // validar número
	        if (!nuevoValor.matches("\\d+(\\.\\d+)?")) {

	            Estimacion.refresh();
	            return;
	        }

	        // validar regresión
	        if (regresion == null) {

	            System.out.println("Primero calcula la regresión");

	            Estimacion.refresh();
	            return;
	        }

	        Dato actual = event.getRowValue();

	        // guardar X
	        actual.setMuestra(nuevoValor);

	        // calcular Y
	        double x = Double.parseDouble(nuevoValor);
	        double y = regresion.getIntercepto() + (regresion.getPendiente() * x);

	        // guardar Y
	        actual.setValor(String.valueOf(formatear(y)));
	        
	        

	        // última fila
	        if (actual == datos.get(datos.size() - 1) && !nuevoValor.isEmpty()) {
	            datos.add(new Dato("", ""));
	        }
	    });

	    // =========================
	    // ASIGNAR DATOS
	    // =========================

	    Estimacion.setItems(datos);

	    // primera fila vacía
	    datos.add(new Dato("", ""));
	}
	
	

	// =========================
	// CALCULAR REGRESIÓN
	// =========================

	@FXML
	private void calcularRegresion() {

		try {

			x = ConversorTabla.convertirTabla(tablaMuestrasX);
			y = ConversorTabla.convertirTabla(tablaMuestrasY);
			
			

			// validar tamaños
			if (!Validaciones.mismaLongitud(x, y)) {
				System.out.println("Las muestras no tienen el mismo tamaño"); //Agregar validacion, y si retorna falso, mostrar mensaje a pantalla
				return;
			}

			// crear regresión
			regresion = new RegresionLineal(x, y);
			
			//Establecer metadatos
			regresion.setNombres(estado.getNombreX(), estado.getNombreY());

			// calcular
			regresion.calcular();
			
			
			// Actualizacion de estatus
			
			estado.setX(x);
			estado.setY(y);
			estado.setRegresion(regresion);

			estado.setDatosCargados(true);
			estado.setRegresionCalculada(true);
			 
			
			dibujarGrafica(x, y);
			
			obtenerDatos();

		} catch (Exception e) {

			System.out.println("Error al calcular " + "la regresión");

			e.printStackTrace();
		}
	}
	
	@FXML 
	private void obtenerDatos() {

	    txtN.setText(String.valueOf(regresion.getN()));

	    
	    txtpromX.setText(formatear(regresion.getPromedioX()));	
	    txtpromY.setText(formatear(regresion.getPromedioY()));

	    txtB.setText(formatear(regresion.getPendiente()));
	    txtA.setText(formatear(regresion.getIntercepto()));
	    txtSe.setText(formatear(regresion.getSe()));

	    txtR2.setText(formatear(regresion.getR2() * 100) + "%");
	    txtR.setText(formatear(regresion.getR() * 100) + "%");

	    txtsumX.setText(formatear(regresion.getSumaX()));
	    txtsumY.setText(formatear(regresion.getSumaY()));
	    txtsumXY.setText(formatear(regresion.getSumaXY()));
	    txtsumX2.setText(formatear(regresion.getSumaX2()));
	    txtsumY2.setText(formatear(regresion.getSumaY2()));
	    
	 // =========================
	 // ACTUALIZAR TABLA ESTIMACIÓN
	 // =========================

	 if (Estimacion != null && regresion != null) {

	     for (Dato dato : Estimacion.getItems()) {

	         String valorX = dato.getMuestra();

	         if (valorX == null || valorX.isBlank()) {
	             continue;
	         }

	         try {

	             double x = Double.parseDouble(valorX);

	             double y =
	                     regresion.getIntercepto()
	                     + (regresion.getPendiente() * x);

	             dato.setValor(formatear(y));

	         } catch (NumberFormatException e) {

	             // ignorar filas inválidas
	         }
	     }

	     Estimacion.refresh();
	 }
	
	}
	
	private void dibujarGrafica(double[] x, double[] y) {

	    Grafica.getData().clear();

	    // =========================
	    // EJES
	    // =========================

	    NumberAxis ejeX = (NumberAxis) Grafica.getXAxis();
	    NumberAxis ejeY = (NumberAxis) Grafica.getYAxis();

	    double minX = EstadisticaDescriptiva.minimo(x);
	    double maxX = EstadisticaDescriptiva.maximo(x);

	    double minY = EstadisticaDescriptiva.minimo(y);
	    double maxY = EstadisticaDescriptiva.maximo(y);

	    ejeX.setAutoRanging(false);
	    ejeY.setAutoRanging(false);

	    ejeX.setLowerBound(minX - 1);
	    ejeX.setUpperBound(maxX + 1);

	    ejeY.setLowerBound(minY - 1);
	    ejeY.setUpperBound(maxY + 1);

	    ejeX.setTickUnit(1);
	    ejeY.setTickUnit(1);

	    // =========================
	    // DISPERSIÓN
	    // =========================

	    XYChart.Series<Number, Number> dispersion =
	            new XYChart.Series<>();

	    dispersion.setName("Datos");

	    for (int i = 0; i < x.length; i++) {

	        dispersion.getData().add(
	                new XYChart.Data<>(x[i], y[i])
	        );
	    }

	    // =========================
	    // RECTA DE REGRESIÓN
	    // =========================

	    XYChart.Series<Number, Number> recta =
	            new XYChart.Series<>();

	    recta.setName("Recta de regresión");

	    double y1 = regresion.getIntercepto() + (regresion.getPendiente() * minX);

	    double y2 = regresion.getIntercepto() + (regresion.getPendiente() * maxX);

	    recta.getData().add(
	            new XYChart.Data<>(minX, y1)
	    );

	    recta.getData().add(
	            new XYChart.Data<>(maxX, y2)
	    );

	    // =========================
	    // AGREGAR SERIES
	    // =========================

	    Grafica.getData().addAll(dispersion, recta);
	    
	    // quitar línea de dispersión
	    dispersion.getNode().setStyle(
	        "-fx-stroke-width: 0px;"
	    );

	    // =========================
	    // ESTILO DISPERSIÓN
	    // =========================

	    // mantener puntos visibles
	    for (XYChart.Data<Number, Number> data : dispersion.getData()) {

	        data.getNode().setStyle(
	                "-fx-background-radius: 5px;"
	        );
	    }
	}
	
	@FXML
	private void abrirOpciones() {

	    try {

	    	Image icon = new Image (getClass().getResourceAsStream("/recursos/Icon.png"));
			
	    	
	        FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/ventanas/VentanaOpciones.fxml"));

	        Parent root = loader.load();
	        
	        ConfigController controller = loader.getController();
	        controller.setTableController(this);
	        
	        Stage stage = new Stage();
	        stage.setTitle("Configuracion (Calculos)");
	        stage.getIcons().add(icon);

	        Scene scene = new Scene(root);

	        GestorTemas.preparar(scene);

	        stage.setScene(scene);
	        stage.initModality(Modality.APPLICATION_MODAL);
	        stage.setResizable(false);
	        stage.showAndWait();

	    } catch (Exception e) {

	        e.printStackTrace();
	    }
	}
	
	
	
	private void actualizarFormato() {

	    if (regresion != null) {
	        obtenerDatos();
	    }
	}
	
	private String formatear(double valor) {

	    String texto = Double.toString(valor);

	    if (texto.contains("E") || texto.contains("e")) {
	        java.math.BigDecimal bd = new java.math.BigDecimal(texto);
	        texto = bd.toPlainString();
	    }

	    int punto = texto.indexOf('.');

	    if (punto == -1) {
	        return texto;
	    }

	    int fin = punto + limiteDecimales + 1;

	    if (fin < texto.length()) {
	        texto = texto.substring(0, fin);
	    }

	    while (texto.endsWith("0")) {
	        texto = texto.substring(0, texto.length() - 1);
	    }

	    if (texto.endsWith(".")) {
	        texto = texto.substring(0, texto.length() - 1);
	    }

	    return texto;
	}
	
	public void setLimiteDecimales(int limite) { //Reasignar clase
	    this.limiteDecimales = limite;
	    actualizarFormato();
	}
	
	//Cambiar nombres
	
	@FXML
	private void cambiarNombreX() {

	    TextInputDialog dialogo = new TextInputDialog(estado.getNombreX());

	    dialogo.setTitle("Nombre de Variable");
	    dialogo.setHeaderText("Asignar nombre a la variable X");
	    dialogo.setContentText("Nombre:");

	    Optional<String> resultado = dialogo.showAndWait();

	    resultado.ifPresent(nombre -> {

	        nombre = nombre.trim();

	        if (nombre.isBlank() || nombre.equalsIgnoreCase("X")) {
	            estado.setNombreX("X");
	            tpX.setText("X");
	        } else {
	            estado.setNombreX(nombre);
	            tpX.setText("X (" + nombre + ")");
	        }

	    });

	}
	
	@FXML
	private void cambiarNombreY() {

	    TextInputDialog dialogo = new TextInputDialog(estado.getNombreY());

	    dialogo.setTitle("Nombre de Variable");
	    dialogo.setHeaderText("Asignar nombre a la variable Y");
	    dialogo.setContentText("Nombre:");

	    Optional<String> resultado = dialogo.showAndWait();

	    resultado.ifPresent(nombre -> {

	        nombre = nombre.trim();

	        if (nombre.isBlank() || nombre.equalsIgnoreCase("Y")) {
	            estado.setNombreY("Y");
	            tpY.setText("Y");
	        } else {
	            estado.setNombreY(nombre);
	            tpY.setText("Y (" + nombre + ")");
	        }

	    });

	}
	
	private void actualizarBotones() {

	    Bounds y = tpY.localToParent(tpY.getBoundsInLocal());
	    System.out.println("Bounds Y: " + y);

	    editY.setLayoutY(y.getMaxY()+36);
	    separator2.setLayoutY(y.getMaxY() + 36);
	}
	
	//MenuBar
	
	@FXML
	private void abrirRepositorio() {
	    MenuActions.abrirRepositorio();
	}
	
	//Exportaciones
	@FXML
	private void exportarJSON() {
		ExportController.exportarJSON(ventana, limiteDecimales);
	}
	
	@FXML
	private void exportarHTML() {
		ExportController.exportarHTML(ventana, limiteDecimales);
	}
	
}