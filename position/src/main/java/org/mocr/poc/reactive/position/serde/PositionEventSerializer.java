package org.mocr.poc.reactive.position.serde;

import java.util.Map;

import org.apache.kafka.common.serialization.Serializer;
import org.mocr.poc.reactive.position.model.PositionEvent;

import com.fasterxml.jackson.databind.ObjectMapper;

public class PositionEventSerializer implements Serializer<PositionEvent> { 
    @Override
    public void close() {

    }

	@Override
	public void configure(Map<String, ?> arg0, boolean arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public byte[] serialize(String arg0, PositionEvent arg1) {
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
