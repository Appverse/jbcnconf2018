package org.mocr.poc.reactive.movements.model;
/**
 * Movement object
 * @author MOCR
 *
 */
public class Movement {
	/**
	 * The object Id
	 */
	private long objectId; 
	/**
	 * X Axis movement (pixels)
	 */
	private int x;
	/**
	 * Y Axis movement (pixels)
	 */
	private int y;
	/**
	 * Eppty constructor
	 */
	public Movement() {}
	
	/**
	 * Constructor
	 * @param objectId
	 * @param x
	 * @param y
	 */
	public Movement (long objectId, int x, int y ) {
		this.objectId = objectId;
		this.x = x;
		this.y = y;
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

	@Override
	public String toString() {
		return "Movement [objectId=" + objectId + ", x=" + x + ", y=" + y + "]";
	}

}
