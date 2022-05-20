package ar.com.edenor.ocp.processors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.com.edenor.ocp.api.CancelarSuspenderErrorResponse;
import ar.com.edenor.ocp.api.CancelarSuspenderRequest;
import ar.com.edenor.ocp.api.CancelarSuspenderResponse;
import ar.com.edenor.ocp.api.Estado;
import ar.com.edenor.ocp.mapper.CancelSuspendErrorResponseMapper;
import ar.com.edenor.ocp.mapper.CancelSuspendResponseMapper;
import ar.com.edenor.ocp.validation.ValidationError;
import overit.geocallwfm.integration.commons.ResponseHeader;
import overit.geocallwfm.integration.workorders.WorkOrderCancelSuspendResponse;

@Service
public class CancelarSuspenderResponseFactory {

	public CancelarSuspenderResponse buildResponse(CancelarSuspenderRequest request, WorkOrderCancelSuspendResponse response) {
		ResponseHeader responseHeader = response.getHeader();
		CancelarSuspenderResponse response0;
		if (Estado.OK.name().equals(responseHeader.getStatus())) {
			response0 = cancelSuspendResponseMapper.toCancelarSuspenderResponse(response);			
		}
		else {
			response0 = cancelSuspendErrorResponseMapper.toCancelarSuspenderResponse(response);			
		}
		
		response0.setRequest(request);
		//response0.setEstado(responseHeader.getStatus());
		return response0;
	}

	public CancelarSuspenderErrorResponse buildErrorResponse(CancelarSuspenderRequest request, ValidationError error) {
		CancelarSuspenderErrorResponse response = new CancelarSuspenderErrorResponse();
		response.setRequest(request);
		response.setEstadoKO();
		
		response.codigoError = error.getCodigoError();
		response.descripcionError = error.descripcionError();

		return response;
	}

	public CancelarSuspenderErrorResponse buildErrorResponse(CancelarSuspenderRequest request, String message) {
		CancelarSuspenderErrorResponse response = new CancelarSuspenderErrorResponse();
		response.setRequest(request);
		response.setEstadoKO();
		
		response.codigoError = "Exception";
		response.descripcionError = message;

		return response;
	}

	public CancelarSuspenderErrorResponse buildErrorResponse(String codigo, String message) {
		CancelarSuspenderErrorResponse response = new CancelarSuspenderErrorResponse();
		response.setEstadoKO();
		
		response.codigoError = codigo;
		response.descripcionError = message;

		return response;
	}

	public CancelarSuspenderErrorResponse buildErrorResponse(CancelarSuspenderRequest request, String message, String codigo) {
		CancelarSuspenderErrorResponse response = new CancelarSuspenderErrorResponse();
		response.setRequest(request);
		response.setEstadoKO();
		
		response.codigoError = codigo;
		response.descripcionError = message;

		return response;
	}
	
	
	@Autowired
	private CancelSuspendResponseMapper cancelSuspendResponseMapper;

	@Autowired
	private CancelSuspendErrorResponseMapper cancelSuspendErrorResponseMapper;

}
