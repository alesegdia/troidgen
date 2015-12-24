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
		return connectedRoom == null && connectedLink == null;
	}

	public boolean canConnect(Link l) {
		if( !l.ownerRoom.collideWith(this.ownerRoom) )
		{
			if( getOppositeDirection() == l.direction )
			{
				Vec2 myAbs = this.getAbsCoords();
				Vec2 otherAbs = l.getAbsCoords();
				float dx = Math.abs( myAbs.x - otherAbs.x );
				float dy = Math.abs( myAbs.y - otherAbs.y );
				switch( direction )
				{
				case TOP:    if( dx == 0 && dy == 1 ) return true; break;
				case DOWN:  if( dx == 0 && dy == 1 ) return true; break;
				case LEFT:  if( dx == 1 && dy == 0 ) return true; break;
				case RIGHT: if( dx == 1 && dy == 0 ) return true; break;
				case NODIR: break;
				}
			}
		}
		return false;
	}
	
}
