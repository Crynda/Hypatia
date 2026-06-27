package application;

import gui.config.Configuracion;
import gui.config.GestorConfiguracion;
import gui.config.GestorTemas;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {

        try {

           
            Image icon = new Image(getClass().getResourceAsStream("/recursos/Icon.png"));
            primaryStage.getIcons().add(icon);

            Parent root = FXMLLoader.load(getClass().getResource("/gui/escenas/Menu.fxml"));

            Scene scene = new Scene(root);

            Configuracion config = GestorConfiguracion.cargar();
            GestorTemas.aplicarDesdeConfig(config);
            GestorTemas.registrar(scene);

            // =========================
            // STAGE FINAL
            // =========================
            primaryStage.setScene(scene);
            primaryStage.setTitle("Hypatia");
            primaryStage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}