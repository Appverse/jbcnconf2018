package org.mocr.poc.reactive.positionbuilder.serde;

import java.util.Map;

import org.apache.kafka.common.serialization.Deserializer;
import org.mocr.poc.reactive.positionbuilder.events.Movement;
import com.fasterxml.jackson.databind.ObjectMapper;

public class MovementDeserializer implements Deserializer<Movement> {

	@Override
	public void close() {
	}

	@Override
	public Movement deserialize(String arg0, byte[] arg1) {
		ObjectMapper mapper = new ObjectMapper();
		Movement mov = null;
		try { 
			if (arg1 != null) {
			    mov = mapper.readValue(arg1, Movement.class);  
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mov;
	}

	@Override
	public void configure(Map arg0, boolean arg1) {
		// TODO Auto-generated method stub

	}

}
