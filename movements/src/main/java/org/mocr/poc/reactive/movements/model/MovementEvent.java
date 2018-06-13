package org.mocr.poc.reactive.movements.model; 

import java.util.Date;
import java.util.List;
import java.util.UUID; 

/**
 * The domain event {@link MovementEvent} tracks the type and state of events as applied to the {@link Movement} domain
 * object. This event resource can be used to event source the aggregate state of {@link Movement}.
 * <p>
 * This event resource also provides a transaction log that can be used to append actions to the event.
 *
 * @author MOCR
 */ 
public class MovementEvent {
	/**
	 * Event ID
	 */
	private UUID eventId; 	
	/**
	 * MovementEvent type
	 */
	private MovementEventType type; 
	/**
	 * {@link Movement}
	 */
	private List<Movement> entities; 
	

	/**
	 * Created At    
	 */
	private Date createdAt; 
	/**
	 * Updated At    
	 */
	private Date lastModified;
	
	public MovementEvent (List<Movement> entities, MovementEventType type) {
		this.setEntities(entities);
		this.setType(type);
		this.setCreatedAt(new Date());
		this.setEventId(UUID.randomUUID());
		this.setLastModified(new Date());
	}
	
	public UUID getEventId() {
		return eventId;
	}
	public void setEventId(UUID eventId) {
		this.eventId = eventId;
	}
	public MovementEventType getType() {
		return type;
	}
	public void setType(MovementEventType type) {
		this.type = type;
	}
	
	public List<Movement> getEntities() {
		return entities;
	}

	public void setEntities(List<Movement> entities) {
		this.entities = entities;
	}
	public Date getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
	public Date getLastModified() {
		return lastModified;
	}
	public void setLastModified(Date lastModified) {
		this.lastModified = lastModified;
	}

	@Override
	public String toString() {
		return "MovementEvent [eventId=" + eventId + ", type=" + type + ", entities=" + entities + ", createdAt="
				+ createdAt + ", lastModified=" + lastModified + "]";
	}

 
}
