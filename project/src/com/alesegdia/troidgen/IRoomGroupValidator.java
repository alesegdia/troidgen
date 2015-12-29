package com.alesegdia.troidgen;

import java.util.List;

import com.alesegdia.troidgen.room.Room;

public interface IRoomGroupValidator {

	public boolean validate( List<Room> outputLayout );
	
}
