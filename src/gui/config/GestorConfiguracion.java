package gui.config;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import gui.util.export.ConfiguracionExportacion;

public final class GestorConfiguracion {

    private static final String RUTA_CONFIGURACION =
            System.getProperty("user.home") + "/.hypatia/config.json";

    private static final String RUTA_EXPORTACION =
            System.getProperty("user.home") + "/.hypatia/exportacion.json";

    private static final Gson gson =
            new GsonBuilder().setPrettyPrinting().create();


    // =========================
    // CONFIGURACIÓN GENERAL
    // =========================

    public static Configuracion cargar() {

        File archivo = new File(RUTA_CONFIGURACION);

        // Si no existe, crear configuración por defecto
        if (!archivo.exists()) {

            Configuracion config = new Configuracion();

            guardar(config);

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

        guardarArchivo(RUTA_CONFIGURACION, config);
    }


    // =========================
    // CONFIGURACIÓN DE EXPORTACIÓN
    // =========================

    public static ConfiguracionExportacion cargarExportacion() {

        File archivo = new File(RUTA_EXPORTACION);

        // Si no existe, crear configuración por defecto
        if (!archivo.exists()) {

            ConfiguracionExportacion config =
                    new ConfiguracionExportacion();

            guardarExportacion(config);

            return config;
        }

        try (FileReader reader = new FileReader(archivo)) {

            ConfiguracionExportacion config =
                    gson.fromJson(reader, ConfiguracionExportacion.class);

            // Seguridad por si el JSON está corrupto
            if (config == null) {

                return new ConfiguracionExportacion();
            }

            return config;

        } catch (IOException e) {

            e.printStackTrace();

            return new ConfiguracionExportacion();
        }
    }


    public static void guardarExportacion(
            ConfiguracionExportacion config) {

        guardarArchivo(RUTA_EXPORTACION, config);
    }


    // =========================
    // GUARDAR ARCHIVO
    // =========================

    private static void guardarArchivo(String ruta, Object config) {

        File archivo = new File(ruta);

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


    private GestorConfiguracion() {

    }
}

