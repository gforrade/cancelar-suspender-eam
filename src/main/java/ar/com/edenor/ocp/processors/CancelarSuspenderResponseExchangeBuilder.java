package ar.com.edenor.ocp.processors;

import java.util.Objects;

import org.apache.camel.Exchange;
import org.apache.cxf.message.MessageContentsList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import ar.com.edenor.ocp.api.CancelarSuspenderRequest;
import ar.com.edenor.ocp.api.CancelarSuspenderResponse;
import ar.com.edenor.ocp.logging.LogUtils;
import ar.com.edenor.ocp.validation.ValidationError;
import lombok.extern.slf4j.Slf4j;
import overit.geocallwfm.integration.workorders.WorkOrderCancelSuspendResponse;

@Service
@Slf4j(topic = "splunk")
public class CancelarSuspenderResponseExchangeBuilder {

	//public void doExchange(Exchange exchange, ValidationError error) {
	public void doExchangeValidationError(Exchange exchange) {
		//CancelarSuspenderRequest request = exchange.getIn().getBody(CancelarSuspenderRequest.class);
		CancelarSuspenderRequest request = exchange.getProperty("CANCEL_SUSPENDER_REQUEST", CancelarSuspenderRequest.class);
		ValidationError error = exchange.getProperty("ERROR_MESSAGES",ValidationError.class);
		
		CancelarSuspenderResponse cancelarSuspenderResponse = factory.buildErrorResponse(request, error);

		exchange.getOut().setBody(cancelarSuspenderResponse);
		
		exchange.getOut().setHeader(Exchange.HTTP_RESPONSE_CODE, 404);			
	}

	public void doExchange(Exchange exchange) {
		MessageContentsList messageContentsList = exchange.getIn().getBody(MessageContentsList.class);
		if (Objects.nonNull(messageContentsList) && !messageContentsList.isEmpty()) {

			CancelarSuspenderRequest request = exchange.getProperty("CANCEL_SUSPENDER_REQUEST", CancelarSuspenderRequest.class);
			CancelarSuspenderResponse response = factory.buildResponse(request, (WorkOrderCancelSuspendResponse) messageContentsList.get(0));
			exchange.getOut().setBody(response);
			if (response.KO()) {
				exchange.getOut().setHeader(Exchange.HTTP_RESPONSE_CODE, 404);			
			}
			
		}
	}

	public Exception doExchangeException(Exchange exchange, String codigo) {

		CancelarSuspenderRequest request = exchange.getProperty("CANCEL_SUSPENDER_REQUEST", CancelarSuspenderRequest.class);
		Exception exception = exchange.getProperty(Exchange.EXCEPTION_CAUGHT, Exception.class);
		
		CancelarSuspenderResponse cancelarSuspenderResponse = factory.buildErrorResponse(request, exception.getMessage(),codigo);

		exchange.getOut().setBody(cancelarSuspenderResponse);
		
		exchange.getOut().setHeader(Exchange.HTTP_RESPONSE_CODE, 500);
		
		return exception;
	}

	public void doExchangeSocketTimeoutException(Exchange exchange) {
		CancelarSuspenderRequest request = exchange.getProperty("CANCEL_SUSPENDER_REQUEST", CancelarSuspenderRequest.class);
		Exception exception = exchange.getProperty(Exchange.EXCEPTION_CAUGHT, org.apache.cxf.interceptor.Fault.class);
		//SocketTimeoutException.class
		
		StringBuilder sb= new StringBuilder()
		.append(exception.getMessage())
		.append(" ")
		.append(exception.getCause().getMessage());
		//.append(ExceptionUtils.getRootCauseMessage(exception));
		//.append(ExceptionUtils.getRootCause(exception).getMessage());
		
		CancelarSuspenderResponse cancelarSuspenderResponse = factory.buildErrorResponse(request, sb.toString());

		exchange.getOut().setBody(cancelarSuspenderResponse);
		
		exchange.getOut().setHeader(Exchange.HTTP_RESPONSE_CODE, 503);			
	}


	public void doExchangeConnectException(Exchange exchange) {
		CancelarSuspenderRequest request = exchange.getProperty("CANCEL_SUSPENDER_REQUEST", CancelarSuspenderRequest.class);
		Exception exception = exchange.getProperty(Exchange.EXCEPTION_CAUGHT, Exception.class);
		
		LogUtils.putErrorLogToMDC("connectionException", exception.getMessage());
		log.error("Internal Error ", exception);
		
        String message = (Objects.nonNull(exception.getCause())) 
        		? exception.getCause().getMessage()
        		: "Error al invocar a " + geocallEndPoint ;
        		
		CancelarSuspenderResponse cancelarSuspenderResponse = factory.buildErrorResponse(request, message, "ConnectionError");

		exchange.getOut().setBody(cancelarSuspenderResponse);

		exchange.getOut().setHeader(Exchange.HTTP_RESPONSE_CODE, HttpStatus.INTERNAL_SERVER_ERROR.value());			
	}

	@Autowired
	private CancelarSuspenderResponseFactory factory;
	
    @Value("${endpoint.cancelsuspendWorkOrder.address}")
    private String geocallEndPoint;
	

}
