package com.alesegdia.troidgen;

import java.util.List;

import com.alesegdia.troidgen.restriction.RestrictionSet;
import com.alesegdia.troidgen.room.Room;

public interface IRoomProvider {

	public List<Room> provideRandomList(int numRooms, RestrictionSet rs);
	
	public void notifySelected( List<Room> rooms );
	
}
