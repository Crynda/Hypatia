package gui.util;

import java.util.function.Consumer;


import javafx.animation.TranslateTransition;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.util.Duration;
import javafx.animation.FadeTransition;
import javafx.scene.layout.Pane;

public class SwitchToggle {

    private static final double POSICION_ACTIVADO = 44;
    private static final double POSICION_DESACTIVADO = 0;
    private static final String COLOR_ACTIVADO = "#2596be";
    private static final String COLOR_DESACTIVADO = "#9b9b9b";

    public static boolean toggleSwitch(Circle circle, boolean estadoActual) {

        boolean nuevoEstado = !estadoActual;

        TranslateTransition anim =
                new TranslateTransition(Duration.millis(250), circle);

        anim.setToX(nuevoEstado ? POSICION_ACTIVADO : POSICION_DESACTIVADO);

        anim.play();

        return nuevoEstado;
    }

    public static void establecerEstadoSwitch(Circle circle, boolean activo) {

        circle.setTranslateX(activo ? POSICION_ACTIVADO : POSICION_DESACTIVADO);

    }

    public static void configurarSwitch(
            Pane pane,
            Circle circle,
            boolean estadoInicial,
            boolean usarColor,
            Consumer<Boolean> accion) {

        establecerEstadoSwitch(circle, estadoInicial);

        if (usarColor) {
            establecerColorSwitch(pane, estadoInicial);
        }

        pane.setOnMouseClicked(e -> {

            boolean estadoActual =
                    circle.getTranslateX() == POSICION_ACTIVADO;

            boolean nuevoEstado =
                    toggleSwitch(circle, estadoActual);

            if (usarColor) {
                animarColorSwitch(pane, nuevoEstado);
            }

            accion.accept(nuevoEstado);

        });

    }
    
    private static void animarColorSwitch(Pane pane, boolean activo) {

        FadeTransition fadeOut = new FadeTransition(Duration.millis(100), pane);

        fadeOut.setToValue(0.7);

        fadeOut.setOnFinished(e -> {

            pane.setStyle(
                    "-fx-background-color: "
                    + (activo ? COLOR_ACTIVADO : COLOR_DESACTIVADO)
                    + ";"
                    + "-fx-background-radius: 20;"
            );

            FadeTransition fadeIn =
                    new FadeTransition(Duration.millis(100), pane);

            fadeIn.setToValue(1.0);

            fadeIn.play();
        });

        fadeOut.play();
    }
    
    public static void establecerColorSwitch(
            Pane pane,
            boolean activo) {

        pane.setStyle(
                "-fx-background-color: "
                + (activo ? COLOR_ACTIVADO : COLOR_DESACTIVADO)
                + ";"
                + "-fx-background-radius: 20;"
        );
    }	

}