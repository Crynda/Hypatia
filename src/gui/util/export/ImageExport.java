package gui.util.export;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.Node;
import javafx.scene.SnapshotParameters;
import javafx.scene.image.WritableImage;

public final class ImageExport {

    private ImageExport() {

    }

    public static BufferedImage generar(Node nodo) {

        WritableImage imagen =
                nodo.snapshot(new SnapshotParameters(), null);

        return SwingFXUtils.fromFXImage(imagen, null);

    }

    public static void exportar(Node nodo, File archivo) {

        try {

            ImageIO.write(generar(nodo), "png", archivo);

        } catch (IOException e) {

            e.printStackTrace();

        }

    }

}