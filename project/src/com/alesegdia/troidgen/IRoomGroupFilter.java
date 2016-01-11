package com.alesegdia.troidgen;

import java.util.List;

import com.alesegdia.troidgen.room.Room;

public interface IRoomGroupFilter {

	List<Room> filter(List<List<Room>> startRooms);

}
