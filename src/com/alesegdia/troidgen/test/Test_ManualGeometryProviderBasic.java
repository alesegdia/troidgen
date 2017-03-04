package com.alesegdia.troidgen.test;

import java.util.List;

import com.alesegdia.troidgen.ManualRoomProvider;
import com.alesegdia.troidgen.restriction.RestrictionSet;
import com.alesegdia.troidgen.room.Room;
import com.alesegdia.troidgen.util.RNG;

public class Test_ManualGeometryProviderBasic {

	public static void main( String[] args )
	{
		RNG.rng = new RNG();

		ManualRoomProvider mgp = new ManualRoomProvider();
		
		mgp.add(new Room(3, 3, new RestrictionSet(4, true, false, false, false)));
		mgp.add(new Room(1, 2, new RestrictionSet(4, true, true, false, false)));
		mgp.add(new Room(2, 1, new RestrictionSet(4, true, true, false, true)));
		mgp.add(new Room(1, 3, new RestrictionSet(4, true, true, false, true)));
		mgp.add(new Room(3, 3, new RestrictionSet(4, true, false, true, false)));
		mgp.add(new Room(1, 3, new RestrictionSet(4, true, false, true, false)));
		mgp.add(new Room(2, 1, new RestrictionSet(4, true, false, false, false)));
		mgp.add(new Room(3, 3, new RestrictionSet(4, true, false, false, true)));
		mgp.add(new Room(3, 3, new RestrictionSet(4, true, false, false, false)));
		mgp.add(new Room(3, 3, new RestrictionSet(4, true, false, false, false)));
		
		test(mgp, 10, new RestrictionSet(4, true, false, false, false));
		// test(mgp, 10, new RestrictionSet(4, false, false, true, false)); fails because no elements resolves
		test(mgp, 10, new RestrictionSet(4, true, false, false, false));
		test(mgp, 10, new RestrictionSet(4, true, false, true, false));
		test(mgp, 10, new RestrictionSet(4, true, false, false, false));
	}
	
	private static void test( ManualRoomProvider mgp, int numRooms, RestrictionSet rs )
	{
		System.out.println("");
		List<Room> l = mgp.provideList(numRooms);
		System.out.println(l);
	}

}
