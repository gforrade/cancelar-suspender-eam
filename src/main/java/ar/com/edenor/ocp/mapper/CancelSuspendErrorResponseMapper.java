package ar.com.edenor.ocp.mapper;

import ar.com.edenor.ocp.api.CancelarSuspenderErrorResponse;
import ar.com.edenor.ocp.api.CancelarSuspenderRequest;
import ar.com.edenor.ocp.api.CancelarSuspenderResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import overit.geocallwfm.integration.commons.RequestHeader;
import overit.geocallwfm.integration.workorders.WorkOrderCancelSuspendRequest;
import overit.geocallwfm.integration.workorders.WorkOrderCancelSuspendRequestPayload;
import overit.geocallwfm.integration.workorders.WorkOrderCancelSuspendResponse;
import overit.geocallwfm.integration.workorders.cancelsuspendworkorder.CancelSuspend;

import javax.xml.bind.annotation.XmlElement;


@Mapper(componentModel = "spring")
public interface CancelSuspendErrorResponseMapper {

    @Mapping(source="workOrderCancelSuspendResponse.header.status" , target="estado")
    @Mapping(source="workOrderCancelSuspendResponse.header.errorCode" , target="codigoError")
    @Mapping(source="workOrderCancelSuspendResponse.header.errorDescription" , target="descripcionError")
    CancelarSuspenderErrorResponse toCancelarSuspenderResponse(WorkOrderCancelSuspendResponse workOrderCancelSuspendResponse);

}

