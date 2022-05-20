package ar.com.edenor.ocp.api;

import lombok.Data;
import lombok.NonNull;

@Data
public class CancelarSuspenderRequest {
	//@NonNull
	public String sistemaOrigen;
	//@NonNull
	public String numeroOrden;
	public String idDespacho;
	//@NonNull
	public String tipoOperacion;
}
