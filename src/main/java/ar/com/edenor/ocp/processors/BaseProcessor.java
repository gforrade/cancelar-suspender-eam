package ar.com.edenor.ocp.processors;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

public abstract class BaseProcessor implements Processor {

    protected void copyHeadersAndBody(Exchange exchange) {
        exchange.getOut().setHeaders(exchange.getIn().getHeaders());
        exchange.getOut().setBody(exchange.getIn().getBody());
    }

}
