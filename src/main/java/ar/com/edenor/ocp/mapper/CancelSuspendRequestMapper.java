package ar.com.edenor.ocp.mapper;

import ar.com.edenor.ocp.api.CancelarSuspenderRequest;
import ar.com.edenor.ocp.idgenerator.RequestIdGeneratorBean;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import overit.geocallwfm.integration.commons.RequestHeader;
import overit.geocallwfm.integration.workorders.WorkOrderCancelSuspendRequest;
import overit.geocallwfm.integration.workorders.WorkOrderCancelSuspendRequestPayload;
import overit.geocallwfm.integration.workorders.cancelsuspendworkorder.CancelSuspend;


@Mapper(componentModel = "spring" , uses = RequestIdGeneratorBean.class)
public interface CancelSuspendRequestMapper {

    @Mapping(source="cancelarSuspenderRequest" , target="request")
    CancelSuspend toCancelSuspend(CancelarSuspenderRequest cancelarSuspenderRequest);

    @Mapping(source="cancelarSuspenderRequest" , target="header")
    @Mapping(source="cancelarSuspenderRequest" , target="payload")
    WorkOrderCancelSuspendRequest toWorkOrderCancelSuspendRequest(CancelarSuspenderRequest cancelarSuspenderRequest);

    @Mapping(source="numeroOrden" , target="externalCodeWO")
    @Mapping(source="tipoOperacion" , target="operationType")
    WorkOrderCancelSuspendRequestPayload toPayload(CancelarSuspenderRequest cancelarSuspenderRequest);

    @Mapping(source="sistemaOrigen" , target="sourceSystem")
    @Mapping(target="language" , constant = "es")
    //@Mapping(target="requestId" , constant = "1") //todo cambiar
    @Mapping(source="sistemaOrigen" ,target="requestId" ,  qualifiedByName = "requestId")
    RequestHeader toRequestHeader(CancelarSuspenderRequest cancelarSuspenderRequest);

}

