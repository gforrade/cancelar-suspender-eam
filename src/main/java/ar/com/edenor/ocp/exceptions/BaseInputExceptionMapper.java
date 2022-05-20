package ar.com.edenor.ocp.exceptions;



import lombok.extern.slf4j.Slf4j;
import org.apache.cxf.jaxrs.impl.ResponseBuilderImpl;
import ar.com.edenor.ocp.api.CancelarSuspenderErrorResponse;
import ar.com.edenor.ocp.logging.LogUtils;
import ar.com.edenor.ocp.processors.CancelarSuspenderResponseFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.ws.rs.core.Response;

@Slf4j
public class BaseInputExceptionMapper {
    private String edenorIntegrationName;
    private String sourceSystem;
    private String endPointAddress;

    protected Response toInternalResponse(String message) {
        ResponseBuilderImpl builder = new ResponseBuilderImpl();
        builder.status(Response.Status.NOT_FOUND);
		CancelarSuspenderErrorResponse cancelarSuspenderResponse = factory.buildErrorResponse("Estructura Mensaje con Error",message);     
        builder.entity(cancelarSuspenderResponse);
        Response response = builder.build();
        response.getMetadata().add("Content-Type","application/json");
        LogUtils.putInputErrorToMDC(Response.Status.NOT_FOUND.getStatusCode(),edenorIntegrationName,sourceSystem,endPointAddress,cancelarSuspenderResponse.codigoError,cancelarSuspenderResponse.descripcionError);
        return response;
    }
	
	@Autowired
	private CancelarSuspenderResponseFactory factory;

    public void setEdenorIntegrationName(String edenorIntegrationName) {
        this.edenorIntegrationName = edenorIntegrationName;
    }

    public void setSourceSystem(String sourceSystem) {
        this.sourceSystem = sourceSystem;
    }

    public void setEndPointAddress(String endPointAddress) {
        this.endPointAddress = endPointAddress;
    }
}
