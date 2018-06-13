package org.mocr.poc.reactive.position.controller;

 
import org.mocr.poc.reactive.position.event.PositionEventHandler;
import org.mocr.poc.reactive.position.event.PositionEventStore;
import org.mocr.poc.reactive.position.model.Position;
import org.mocr.poc.reactive.position.model.PositionEvent;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Flux;

@RestController
@CrossOrigin(origins = "*")
public class PositionController {
	
	PositionEventStore repository;  
	PositionEventHandler events;
	 
	public PositionController (PositionEventStore repository, PositionEventHandler events ) {
	   this.repository = repository; 
	   this.events = events;
	}

	@GetMapping(path = "/positions", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.ACCEPTED)
	public Flux<Position> get() {	 
		return  repository.getPositions().log();
	}
 
	
	@GetMapping(value = "/positions/stream", produces = MediaType.TEXT_EVENT_STREAM_VALUE)  
	public Flux<PositionEvent> getAsStream() {	 
		return events.getEvents();
	}
	
 
}
