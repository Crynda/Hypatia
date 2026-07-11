package gui.util.export;

import gui.status.graphics.GraficoSesion;
import gui.status.graphics.PuntoGrafico;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;


public class ImageExport {


    public static void exportar(
            File archivo,
            GraficoSesion grafico
    ) {

        try {

            int ancho = 900;
            int alto = 600;


            BufferedImage imagen =
                    new BufferedImage(
                            ancho,
                            alto,
                            BufferedImage.TYPE_INT_RGB
                    );


            Graphics2D g =
                    imagen.createGraphics();


            // Fondo blanco

            g.setColor(Color.WHITE);
            g.fillRect(
                    0,
                    0,
                    ancho,
                    alto
            );


            // Aquí iría el sistema de coordenadas
            // para transformar los puntos


            for(PuntoGrafico punto : grafico.getDatos()) {


                int px =
                    (int)(punto.getX() * 50);


                int py =
                    alto -
                    (int)(punto.getY() * 50);



                g.fillOval(
                        px,
                        py,
                        8,
                        8
                );

            }


            g.dispose();


            ImageIO.write(
                    imagen,
                    "png",
                    archivo
            );


        } catch(Exception e) {

            e.printStackTrace();

        }

    }


}