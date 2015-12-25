package com.alesegdia.troidgen.room;

import com.alesegdia.troidgen.util.Vec2;

public class Link {

	public Vec2 relCoord = new Vec2(0, 0);
	public Direction direction;
	public Room ownerRoom;
	public Room connectedRoom = null;
	public Link connectedLink = null;
	
	public Link( float x, float y, Direction dir, Room owner )
	{
		this.relCoord.Set(x, y);
		this.direction = dir;
		this.ownerRoom = owner;
	}
	
	public Vec2 getAbsCoords()
	{
		return new Vec2( ownerRoom.position.x + relCoord.x, ownerRoom.position.y + relCoord.y);
	}
	
	public void disconnect()
	{
		// clean the other link
		this.connectedLink.connectedRoom = null;
		this.connectedLink.connectedLink = null;

		// clean myself
		this.connectedRoom = null;
		this.connectedLink = null;
	}

	public Direction getOppositeDirection ()
	{
		switch( direction )
		{
		case TOP: return Direction.DOWN;
		case DOWN: return Direction.TOP;
		case LEFT: return Direction.RIGHT;
		case RIGHT: return Direction.LEFT;
		default: return Direction.NODIR;
		}
	}
	
	public void connectTo( Link other )
	{
		this.connectedRoom = other.ownerRoom;
		this.connectedLink = other;
		other.connectedRoom = this.ownerRoom;
		other.connectedLink = this;
	}

	public boolean isConnected() {
		return connectedRoom != null && connectedLink != null;
	}

	public boolean canConnect(Link l) {
		assert(l.direction != Direction.NODIR);
		if( l.ownerRoom != this.ownerRoom && l.ownerRoom.isTouching(this.ownerRoom) && getOppositeDirection() == l.direction )
		{
			Vec2 myAbs = this.getAbsCoords();
			Vec2 otherAbs = l.getAbsCoords();
			
			float dx = ( myAbs.x - otherAbs.x );
			float dy = ( myAbs.y - otherAbs.y );

			switch( direction )
			{
			case TOP: return dx == 0 && dy == -1;
			case DOWN: return dx == 0 && dy == 1;
			case LEFT: return dx == 1 && dy == 0;
			case RIGHT: return dx == -1 && dy == 0;
			case NODIR: return false;
			}
		}

		return false;
	}
	
}
