package ar.com.edenor.ocp.exceptions;

import org.apache.camel.Exchange;
import org.springframework.beans.factory.annotation.Autowired;

import ar.com.edenor.ocp.logging.LogUtils;
import ar.com.edenor.ocp.processors.CancelarSuspenderResponseExchangeBuilder;
import ar.com.edenor.ocp.validation.ValidationError;
import lombok.extern.slf4j.Slf4j;

@Slf4j(topic = "splunk")
public class ExceptionHandler {


    public void validationException(Exchange exchange) {
    	exchangeBuilder.doExchangeValidationError(exchange);
    }

    public void exception(Exchange exchange) {
    	Exception exception = exchangeBuilder.doExchangeException(exchange, "Exception");    	
		LogUtils.putErrorLogToMDC("genericException", exception.getMessage());
    }

    public void socketTimeoutException(Exchange exchange) {
    	exchangeBuilder.doExchangeSocketTimeoutException(exchange);
    }

    public void connectException(Exchange exchange) {
    	exchangeBuilder.doExchangeConnectException(exchange);
    }

    public void HTTPException(Exchange exchange) {
    	Exception exception = exchangeBuilder.doExchangeException(exchange, "HTTPException");
    	LogUtils.putErrorLogToMDC("connectionException", exception.getMessage());
    	log.error("Internal Error ", exception);
    }

    public void SoapFault(Exchange exchange) {
    	Exception exception = exchangeBuilder.doExchangeException(exchange, "SoapFault");
    	LogUtils.putSoapErrorLogToMDC("genericException", exception.getMessage());
    	log.info(exception.getMessage());
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
