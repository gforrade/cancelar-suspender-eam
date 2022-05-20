package ar.com.edenor.ocp.validation;

public class ValidationError {
	
    private String parametro;
    private String codigoError;
    private String mensaje;

    public String descripcionError() {
    	return new StringBuilder()
				.append("Error de validacion de parametro ")
				.append(parametro)
				.append(": ")
				.append(mensaje)
				.toString();
	}

	public ValidationError(String parametro, String codigoError, String mensaje) {
		super();
		this.parametro = parametro;
		this.codigoError = codigoError;
		this.mensaje = mensaje;
	}

	public String getParametro() {
		return parametro;
	}

	public void setParametro(String parametro) {
		this.parametro = parametro;
	}

	public String getCodigoError() {
		return codigoError;
	}

	public void setCodigoError(String codigoError) {
		this.codigoError = codigoError;
	}

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

    
}
