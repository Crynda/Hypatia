package gui.componentes;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Dato {

    private final StringProperty muestra;
    private final StringProperty valor;

    public Dato(String muestra, String valor) {
        this.muestra = new SimpleStringProperty(muestra);
        this.valor = new SimpleStringProperty(valor);
    }

    public String getMuestra() {
        return muestra.get();
    }

    public void setMuestra(String muestra) {
        this.muestra.set(muestra);
    }

    public StringProperty muestraProperty() {
        return muestra;
    }

    public String getValor() {
        return valor.get();
    }

    public void setValor(String valor) {
        this.valor.set(valor);
    }

    public StringProperty valorProperty() {
        return valor;
    }
}