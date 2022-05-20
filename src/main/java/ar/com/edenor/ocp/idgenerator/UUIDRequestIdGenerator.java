package ar.com.edenor.ocp.idgenerator;

import java.util.UUID;

import org.springframework.stereotype.Service;

//@Service
public class UUIDRequestIdGenerator implements RequestIdGenerator {

	@Override
	public String getId(String valor) {
		return UUID.randomUUID().toString();
	}

}
