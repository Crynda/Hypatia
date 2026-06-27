package gui.config;

public class TestConfig {

    public static void main(String[] args) {

        //Cargar
        Configuracion config = GestorConfiguracion.cargar();

        System.out.println("=== CONFIG CARGADA ===");
        System.out.println("Tema: " + config.getTema());
        System.out.println("Decimales: " + config.getDecimales());
        System.out.println("Animaciones: " + config.isAnimaciones());
        System.out.println("Tooltips: " + config.isTooltips());

        //Modificar
        config.setTema("oscuro");
        config.setDecimales(8);

        //Guardar
        GestorConfiguracion.guardar(config);

        System.out.println("\n=== CONFIG GUARDADA ===");
    }
}