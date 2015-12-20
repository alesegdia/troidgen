package com.alesegdia.troidgen.geom;

import java.util.LinkedList;
import java.util.List;

import com.alesegdia.troidgen.restriction.RestrictionSet;
import com.alesegdia.troidgen.util.Rect;

public class Geometry {

	private List<Link> links;
	public Rect rect;
	public RestrictionSet restrictionSet;
	
	public Geometry( float width, float height, RestrictionSet rs ) {
		rect = new Rect( 0, 0, width, height );
		restrictionSet = rs;
		links = new LinkedList<Link>();
	}
	
	public void addLink( Link l )
	{
		links.add(l);
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
	public Geometry clone()
	{
		Geometry mg = new Geometry( this.rect.size.x, this.rect.size.y, this.restrictionSet );
		for( Link gl : links )
		{
			assert(gl.isConnected());
			mg.links.add( new Link(gl.relCoord.x, gl.relCoord.y, gl.direction, mg) );
		}
		return mg;
	}

}
