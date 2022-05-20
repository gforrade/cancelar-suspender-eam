package ar.com.edenor.ocp.api;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import ar.com.edenor.ocp.validation.ValidationError;

@Service
public class CancelarSuspenderRequestValidador {

	public ValidationError validar(CancelarSuspenderRequest cancelarSuspenderRequest) {
				
		if (StringUtils.isEmpty(cancelarSuspenderRequest.sistemaOrigen)) {
			return newValidationErrorParametroRequerido("sistemaOrigen");
		}
		if (StringUtils.isEmpty(cancelarSuspenderRequest.numeroOrden)) {
			return newValidationErrorParametroRequerido("numeroOrden");	
		}
		if (StringUtils.isEmpty(cancelarSuspenderRequest.tipoOperacion)) {
			return newValidationErrorParametroRequerido("tipoOperacion");				
		}

		return null;
	}

	private ValidationError newValidationErrorParametroRequerido(String parametro) {
		return new ValidationError(parametro, "parametro requerido", "parametro requerido");
	}
	
}
