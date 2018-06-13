package org.mocr.poc.reactive.positionbuilder.events;

import java.util.Date;
import org.apache.kafka.streams.KeyValue;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.Reducer;
import org.apache.log4j.Logger;
import org.mocr.poc.reactive.positionbuilder.model.Position;
import org.mocr.poc.reactive.positionbuilder.model.PositionEvent;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.binder.kafka.streams.annotations.KafkaStreamsProcessor;
import org.springframework.messaging.handler.annotation.SendTo;

 
@EnableBinding(KafkaStreamsProcessor.class)
@EnableAutoConfiguration
public class MovementsConsumer {

	private final Logger log = Logger.getLogger(MovementsConsumer.class); 

	@StreamListener("input")
	@SendTo("output")
	public KStream<?, PositionEvent> process(KStream<Object, MovementEvent> input) {
		log.info(input);
		return input.map((key, event) -> {
			log.info(event);
			return new KeyValue<Long, Movement>(event.getEntity().getObjectId(), event.getEntity());
		})
		.groupByKey()		
		.reduce(new MovementReducer()) 
		.toStream()
		.map((key, value) -> new KeyValue<>(null, new PositionEvent(new Position (key, value.getX(), value.getY(), new Date()))));
		
	} 
}



class MovementReducer implements Reducer<Movement> {

	private static final int MAX_X = 800;
	private static final int MAX_Y = 600;
	
	private static final int MIN_X = 0;
	private static final int MIN_Y = 0;

	@Override
	public Movement apply(Movement value1, Movement value2) { 		
		if ((value1.getX() + value2.getX()) > MAX_X) {
			value1.setX(MAX_X);
		} else if ((value1.getX() + value2.getX()) < MIN_X) {
			value1.setX(MIN_Y);
		} else {
			value1.setX(value1.getX() + value2.getX());
		}			
		if ((value1.getY() + value2.getY()) > MAX_Y) {
			value1.setY(MAX_Y);
		} else if ((value1.getY() + value2.getY()) < MIN_Y) {
			value1.setY(MIN_Y);
		} else {
			value1.setY(value1.getY() + value2.getY());
		}
		return value1;
	}

}
