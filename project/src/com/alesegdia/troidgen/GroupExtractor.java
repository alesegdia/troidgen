package com.alesegdia.troidgen;

import java.util.LinkedList;
import java.util.List;
import com.alesegdia.troidgen.util.Rect;

public class GroupExtractor {
	
	public List<List<Rect>> solve( List<Rect> allRects )
	{
		List<List<Rect>> rectGroups = new LinkedList<List<Rect>>();
		List<Rect> assignedRooms = new LinkedList<Rect>();
		
		while( assignedRooms.size() < allRects.size() )
		{
			// one group for every room, because previously assigned rooms will be already in visited
			for( Rect outer_room : allRects )
			{
				// if this room was not assigned to any group
				if( !assignedRooms.contains(outer_room) )
				{
					// we create a group because we will be collecting all connected rooms from this one
					List<Rect> currentGroup = new LinkedList<Rect>();
					currentGroup.add(outer_room);
					assignedRooms.add(outer_room);
					
					collectConnected( allRects, assignedRooms, currentGroup, outer_room );
					
					// add the group to the return list
					rectGroups.add(currentGroup);
				}
			}
		}
		
		return rectGroups;
	}
	
	public void collectConnected(List<Rect> allRects, List<Rect> assigned, List<Rect> group, Rect r)
	{
		// iterate over all rooms to check which ones are connected
		for( Rect inner_room : allRects )
		{
			// if it's not the same room, it is not assigned and it is touching the other room
			if( r != inner_room && !assigned.contains(inner_room) && r.isTouching(inner_room) )
			{
				// add this room to the current group
				group.add(inner_room);
				
				// this room now has this group, so it's marked as visited
				assigned.add(inner_room);
			
				collectConnected( allRects, assigned, group, inner_room );
			}
		}
	}

}
