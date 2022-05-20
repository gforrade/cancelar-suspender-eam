package ar.com.edenor.ocp.api;

import lombok.Data;

@Data
public class CancelarSuspenderResponse extends CancelarSuspenderRequest {
	private Estado estado;
	public void setRequest(CancelarSuspenderRequest request) {
		super.sistemaOrigen = request.sistemaOrigen;
		super.numeroOrden = request.numeroOrden;
		super.idDespacho = request.idDespacho;
		super.tipoOperacion = request.tipoOperacion;
	}
	public boolean OK() {
		return Estado.OK.equals(estado);
	}
	public boolean KO() {
		return Estado.KO.equals(estado);
	}
	public void setEstado(String estado) {
		this.estado = Estado.valueOf(estado);
	}
	public void setEstadoKO() {
		setEstado(Estado.KO);
	}

	public Estado getEstado() {
		return estado;
	}
	public void setEstado(Estado estado) {
		this.estado = estado;
	}
	
}
