package com.alesegdia.troidgen.geom;

import com.alesegdia.troidgen.util.Vec2;

public class Link {

	public Vec2 relCoord = new Vec2(0, 0);
	public Direction direction;
	public Geometry ownerGeo;
	public Geometry connectedGeo = null;
	public Link connectedLink = null;
	
	public Link( float x, float y, Direction dir, Geometry owner )
	{
		this.relCoord.Set(x, y);
		this.direction = dir;
		this.ownerGeo = owner;
	}
	
	public Vec2 getAbsCoords()
	{
		return new Vec2( ownerGeo.rect.position.x + relCoord.x, ownerGeo.rect.position.y + relCoord.y);
	}

	public boolean isConnected() {
		return connectedGeo == null && connectedLink == null;
	}
	
}
