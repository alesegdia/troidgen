package com.alesegdia.troidgen;

import java.util.List;

import com.alesegdia.troidgen.room.Room;
import com.alesegdia.troidgen.util.Rect;

public interface IRoomProvider {

	public Room createRandomRoom();
	public List<Room> provideRandomList(int i);
	
}
