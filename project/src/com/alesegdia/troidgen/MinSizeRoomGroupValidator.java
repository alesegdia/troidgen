package com.alesegdia.troidgen;

import java.util.List;

import com.alesegdia.troidgen.room.Room;

public class MinSizeRoomGroupValidator implements IRoomGroupValidator {

	private int min;

	public MinSizeRoomGroupValidator( int min )
	{
		this.min = min;
	}
	
	@Override
	public boolean validate(List<Room> group) {
		return group.size() > this.min;
	}

}
