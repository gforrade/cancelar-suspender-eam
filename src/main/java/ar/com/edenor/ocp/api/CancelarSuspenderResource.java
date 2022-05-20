package ar.com.edenor.ocp.api;

import javax.ws.rs.DELETE;

import javax.ws.rs.Path;
import javax.ws.rs.Produces;

@Path("tareas")
public interface CancelarSuspenderResource {
    @DELETE
    @Path("/")
    @Produces({"application/json"})
    CancelarSuspenderResponse delete(CancelarSuspenderRequest request);


}
