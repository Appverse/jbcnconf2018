package org.mocr.poc.reactive.positionbuilder.serde;

import java.util.Map;

import org.apache.kafka.common.serialization.Deserializer;
import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serializer;
import org.mocr.poc.reactive.positionbuilder.events.Movement;
import org.mocr.poc.reactive.positionbuilder.events.MovementEvent;

public class MovementSerde implements Serde<Movement> {
	private MovementSerializer serializer = new MovementSerializer();
	private MovementDeserializer deserializer = new MovementDeserializer();

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
	public Deserializer<Movement> deserializer() {
		// TODO Auto-generated method stub
		return deserializer;
	}

	@Override
	public Serializer<Movement> serializer() {
		// TODO Auto-generated method stub
		return serializer;
	}

}
