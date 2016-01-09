package com.alesegdia.troidgen.room;

import java.util.LinkedList;
import java.util.List;

import com.alesegdia.troidgen.restriction.RestrictionSet;
import com.alesegdia.troidgen.util.Vec2;

public class RoomInfo {

	public Vec2 size = new Vec2(0,0);
	public List<LinkInfo> linkInfo = new LinkedList<LinkInfo>();
	public RestrictionSet restriction;
	public RoomType rtype;
	public RestrictionSet passageNextRestriction;
	public int constraintSolved;
	public String id;
	public int neededRooms = 5;
	
	@Override
	public String toString()
	{
		String s = "[" + this.rtype;
		
		if( rtype == RoomType.ABILITY )
		{
			s += ",\n\tsolved(" + this.constraintSolved + ")";
		}
		if( rtype == RoomType.PASSAGE )
		{
			s += ",\n\tneeded(" + this.passageNextRestriction + ")";
		}
		
		s += ",\n\tsize(" + size.x + "," + size.y + "),\n";
		for( LinkInfo l : linkInfo )
		{
			String sl = "\t(" + l.direction + " (" + l.relativeCoordinate.x + "," + l.relativeCoordinate.y + ")),\n";
			s = s + sl;
		}
		return s + "]";
	}
	
}
