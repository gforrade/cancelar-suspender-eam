package ar.com.edenor.ocp.idgenerator;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class OrigenRequestIdGenerator implements RequestIdGenerator {

	@Override
	public String getId(String exchangeId) {
		return new StringBuilder()
		.append(origen)
		.append(exchangeId)
		.toString();
	}

	@Value("${OrigenRequestIdGenerator.origen}")
	private String origen;

}
