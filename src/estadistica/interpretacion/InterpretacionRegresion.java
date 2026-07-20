package estadistica.interpretacion;

public class InterpretacionRegresion {

    private SemaforoInterpretacion semaforo;

    private String calidad;
    private String r2Pct;
    private String seRelPct;

    private String direccion;
    private String fuerza;

    private String ejemploX;
    private String ejemploY;

    private String pendienteDir;
    private String pendienteVal;

    private PreguntaInterpretacion pregunta1;
    private PreguntaInterpretacion pregunta2;
    private PreguntaInterpretacion pregunta3;

    private String conclusion;

    public InterpretacionRegresion() {

    }

    public SemaforoInterpretacion getSemaforo() {
        return semaforo;
    }

    public void setSemaforo(SemaforoInterpretacion semaforo) {
        this.semaforo = semaforo;
    }

    public String getCalidad() {
        return calidad;
    }

    public void setCalidad(String calidad) {
        this.calidad = calidad;
    }

    public String getR2Pct() {
        return r2Pct;
    }

    public void setR2Pct(String r2Pct) {
        this.r2Pct = r2Pct;
    }

    public String getSeRelPct() {
        return seRelPct;
    }

    public void setSeRelPct(String seRelPct) {
        this.seRelPct = seRelPct;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getFuerza() {
        return fuerza;
    }

    public void setFuerza(String fuerza) {
        this.fuerza = fuerza;
    }

    public String getEjemploX() {
        return ejemploX;
    }

    public void setEjemploX(String ejemploX) {
        this.ejemploX = ejemploX;
    }

    public String getEjemploY() {
        return ejemploY;
    }

    public void setEjemploY(String ejemploY) {
        this.ejemploY = ejemploY;
    }

    public String getPendienteDir() {
        return pendienteDir;
    }

    public void setPendienteDir(String pendienteDir) {
        this.pendienteDir = pendienteDir;
    }

    public String getPendienteVal() {
        return pendienteVal;
    }

    public void setPendienteVal(String pendienteVal) {
        this.pendienteVal = pendienteVal;
    }

    public PreguntaInterpretacion getPregunta1() {
        return pregunta1;
    }

    public void setPregunta1(PreguntaInterpretacion pregunta1) {
        this.pregunta1 = pregunta1;
    }

    public PreguntaInterpretacion getPregunta2() {
        return pregunta2;
    }

    public void setPregunta2(PreguntaInterpretacion pregunta2) {
        this.pregunta2 = pregunta2;
    }

    public PreguntaInterpretacion getPregunta3() {
        return pregunta3;
    }

    public void setPregunta3(PreguntaInterpretacion pregunta3) {
        this.pregunta3 = pregunta3;
    }

    public String getConclusion() {
        return conclusion;
    }

    public void setConclusion(String conclusion) {
        this.conclusion = conclusion;
    }

}