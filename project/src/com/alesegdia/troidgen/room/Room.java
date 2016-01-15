package com.alesegdia.troidgen.room;

import java.util.LinkedList;
import java.util.List;

import com.alesegdia.troidgen.restriction.RestrictionSet;
import com.alesegdia.troidgen.util.Rect;
import com.alesegdia.troidgen.util.Vec2;

public class Room extends Rect {

	public List<Link> links = new LinkedList<Link>();
	public RestrictionSet restrictionSet;
	public List<Room> neighboors = new LinkedList<Room>();
	public RoomType rtype;
	public RoomInfo rinfo = null;
	public boolean isVisited = false;
	
	public Room(RoomInfo rinfo)
	{
		this(rinfo.size.x, rinfo.size.y, rinfo.restriction);
		this.rinfo = rinfo;
		links.clear();
		for( LinkInfo linfo : rinfo.linkInfo )
		{
			links.add(new Link(linfo.relativeCoordinate.x, linfo.relativeCoordinate.y, linfo.direction, this));
		}
		this.rtype = rinfo.rtype;
		restrictionSet = rinfo.restriction;
	}
	
	public Room( float width, float height, RestrictionSet rs ) {
		super( 0, 0, width, height );
		restrictionSet = rs;
		computeAllLinks();
	}
	
	public Room(Room r) {
		super( r.position.x, r.position.y, r.size.x, r.size.y );
		copyLinks(r);
		this.rinfo = r.rinfo;
		//computeAllLinks();
	}

	private void copyLinks(Room r) {
		for( Link link : r.links )
		{
			this.links.add(new Link(link, this));
		}
	}

	public Room(float f, float g, float h, float i) {
		super(f, g, h, i);
		computeAllLinks();
	}

	private void computeAllLinks() {
		this.links = new LinkedList<Link>();
		for( int x = 0; x < this.size.x; x++ )
		{
			for( int y = 0; y < this.size.y; y++ )
			{
				if( y == 0 )
				{
					this.links.add(new Link(x, y, Direction.DOWN, this));
				}
				
				if( y == this.size.y -1 )
				{
					this.links.add(new Link(x, y, Direction.TOP, this));
				}
				
				if( x == 0 )
				{
					this.links.add(new Link(x, y, Direction.LEFT, this));
				}
				
				if( x == this.size.x -1 )
				{
					this.links.add(new Link(x, y, Direction.RIGHT, this));
				}
			}
		}
	}

	public List<LinkPair> getPossibleConnections( Room other )
	{
		List<LinkPair> possibleConnections = new LinkedList<LinkPair>();
		for( Link l : links )
		{
			for( Link ol : other.links )
			{
				if( l.canConnect( ol ) )
				{
					possibleConnections.add(new LinkPair( l, ol ));
				}
			}
		}
		return possibleConnections;
	}

	public List<Link> linksFacing( Direction dir )
	{
		List<Link> retlinks = new LinkedList<Link>();
		for( Link l : links )
		{
			if( l.direction == dir )
			{
				retlinks.add(l);
			}
		}
		return retlinks;
	}
	
	public boolean hasLinkFacing( Direction dir )
	{
		for( Link l : links )
		{
			if( l.direction == dir )
			{
				return true;
			}
		}
		return false;
	}

	public boolean connectsWith_(Room other, List<Room> visited)
	{
		for( Link l : this.links )
		{
			if( l.isConnected() && l.connectedRoom == other )
			{
				return true;
			}
			else if( !visited.contains(l.connectedRoom) )
			{
				visited.add(l.connectedRoom);
				return l.connectedRoom.connectsWith_(other, visited); 
			}
		}
		return false;
	}
	
	public boolean connectsWith(Room other)
	{
		List<Room> visited = new LinkedList<Room>();
		return connectsWith_(other, visited);
	}
	
	@Override
	public String toString()
	{
		return "(" + this.position.x + ", " + this.position.y + ", " + this.size.x + ", " + this.size.y + ")";
	}

	public boolean directlyConnectedWith(Room r2) {
		for( Link l : links )
		{
			if( l.isConnected() && l.connectedRoom == r2 )
			{
				return true;
			}
		}
		return false;
	}

	public Vec2 centroid() {
		return new Vec2( this.position.x + this.size.x / 2, this.position.y + this.size.y / 2 );
	}

}
