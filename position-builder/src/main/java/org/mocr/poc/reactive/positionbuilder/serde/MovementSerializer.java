package org.mocr.poc.reactive.positionbuilder.serde;

import java.util.Map;

import org.apache.kafka.common.serialization.Serializer;
import org.mocr.poc.reactive.positionbuilder.events.Movement;
import org.mocr.poc.reactive.positionbuilder.events.MovementEvent;

import com.fasterxml.jackson.databind.ObjectMapper;

public class MovementSerializer implements Serializer<Movement> { 
    @Override
    public void close() {

    }

	@Override
	public void configure(Map<String, ?> arg0, boolean arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public byte[] serialize(String arg0, Movement arg1) {
		byte[] retVal = null;
	    ObjectMapper objectMapper = new ObjectMapper();
	    try {
	      retVal = objectMapper.writeValueAsString(arg1).getBytes();
	    } catch (Exception e) {
	      e.printStackTrace();
	    }
	    return retVal;
	}
}
