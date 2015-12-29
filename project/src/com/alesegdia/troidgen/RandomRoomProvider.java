package com.alesegdia.troidgen;

import java.util.LinkedList;
import java.util.List;

import com.alesegdia.troidgen.restriction.RestrictionSet;
import com.alesegdia.troidgen.room.Room;
import com.alesegdia.troidgen.util.RNG;

public class RandomRoomProvider implements IRoomProvider {

	private int max;
	private int min;

	public RandomRoomProvider( int min, int max )
	{
		this.min = min; this.max = max;
	}
	
	public Room createRandomRoom()
	{
		return new Room( RNG.rng.nextInt(min, max), RNG.rng.nextInt(min, max), null );
	}

	@Override
	public List<Room> provideRandomList(int numRooms, RestrictionSet rs) {
		List<Room> rects = new LinkedList<Room>();
		for( int i = 0; i < numRooms; i++ )
		{
			rects.add(createRandomRoom());
		}
		return rects;
	}
	
}
