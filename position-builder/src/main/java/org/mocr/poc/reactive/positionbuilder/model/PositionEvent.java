package org.mocr.poc.reactive.positionbuilder.model;

import java.util.Date;
import java.util.UUID;

 
public class PositionEvent {
    
 
	private UUID id;
	
	private Position position;
	
	private Date createdAt;
	
	public PositionEvent (Position position) {
		this.position = position;
		this.id = UUID.randomUUID();
		this.createdAt = new Date();
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
