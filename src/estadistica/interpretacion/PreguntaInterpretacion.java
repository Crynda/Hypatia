package estadistica.interpretacion;

public class PreguntaInterpretacion {

    private String respuesta;
    private String detalle;

    public PreguntaInterpretacion() {

    }

    public PreguntaInterpretacion(String respuesta, String detalle) {
        this.respuesta = respuesta;
        this.detalle = detalle;
    }

    public String getRespuesta() {
        return respuesta;
    }

    public void setRespuesta(String respuesta) {
        this.respuesta = respuesta;
    }

    public String getDetalle() {
        return detalle;
    }

    public void setDetalle(String detalle) {
        this.detalle = detalle;
    }

}