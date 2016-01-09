package com.alesegdia.troidgen;

import java.util.List;

import com.alesegdia.troidgen.room.Room;
import com.alesegdia.troidgen.util.Logger;

public class ExactRoomProviderValidator implements IRoomGroupValidator {

	private ExactRoomProvider rp;

	public ExactRoomProviderValidator( ExactRoomProvider erp )
	{
		this.rp = erp;
	}
	
	@Override
	public boolean validate(List<Room> group) {
		Logger.Log(group.size());
		return this.rp.isOk(); // || group.size() > this.rp.totalNeeded() / 4;
	}

}
