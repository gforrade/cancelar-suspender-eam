package ar.com.edenor.ocp.processors;

import ar.com.edenor.ocp.api.CancelarSuspenderRequest;
import ar.com.edenor.ocp.api.CancelarSuspenderResponse;
import ar.com.edenor.ocp.mapper.CancelSuspendResponseMapper;

import lombok.extern.slf4j.Slf4j;
import org.apache.camel.Exchange;
import org.apache.cxf.message.MessageContentsList;
import org.springframework.beans.factory.annotation.Autowired;
import overit.geocallwfm.integration.workorders.WorkOrderCancelSuspendResponse;

import java.util.Objects;


@Slf4j
public class SetWebServiceResponseProcessor extends BaseProcessor {
/*
	@Autowired
	private CancelSuspendResponseMapper cancelSuspendResponseMapper;
*/

	@Override
	public void process(Exchange exchange)  {
		exchangeBuilder.doExchange(exchange);
		/*
		MessageContentsList messageContentsList = exchange.getIn().getBody(MessageContentsList.class);
		if (Objects.nonNull(messageContentsList) && !messageContentsList.isEmpty()) {
			CancelarSuspenderResponse cancelarSuspenderResponse = cancelSuspendResponseMapper.toCancelarSuspenderResponse((WorkOrderCancelSuspendResponse) messageContentsList.get(0));
			CancelarSuspenderRequest cancelarSuspenderRequest = exchange.getProperty("CANCEL_SUSPENDER_REQUEST",CancelarSuspenderRequest.class);
			cancelarSuspenderResponse.setRequest(cancelarSuspenderRequest);
			exchange.getOut().setBody(cancelarSuspenderResponse);
		}
*/
	}

	@Autowired
    private CancelarSuspenderResponseExchangeBuilder exchangeBuilder;
/*
	public CancelarSuspenderResponseExchangeBuilder getExchangeBuilder() {
		return exchangeBuilder;
	}

	public void setExchangeBuilder(CancelarSuspenderResponseExchangeBuilder exchangeBuilder) {
		this.exchangeBuilder = exchangeBuilder;
	}
*/
}
