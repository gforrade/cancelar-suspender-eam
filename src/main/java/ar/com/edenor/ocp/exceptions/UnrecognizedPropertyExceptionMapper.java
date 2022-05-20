package ar.com.edenor.ocp.exceptions;

import com.fasterxml.jackson.databind.exc.UnrecognizedPropertyException;
import lombok.extern.slf4j.Slf4j;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

@Slf4j
public class UnrecognizedPropertyExceptionMapper extends BaseInputExceptionMapper  implements ExceptionMapper<UnrecognizedPropertyException> {

    @Override
    public Response toResponse(UnrecognizedPropertyException unrecognizedPropertyException) {
        return toInternalResponse(unrecognizedPropertyException.getMessage());
    }

}
