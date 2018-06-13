package org.mocr.poc.reactive.position.event;

import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KeyValue;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.Materialized;
import org.mocr.poc.reactive.position.model.Position;
import org.mocr.poc.reactive.position.model.PositionEvent;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.kafka.support.serializer.JsonSerde;


@EnableBinding(PositionsChannels.class) 
@EnableAutoConfiguration
public class PositionEventConsumer {  
	
	PositionEventHandler handler;
	  
	public PositionEventConsumer (PositionEventHandler handler) { 
		this.handler = handler;
	}
 
	@StreamListener 
	public void receive(@Input(PositionsChannels.INPUT)  KStream<?, PositionEvent> input) {   
	  input
	      .map ((k,v) -> {	    	  
	    	  handler.handle(v);
	    	  return new KeyValue<Long, Position>(v.getPosition().getObjectId(), v.getPosition()); 
	      })
		  .groupByKey() 
		  .reduce ((a,b) -> b, Materialized.as(PositionsChannels.POSITIONS_STORE) 
				  .withValueSerde(new JsonSerde(Position.class)))		            
          .toStream();
	}			 
	 
}

interface PositionsChannels {
	
	static final String POSITIONS_STORE = "positionsStore";
    
    String INPUT = "input";
  
    @Input(INPUT)
    KStream<?, PositionEvent> input();
}