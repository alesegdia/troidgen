package com.alesegdia.troidgen.room;

import java.util.LinkedList;
import java.util.List;

import com.alesegdia.troidgen.restriction.RestrictionSet;
import com.alesegdia.troidgen.util.Rect;
import com.alesegdia.troidgen.util.Vec2;

public class Room extends Rect {

	private List<Link> links = new LinkedList<Link>();
	public RestrictionSet restrictionSet;
	public List<Room> neighboors = new LinkedList<Room>();
	
	public Room( float width, float height, RestrictionSet rs ) {
		super( 0, 0, width, height );
		restrictionSet = rs;
		computeLinks();
	}
	
	public Room(Room r) {
		super( r.position.x, r.position.y, r.size.x, r.size.y );
		computeLinks();
	}

	public Room(float f, float g, float h, float i) {
		super(f, g, h, i);
		computeLinks();
	}

	private void computeLinks() {
		for( int x = 0; x < this.size.x; x++ )
		{
			for( int y = 0; y < this.size.y; y++ )
			{
				if( y == 0 )
				{
					this.links.add(new Link(x, y, Direction.TOP, this));
				}
				
				if( y == this.size.y -1 )
				{
					this.links.add(new Link(x, y, Direction.DOWN, this));
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

	public void addLink( Link l )
	{
		links.add(l);
	}

	public List<LinkPair> getPossibleConnections( Room other )
	{
		List<LinkPair> possibleConnections = null;
		for( Link l : links )
		{
			for( Link ol : other.links )
			if( l.canConnect( ol ) )
			{
				if( possibleConnections == null )
				{
					possibleConnections = new LinkedList<LinkPair>();
				}
				possibleConnections.add(new LinkPair( l, ol ));
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

	@Override
	public Room clone()
	{
		Room mg = new Room( this.size.x, this.size.y, this.restrictionSet );
		for( Link gl : links )
		{
			assert(gl.isConnected());
			mg.links.add( new Link(gl.relCoord.x, gl.relCoord.y, gl.direction, mg) );
		}
		return mg;
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
		String s = "\n<" + neighboors.size() + "-";
		
		for( Room r : neighboors )
		{
			s += r.position + ",";
		}
		
		s += ">\n";
		
		return s;		
	}

}
