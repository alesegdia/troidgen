package com.alesegdia.troidgen;

import java.util.List;

import com.alesegdia.troidgen.room.Room;
import com.alesegdia.troidgen.util.UpperMatrix2D;

public class GraphBuilder {

	public UpperMatrix2D<Float> build( List<Room> rects )
	{
		UpperMatrix2D<Float> m = new UpperMatrix2D<Float>( rects.size(), rects.size(), Float.MAX_VALUE );
		for( Room router : rects )
		{
			for( Room rinner : rects )
			{
				if( router != rinner && router.isTouching(rinner) )
				{
					router.neighboors.add(rinner);
					m.Set(rects.indexOf(router), rects.indexOf(rinner), router.position.distance(rinner.position));
				}
			}
		}
		return m;
	}
	
}
