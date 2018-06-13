package org.mocr.poc.reactive.position.serde;

import java.util.Map;

import org.apache.kafka.common.serialization.Deserializer;
import org.mocr.poc.reactive.position.model.PositionEvent;

import com.fasterxml.jackson.databind.ObjectMapper;

public class PositionEventDeserializer implements Deserializer<PositionEvent> {

	@Override
	public void close() {
	}

	@Override
	public PositionEvent deserialize(String arg0, byte[] arg1) {
		ObjectMapper mapper = new ObjectMapper();
		PositionEvent pos = null;
		try { 
			pos = mapper.readValue(arg1, PositionEvent.class);  
		} catch (Exception e) {
			e.printStackTrace();
		}
		return pos;
	}

	@Override
	public void configure(Map arg0, boolean arg1) {
		// TODO Auto-generated method stub

	}

}
