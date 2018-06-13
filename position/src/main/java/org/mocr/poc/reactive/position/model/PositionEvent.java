package org.mocr.poc.reactive.position.model;

import java.util.Date;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
 
public class PositionEvent {
 
	private UUID id;
	
	private Position position;
	
	private Date createdAt;
	
	@JsonCreator
	public PositionEvent (@JsonProperty(value = "id", required = true) UUID id, 
			@JsonProperty(value = "position", required = true) Position position,
			@JsonProperty(value = "createdAt", required = true) Date createdAt) {
		this.position = position;
		this.id = id;
		this.createdAt = createdAt;
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public Position getPosition() {
		return position;
	}

	public void setPosition(Position position) {
		this.position = position;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
}
