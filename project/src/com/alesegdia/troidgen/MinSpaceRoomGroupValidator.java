package com.alesegdia.troidgen;

import java.util.List;

import com.alesegdia.troidgen.util.Rect;
import com.alesegdia.troidgen.room.Room;

public class MinSpaceRoomGroupValidator implements IRoomGroupValidator {

	private float min;

	public MinSpaceRoomGroupValidator( float min )
	{
		this.min = min;
	}
	
	@Override
	public boolean validate(List<Room> group) {
		float f = 0;
		for( Rect r : group )
		{
			f += r.size.x * r.size.y;
		}
		return f > min;
	}

}
