package org.mocr.poc.reactive.movements.producer;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface MovementChannels {

	 @Output
	 MessageChannel movements();
}
