package org.mocr.poc.reactive.position.event;

import org.mocr.poc.reactive.position.model.PositionEvent;
import org.springframework.stereotype.Component;

import reactor.core.publisher.EmitterProcessor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.FluxSink;

@Component
public class PositionEventHandler {
	 
	EmitterProcessor<PositionEvent> processor = EmitterProcessor.create();
	FluxSink<PositionEvent> fluxSink = processor.sink(FluxSink.OverflowStrategy.LATEST); 
	Flux<PositionEvent> hotFlux = processor.publish().autoConnect(); 
     
	 public void handle (PositionEvent event) {  
		 fluxSink.next(event);
	 }
     
     public Flux<PositionEvent> getEvents () {
      return 
    	 Flux.<PositionEvent>create(sink -> {
    		 hotFlux.subscribe(event -> sink.next(event));
    	 })
    	 .share(); 
     }
}
