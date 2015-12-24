package com.alesegdia.troidgen;

import java.util.List;

import com.alesegdia.troidgen.util.Rect;

public class MinSpaceRoomGroupEvaluator implements IRoomGroupEvaluator {

	private float min;

	public MinSpaceRoomGroupEvaluator( float min )
	{
		this.min = min;
	}
	
	@Override
	public boolean evaluate(List<Rect> group) {
		float f = 0;
		for( Rect r : group )
		{
			f += r.size.x * r.size.y;
		}
		return f > min;
	}

}
