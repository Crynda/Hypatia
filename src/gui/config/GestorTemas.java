package gui.config;

import java.util.ArrayList;
import java.util.List;

import javafx.animation.FadeTransition;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.util.Duration;

public class GestorTemas {

    private static String temaActual = "claro";
    private static List<Scene> escenas = new ArrayList<>();

    // =========================
    // ESTADO
    // =========================

    public static String getTemaActual() {
        return temaActual;
    }

    public static void setTemaActual(String tema) {
        temaActual = tema;
    }

    public static void aplicarDesdeConfig(Configuracion config) {
        temaActual = config.getTema();
        actualizarTodas();
    }

    // =========================
    // CORE DE TEMAS
    // =========================

    private static boolean esOscuro() {
        return "oscuro".equalsIgnoreCase(temaActual);
    }

    public static void aplicarTema(Scene scene) {
        aplicarTema(scene, false);
    }

    public static void aplicarTema(Scene scene, boolean animado) {

        scene.getStylesheets().clear();

        String css = resolverCSS();

        if (animado) {

            Parent root = scene.getRoot();

            FadeTransition fadeOut = new FadeTransition(Duration.millis(150), root);
            fadeOut.setFromValue(1.0);
            fadeOut.setToValue(0.3);

            fadeOut.setOnFinished(event -> {

                scene.getStylesheets().add(css);

                FadeTransition fadeIn = new FadeTransition(Duration.millis(150), root);
                fadeIn.setFromValue(0.3);
                fadeIn.setToValue(1.0);
                fadeIn.play();
            });

            fadeOut.play();

        } else {
            scene.getStylesheets().add(css);
        }
    }

    public static void aplicarTemaActual(Scene scene) {
        aplicarTema(scene, false);
    }

    public static void aplicarTemaAnimado(Scene scene) {
        aplicarTema(scene, true);
    }

    // =========================
    // RESOLUCIÓN DE TEMA (CLAVE PARA FUTURO)
    // =========================

    private static String resolverCSS() {

        if (esOscuro()) {
            return GestorTemas.class
                    .getResource("/gui/styles/dark.css")
                    .toExternalForm();
        }

        return GestorTemas.class
                .getResource("/gui/styles/light.css")
                .toExternalForm();
    }

    // =========================
    // MANEJO GLOBAL DE ESCENAS
    // =========================

    public static void preparar(Scene scene) {
        registrar(scene);
        aplicarTemaActual(scene);
    }

    public static void registrar(Scene scene) {
        if (!escenas.contains(scene)) {
            escenas.add(scene);
        }
    }

    public static void actualizarTodas() {
        for (Scene scene : escenas) {
            aplicarTemaAnimado(scene);
        }
    }

    public static void cambiarTema(Configuracion config) {

        if ("oscuro".equalsIgnoreCase(config.getTema())) {
            config.setTema("claro");
        } else {
            config.setTema("oscuro");
        }

        aplicarDesdeConfig(config);
    }
}