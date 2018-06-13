package org.mocr.poc.reactive.position.serde;

import java.util.Map;

import org.apache.kafka.common.serialization.Deserializer;
import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serializer;
import org.mocr.poc.reactive.position.model.Position;
import org.mocr.poc.reactive.position.model.PositionEvent;
 

public class PositionEventSerde implements Serde<PositionEvent> {
	private  PositionEventSerializer serializer = new  PositionEventSerializer();
	private PositionEventDeserializer deserializer = new PositionEventDeserializer();

	@Override
	public void close() {
		serializer.close();
		deserializer.close();
	}

	@Override
	public void configure(Map<String, ?> arg0, boolean arg1) {
		serializer.configure(arg0, arg1);
		deserializer.configure(arg0, arg1);

	}

	@Override
	public Deserializer<PositionEvent> deserializer() {
		// TODO Auto-generated method stub
		return deserializer;
	}

	@Override
	public Serializer<PositionEvent> serializer() {
		// TODO Auto-generated method stub
		return serializer;
	}

}
