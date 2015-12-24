package com.alesegdia.troidgen;

import java.util.List;

import com.alesegdia.troidgen.room.Room;
import com.alesegdia.troidgen.util.Rect;

public interface IRoomGroupValidator {

	public boolean validate( List<Room> outputLayout );
	
}
