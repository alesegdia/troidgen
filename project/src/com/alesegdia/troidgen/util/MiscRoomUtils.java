package com.alesegdia.troidgen.util;

import java.util.List;

import com.alesegdia.troidgen.room.Room;

public class MiscRoomUtils {

	public static void RandomPlaceInRange( List<Room> rects, Rect enclosing )
	{
		for( Room r : rects )
		{
			r.position.x = RNG.rng.nextInt((int) enclosing.position.x, (int) (enclosing.position.x + enclosing.size.x) );
			r.position.y = RNG.rng.nextInt((int) enclosing.position.y, (int) (enclosing.position.y + enclosing.size.y) );
		}
	}
	
}
