package org.mocr.poc.reactive.position.serde;

import java.util.Map;

import org.apache.kafka.common.serialization.Deserializer;
import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serializer;
import org.mocr.poc.reactive.position.model.Position;
 

public class PositionSerde implements Serde<Position> {
	private PositionSerializer serializer = new  PositionSerializer();
	private PositionDeserializer deserializer = new PositionDeserializer();

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
	public Deserializer<Position> deserializer() {
		// TODO Auto-generated method stub
		return deserializer;
	}

	@Override
	public Serializer<Position> serializer() {
		// TODO Auto-generated method stub
		return serializer;
	}

}
