package estadistica.interpretacion;

public class PreguntaInterpretacion {

    private final String pregunta;

    private String respuesta;
    private String detalle;

    public PreguntaInterpretacion(String pregunta) {

        this.pregunta = pregunta;

    }

    public String getPregunta() {
        return pregunta;
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