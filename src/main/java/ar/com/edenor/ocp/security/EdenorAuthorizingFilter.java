package ar.com.edenor.ocp.security;

import javax.annotation.Priority;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Response;

import org.apache.cxf.interceptor.security.AccessDeniedException;
import org.apache.cxf.jaxrs.impl.ResponseBuilderImpl;
import org.apache.cxf.jaxrs.utils.JAXRSUtils;
import org.apache.cxf.phase.AbstractPhaseInterceptor;
import org.springframework.beans.factory.annotation.Autowired;

import ar.com.edenor.ocp.api.CancelarSuspenderErrorResponse;
import ar.com.edenor.ocp.logging.LogUtils;
import ar.com.edenor.ocp.processors.CancelarSuspenderResponseFactory;

@Priority(2000)
public class EdenorAuthorizingFilter implements ContainerRequestFilter {
    private String edenorIntegrationName;
    private AbstractPhaseInterceptor interceptor;
	private String sourceSystem;

    public EdenorAuthorizingFilter() {
    }

    public void filter(ContainerRequestContext context) {
        try {
            this.interceptor.handleMessage(JAXRSUtils.getCurrentMessage());
        } catch (AccessDeniedException ex) {
            ResponseBuilderImpl builder = new ResponseBuilderImpl();
            builder.status(Response.Status.UNAUTHORIZED);
            CancelarSuspenderErrorResponse cancelarSuspenderResponse = factory.buildErrorResponse("Unauthorized",ex.getMessage());
            builder.entity(cancelarSuspenderResponse);
            Response response = builder.build();
            response.getMetadata().add("Content-Type","application/json");
            LogUtils.putSecurityAccessErrorLogToMDC("Unauthorized", ex.getMessage(), edenorIntegrationName, String.valueOf(Response.Status.UNAUTHORIZED.getStatusCode()),sourceSystem);
            context.abortWith(response);
        }

    }

   public void setSourceSystem(String sourceSystem) {
        this.sourceSystem = sourceSystem;
    }
    public void setInterceptor(AbstractPhaseInterceptor in) {
        this.interceptor = in;
    }

    public void setEdenorIntegrationName(String edenorIntegrationName) {
        this.edenorIntegrationName = edenorIntegrationName;
    }
    
	@Autowired
	private CancelarSuspenderResponseFactory factory;

}

