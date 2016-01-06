package com.alesegdia.troidgen;

import java.util.LinkedList;
import java.util.List;

import com.alesegdia.troidgen.room.Room;

public class GroupExtractor {
	
	public List<List<Room>> solve( List<Room> testRects )
	{
		List<List<Room>> rectGroups = new LinkedList<List<Room>>();
		List<Room> assignedRooms = new LinkedList<Room>();
		
		while( assignedRooms.size() < testRects.size() )
		{
			// one group for every room, because previously assigned rooms will be already in visited
			for( Room outer_room : testRects )
			{
				// if this room was not assigned to any group
				if( !assignedRooms.contains(outer_room) )
				{
					// we create a group because we will be collecting all connected rooms from this one
					List<Room> currentGroup = new LinkedList<Room>();
					currentGroup.add(outer_room);
					assignedRooms.add(outer_room);
					
					collectConnected( testRects, assignedRooms, currentGroup, outer_room );
					
					// add the group to the return list
					rectGroups.add(currentGroup);
				}
			}
		}
		
		return rectGroups;
	}
	
	public void collectConnected(List<Room> testRects, List<Room> assignedRooms, List<Room> currentGroup, Room r)
	{
		// iterate over all rooms to check which ones are connected
		for( Room inner_room : testRects )
		{
			// if it's not the same room, it is not assigned and it is touching the other room
			if( r != inner_room && !assignedRooms.contains(inner_room) && r.isTouching(inner_room) && r.getPossibleConnections(inner_room).size() > 0 )
			{
				// add this room to the current group
				currentGroup.add(inner_room);
				
				// this room now has this group, so it's marked as visited
				assignedRooms.add(inner_room);
			
				collectConnected( testRects, assignedRooms, currentGroup, inner_room );
			}
		}
	}

}
