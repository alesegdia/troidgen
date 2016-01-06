package com.alesegdia.troidgen;

import java.util.List;

import com.alesegdia.troidgen.room.Room;

public class ExactRoomProviderValidator implements IRoomGroupValidator {

	private IRoomProvider rp;

	public ExactRoomProviderValidator( IRoomProvider rp )
	{
		this.rp = rp;
	}
	
	@Override
	public boolean validate(List<Room> group) {
		return this.rp.isOk();
	}

}
