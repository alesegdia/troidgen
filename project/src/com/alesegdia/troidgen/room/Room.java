package com.alesegdia.troidgen.room;

import java.util.LinkedList;
import java.util.List;

import com.alesegdia.troidgen.restriction.RestrictionSet;
import com.alesegdia.troidgen.util.Rect;
import com.alesegdia.troidgen.util.Vec2;

public class Room extends Rect {

	private List<Link> links;
	public RestrictionSet restrictionSet;
	public Room parent = null;
	
	public Room( float width, float height, RestrictionSet rs ) {
		super( 0, 0, width, height );
		restrictionSet = rs;
		computeLinks();
		links = new LinkedList<Link>();
	}
	
	private void computeLinks() {

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

}
