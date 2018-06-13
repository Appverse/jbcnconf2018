package org.mocr.poc.reactive.movements.producer;

import java.util.Map;

import org.apache.kafka.clients.producer.ProducerInterceptor;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.mocr.poc.reactive.movements.model.Movement;
import org.mocr.poc.reactive.movements.model.MovementEvent;
import org.mocr.poc.reactive.movements.model.MovementEventType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.integration.support.MessageBuilder;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
/**
 * Movement Kafka producer
 * @author MOCR
 *
 */
@EnableAutoConfiguration
@EnableBinding(Source.class)
public class MovementProducer {
	
	private static final Logger log = LoggerFactory.getLogger(MovementProducer.class);
    /**
     * Source
     */
	@Autowired
	Source source;
  
	/**
	 * Build and Send MovementEvent to a Kafka Topic (output)
	 * @param input
	 * @return
	 */
	public Mono<MovementEvent> emit(Flux<Movement> input) {
		  return input.collectList()
		           .map(movements -> {
		            	MovementEvent event = new MovementEvent(movements, MovementEventType.CREATED);		            	
		            	source.output().send(MessageBuilder.withPayload(event).build());
		            	return event;		             
		           });
	}  
	 
    /**
     * ProducerInterceptor 
     * Monitoring the f&F producer, it just use logger for undelivered messages to Kafka
     * @author MOCR
     *
     */
	public static class MovementProducerInterceptor implements ProducerInterceptor<byte[], byte[]> { 

		@Override
		public void close() {
		}

		@Override
		public void configure(Map<String, ?> configs) { 
		}

		@Override
		public ProducerRecord<byte[], byte[]> onSend(ProducerRecord<byte[], byte[]> record) { 
			log.info(record.toString());
			return record;
		}
        
		@Override
		public void onAcknowledgement(RecordMetadata metadata, Exception exception) {
			if (metadata != null) {
				log.info(metadata.toString());
				
			}
			if (exception != null) {
				log.error("> Failed to send > " + metadata);
				log.error(exception.getMessage());
			}
			
		}
	}
}
