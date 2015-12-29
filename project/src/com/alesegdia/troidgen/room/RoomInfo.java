package com.alesegdia.troidgen.room;

import java.util.LinkedList;
import java.util.List;

import com.alesegdia.troidgen.restriction.RestrictionSet;
import com.alesegdia.troidgen.util.Vec2;

public class RoomInfo {

	public Vec2 size = new Vec2(0,0);
	public List<LinkInfo> linkInfo = new LinkedList<LinkInfo>();
	public RestrictionSet restriction;
	
	@Override
	public String toString()
	{
		String s = "[" + size.x + "," + size.y + ", ";
		for( LinkInfo l : linkInfo )
		{
			String sl = "(" + l.direction + " (" + l.relativeCoordinate.x + "," + l.relativeCoordinate.y + ")), ";
			s = s + sl;
		}
		return s + "]";
	}
	
}
