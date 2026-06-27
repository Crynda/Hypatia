package gui.config;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class GestorConfiguracion {

    private static final String RUTA =
            System.getProperty("user.home") + "/.hypatia/config.json";

    private static final Gson gson =
            new GsonBuilder().setPrettyPrinting().create();

    public static Configuracion cargar() {

        File archivo = new File(RUTA);

        // Si no existe, crear configuración por defecto
        if (!archivo.exists()) {

            Configuracion config = new Configuracion();
            guardar(config); // lo crea en disco
            return config;
        }

        try (FileReader reader = new FileReader(archivo)) {

            Configuracion config =
                    gson.fromJson(reader, Configuracion.class);

            // Seguridad por si el JSON está corrupto
            if (config == null) {
                return new Configuracion();
            }

            return config;

        } catch (IOException e) {
            e.printStackTrace();
            return new Configuracion();
        }
    }

    public static void guardar(Configuracion config) {

        File archivo = new File(RUTA);

        try {
            File padre = archivo.getParentFile();
            if (!padre.exists()) {
                padre.mkdirs();
            }

            try (FileWriter writer = new FileWriter(archivo)) {
                gson.toJson(config, writer);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}