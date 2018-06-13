package org.mocr.poc.reactive.position.model;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

 
public class Position implements Serializable { 
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L; 
 
	private long objectId;

	private int x;

	private int y;
	
	/**
	 * Created At    
	 */
	private Date createdAt; 
	
	 
	@JsonCreator
	public Position (@JsonProperty(value = "objectId", required = true) long objectId, 
			@JsonProperty(value = "x", required = true) int x, 
			@JsonProperty(value = "y", required = true) int y, 
			@JsonProperty(value = "createdAt", required = true) Date createdAt) {
		this.objectId = objectId;
		this.x = x;
		this.y = y;
		this.createdAt = createdAt;
	}
	
	
 

	public long getObjectId() {
		return objectId;
	}

	public void setObjectId(long objectId) {
		this.objectId = objectId;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
	
	@Override
	public String toString() {
		return "Position [objectId=" + objectId + ", x=" + x + ", y=" + y + ", createdAt=" + createdAt + "]";
	} 
}
