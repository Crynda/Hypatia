package gui.util.export;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.Node;
import javafx.scene.SnapshotParameters;
import javafx.scene.chart.LineChart;
import javafx.scene.image.WritableImage;

public class ImageExport {

	public static void exportar(Node nodo, File archivo) {

        try {

            WritableImage imagen = nodo.snapshot(new SnapshotParameters(), null);

            ImageIO.write(SwingFXUtils.fromFXImage(imagen, null), "png", archivo);

        } catch (IOException e) {

            e.printStackTrace();

        }

    }

}