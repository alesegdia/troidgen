package com.alesegdia.troidgen.util;

import java.util.List;

public class RectUtils {

	public static void RandomPlaceInRange( List<Rect> rects, Rect enclosing )
	{
		for( Rect r : rects )
		{
			r.position.x = RNG.rng.nextInt((int) enclosing.position.x, (int) (enclosing.position.x + enclosing.size.x) );
			r.position.y = RNG.rng.nextInt((int) enclosing.position.y, (int) (enclosing.position.y + enclosing.size.y) );
		}
	}
	
}
