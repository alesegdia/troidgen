package com.alesegdia.troidgen;

import java.util.List;

import com.alesegdia.troidgen.room.Room;

public class BiggestGroupFilter implements IRoomGroupFilter {

	@Override
	public List<Room> filter(List<List<Room>> startRooms) {
		if( startRooms == null || startRooms.size() == 0 )
		{
			return null;
		}
		
		List<Room> best = startRooms.get(0);
		for( List<Room> r : startRooms )
		{
			if( r.size() > best.size() )
			{
				best = r;
			}
		}
		return best;
	}

}
