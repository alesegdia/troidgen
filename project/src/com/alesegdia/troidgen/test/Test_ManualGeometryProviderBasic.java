package com.alesegdia.troidgen.test;

import com.alesegdia.troidgen.ManualRoomProvider;
import com.alesegdia.troidgen.room.Room;
import com.alesegdia.troidgen.util.RNG;

public class Test_ManualGeometryProviderBasic {

	public static void main( String[] args )
	{
		RNG.rng = new RNG();

		ManualRoomProvider mgp = new ManualRoomProvider();
		
		mgp.addGeometryModel(new Room(3, 3, null));
		mgp.addGeometryModel(new Room(1, 2, null));
		mgp.addGeometryModel(new Room(2, 1, null));
		mgp.addGeometryModel(new Room(1, 3, null));
		mgp.addGeometryModel(new Room(3, 3, null));
		mgp.addGeometryModel(new Room(1, 3, null));
		mgp.addGeometryModel(new Room(2, 1, null));
		mgp.addGeometryModel(new Room(3, 3, null));
		mgp.addGeometryModel(new Room(3, 3, null));
		
		mgp.debug();

	}

}
