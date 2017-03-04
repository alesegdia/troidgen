package com.alesegdia.troidgen;

import java.util.List;

import com.alesegdia.troidgen.room.Room;

public interface IWorldComposer {

	public List<Room> compose(List<Room> fixed);
	
}
