package org.mocr.poc.reactive.position.event;

import org.apache.kafka.streams.state.QueryableStoreTypes;
import org.apache.kafka.streams.state.ReadOnlyKeyValueStore;
import org.mocr.poc.reactive.position.model.Position;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.binder.kafka.streams.QueryableStoreRegistry;
import org.springframework.stereotype.Component;

import reactor.core.publisher.Flux;

@Component
public class PositionEventStore {
	
	 @Autowired
	 private QueryableStoreRegistry registry;

	 public Flux<Position> getPositions(){
		 return Flux.<Position>create(emitter -> { 
			 		ReadOnlyKeyValueStore<Long, Position> queryableStoreType = registry.getQueryableStoreType(PositionsChannels.POSITIONS_STORE, QueryableStoreTypes.keyValueStore());
			 		queryableStoreType.all().forEachRemaining( k -> emitter.next(k.value));
			 		emitter.complete();
		 		})
				 .share();
	 }
 
}
