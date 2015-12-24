package com.alesegdia.troidgen;

import java.util.List;

import com.alesegdia.troidgen.room.Room;

public class GraphBuilder {

	public void generate( List<Room> rects )
	{
		for( Room router : rects )
		{
			for( Room rinner : rects )
			{
				if( router != rinner && router.isTouching(rinner) )
				{
					router.neighboors.add(rinner);
				}
			}
		}
	}
	
}
