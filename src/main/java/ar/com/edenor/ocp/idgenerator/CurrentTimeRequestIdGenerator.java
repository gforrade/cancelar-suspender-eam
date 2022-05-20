package ar.com.edenor.ocp.idgenerator;

import org.springframework.stereotype.Service;

//@Service
public class CurrentTimeRequestIdGenerator implements RequestIdGenerator {

	@Override
	public String getId(String valor) {
		//LocalDateTime.now()
		return String.valueOf(System.currentTimeMillis());
	}

}
