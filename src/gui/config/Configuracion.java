package gui.config;

public class Configuracion {

    private String tema = "claro";
    private int decimales = 4;
    private boolean animaciones = true;
    private boolean tooltips = true;

    public Configuracion() {
    }

    public String getTema() {
        return tema;
    }

    public void setTema(String tema) {
        this.tema = tema;
    }

    public int getDecimales() {
        return decimales;
    }

    public void setDecimales(int decimales) {
        this.decimales = decimales;
    }

    public boolean isAnimaciones() {
        return animaciones;
    }

    public void setAnimaciones(boolean animaciones) {
        this.animaciones = animaciones;
    }

    public boolean isTooltips() {
        return tooltips;
    }

    public void setTooltips(boolean tooltips) {
        this.tooltips = tooltips;
    }
}