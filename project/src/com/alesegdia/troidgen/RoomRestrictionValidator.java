package com.alesegdia.troidgen;

import com.alesegdia.troidgen.restriction.RestrictionSet;
import com.alesegdia.troidgen.room.Room;

public class RoomRestrictionValidator implements IRoomValidator {

	private RestrictionSet rs;

	public RoomRestrictionValidator( RestrictionSet rs )
	{
		this.rs = rs;
	}

	@Override
	public boolean validate(Room r) {
		return rs.equals(r.rinfo.restriction);	
	}
	
	
	
}
