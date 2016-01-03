package com.alesegdia.troidgen;

import java.util.LinkedList;
import java.util.List;
import com.alesegdia.troidgen.restriction.RestrictionSet;
import com.alesegdia.troidgen.room.Room;
import com.alesegdia.troidgen.util.RNG;
import com.alesegdia.troidgen.util.Util;

public class ManualRoomProvider implements IRoomProvider {

	private List<Room> availableGeom = new LinkedList<Room>();
	
	public void add( Room mg )
	{
		availableGeom.add(mg);
	}
	
	@Override
	public List<Room> provideRandomList(int numRooms, RestrictionSet rs) {
		List<Room> feasibleRooms = new LinkedList<Room>();
		
		for( Room r : availableGeom )
		{
			if( r.restrictionSet != null && r.restrictionSet.resolves(rs) )
			{
				feasibleRooms.add(r);
			}
		}
		
		assert(feasibleRooms.size() != 0);
		Util.shuffle(feasibleRooms);

		List<Room> retlist = new LinkedList<Room>();
		for( int i = 0; i < numRooms; i++ )
		{
			int max = feasibleRooms.size() - 1;
			assert max >= 0: "there are no rooms satisfying all restrictions";
			retlist.add(feasibleRooms.get(RNG.rng.nextInt(0, max)));
		}
		
		return retlist;
	}

	public void addAll(List<Room> rooms) {
		for( Room r : rooms )
		{
			add(r);
		}
	}

	@Override
	public void notifySelected(List<Room> rooms) {
		// TODO Auto-generated method stub
		
	}
	
}
