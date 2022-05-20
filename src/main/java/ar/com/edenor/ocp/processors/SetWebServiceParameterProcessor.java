package ar.com.edenor.ocp.processors;

import java.util.Objects;

import org.apache.camel.Exchange;
import org.springframework.beans.factory.annotation.Autowired;

import ar.com.edenor.ocp.api.CancelarSuspenderRequest;
import ar.com.edenor.ocp.api.CancelarSuspenderRequestValidador;
import ar.com.edenor.ocp.exceptions.ValidationException;
import ar.com.edenor.ocp.idgenerator.RequestIdGenerator;
import ar.com.edenor.ocp.mapper.CancelSuspendRequestMapper;
import ar.com.edenor.ocp.validation.ValidationError;
import lombok.extern.slf4j.Slf4j;
import overit.geocallwfm.integration.workorders.cancelsuspendworkorder.CancelSuspend;



@Slf4j
public class SetWebServiceParameterProcessor extends BaseProcessor {

    @Override
    public void process(Exchange exchange)  {
        CancelarSuspenderRequest cancelarSuspenderRequest = exchange.getIn().getBody(CancelarSuspenderRequest.class);
        
		ValidationError error = validador.validar(cancelarSuspenderRequest);
		if (!Objects.isNull(error)) {
			exchange.setProperty("ERROR_MESSAGES",error);
			throw new ValidationException();
		}
        
		CancelSuspend cancelSuspendrequest = workOrderCancelSuspendRequestMapper.toCancelSuspend(cancelarSuspenderRequest);
		//cancelSuspendrequest.getRequest().getHeader().setRequestId(requestIdGenerator.getId(exchange.getExchangeId()));
		
        exchange.getOut().setBody(cancelSuspendrequest);
    }

    @Autowired
    private CancelSuspendRequestMapper workOrderCancelSuspendRequestMapper;
    @Autowired
    private CancelarSuspenderRequestValidador validador;
	/*
    @Autowired
	private RequestIdGenerator requestIdGenerator;
*/
}
