package estadistica.modelos;

public abstract class ResultadoEstadistico {
	
	protected boolean valido;
	protected String mensaje;
	
	public ResultadoEstadistico() {
		
	}
	
	public boolean isValido() {
		return valido;
	}
	
	public void setValido(boolean e) {
		this.valido = e;
	}
	
	public String getMensaje() {
		return mensaje;
	}
	
	public void setMensaje(String e) {
		this.mensaje = e;
	}

}
