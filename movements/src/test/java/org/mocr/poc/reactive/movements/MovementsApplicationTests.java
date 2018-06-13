package org.mocr.poc.reactive.movements;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.ClassRule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.mocr.poc.reactive.movements.controller.MovementsController;
import org.mocr.poc.reactive.movements.model.Movement;
import org.mocr.poc.reactive.movements.model.MovementEvent;
import org.mocr.poc.reactive.movements.model.MovementEventType;
import org.mocr.poc.reactive.movements.producer.MovementProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.http.MediaType;
import org.springframework.kafka.test.rule.KafkaEmbedded;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;

import reactor.core.publisher.Mono;
/**
 * Test web and producer. 
 * KafkaEmbedded to to mock the broker
 * @author MOCR
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest (webEnvironment=WebEnvironment.DEFINED_PORT)
public class MovementsApplicationTests {  

	private WebTestClient webTestClient;
	
	@Autowired Source source;
	
	@MockBean private MovementProducer emitter;   
	 
	@Autowired MovementsController controller;  
	
	@ClassRule
	public static KafkaEmbedded embeddedKafka = new KafkaEmbedded(1);
	
	@Before
	public void initClient() {
		webTestClient = WebTestClient.bindToServer().build();
	}
	 
	@BeforeClass
	    public static void setup() { 
	        System.setProperty("spring.cloud.stream.kafka.binder.brokers", embeddedKafka.getBrokersAsString());
	        System.setProperty("spring.cloud.stream.kafka.binder.zkNodes", embeddedKafka.getZookeeperConnectionString());
	    }
    
	@Test
	public void post() throws Exception {
		Movement mov = new Movement(123, 50, 50);
		
		MovementEvent movEvent = new MovementEvent(mov, MovementEventType.CREATED);
        
		BDDMockito.given(emitter.emit(Mono.just(mov)))
				.willReturn(Mono.just(movEvent)); 

		webTestClient.post().uri("http://localhost:8080/movements")
			.contentType(MediaType.APPLICATION_JSON)
			.body(BodyInserters.fromObject(mov))
			.exchange()
			.expectStatus()
			.isCreated()
			.expectBody(MovementEvent.class);
	}
	
    /* Mock Source Binding */
	@SpringBootApplication 
	@EnableBinding(Source.class) 
	public static class SourceProcessor { 
		   @Autowired private Source source;
	 
	}
	
 
}
