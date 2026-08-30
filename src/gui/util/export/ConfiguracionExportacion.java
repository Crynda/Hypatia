package gui.util.export;

public class ConfiguracionExportacion {

    private boolean fecha;
    private boolean creditos;
    private boolean informacionComplementaria;

    public ConfiguracionExportacion() {

        // Valores por defecto
        fecha = true;
        creditos = true;
        informacionComplementaria = true;

    }

    public boolean isFecha() {
        return fecha;
    }

    public void setFecha(boolean fecha) {
        this.fecha = fecha;
    }

    public boolean isCreditos() {
        return creditos;
    }

    public void setCreditos(boolean creditos) {
        this.creditos = creditos;
    }

    public boolean isInformacionComplementaria() {
        return informacionComplementaria;
    }

    public void setInformacionComplementaria(boolean informacionComplementaria) {
        this.informacionComplementaria = informacionComplementaria;
    }

}