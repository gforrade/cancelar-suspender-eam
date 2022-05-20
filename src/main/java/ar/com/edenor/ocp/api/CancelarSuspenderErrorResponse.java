package ar.com.edenor.ocp.api;

import lombok.Data;

@Data
public class CancelarSuspenderErrorResponse extends CancelarSuspenderResponse {
	public String codigoError;
	public String descripcionError;
}
