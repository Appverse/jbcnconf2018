package org.mocr.poc.reactive.positionbuilder.events;

import java.io.Serializable;

public class Movement implements Serializable{
	 
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private long objectId; 
	
	private int x;
	
	private int y;

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

	@Override
	public String toString() {
		return "Movement [objectId=" + objectId + ", x=" + x + ", y=" + y + "]";
	}
	
	

}
