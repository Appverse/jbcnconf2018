package org.mocr.poc.reactive.movements.controller;

import org.mocr.poc.reactive.movements.model.Movement;
import org.mocr.poc.reactive.movements.model.MovementEvent;
import org.mocr.poc.reactive.movements.producer.MovementProducer;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
/**
 * Spring 5 WebFlux annotated controller for @Link Movement
 * @author MOCR
 *
 */
@RestController
public class MovementsController {  
	/**
	 * Kafka Emitter 
	 */
	private MovementProducer emitter; 
 
	/**
	 * Constructor
	 * @param emitter
	 */
	public MovementsController (MovementProducer emitter) {
		this.emitter = emitter;
	}
    /**
     * POST endpoint for a movement
     * @param movement
     * @return event response
     */
	@PostMapping(path = "/movements", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus (HttpStatus.CREATED)
	public Mono<MovementEvent> create(@RequestBody Flux<Movement> movements) {	  
		return emitter.emit(movements);  
				  
	} 

}
