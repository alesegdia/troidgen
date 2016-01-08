package com.alesegdia.troidgen;

import java.util.Hashtable;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.alesegdia.troidgen.restriction.RestrictionSet;
import com.alesegdia.troidgen.room.Room;
import com.alesegdia.troidgen.room.RoomInfo;
import com.alesegdia.troidgen.util.RNG;
import com.alesegdia.troidgen.util.Util;

public class ExactRoomProvider implements IRoomProvider {

	private List<Room> availableGeom = new LinkedList<Room>();
	private Map<RoomInfo, Integer> neededRooms = new Hashtable<RoomInfo, Integer>();
	private Map<RoomInfo, Integer> lastCount;
	private boolean isOk = false;
	private int totalNeeded;
	
	public void add( Room mg )
	{
		availableGeom.add(mg);
		this.totalNeeded += mg.rinfo.neededRooms;
		if( !neededRooms.containsKey(mg.rinfo) )
		{
			neededRooms.put(mg.rinfo, new Integer(mg.rinfo.neededRooms));
		}
	}

	@Override
	public List<Room> provideList(int numRooms) {
		
		List<Room> feasibleRooms = new LinkedList<Room>();
		
		for( Room r : availableGeom )
		{
			feasibleRooms.add(r);
		}
		
		assert(feasibleRooms.size() != 0);
		Util.shuffle(feasibleRooms);

		List<Room> retlist = new LinkedList<Room>();

		if( lastCount != null )
		{
			this.isOk = true;
			for( RoomInfo ri : neededRooms.keySet() )
			{
				int needed = neededRooms.get(ri);
				int present = 0;
				if( lastCount.containsKey(ri) )
				{
					present = lastCount.get(ri);
				}
	
				int diff = needed - present;
				
				for( int i = 0; i < diff; i++ )
				{
					isOk = false;
					retlist.add(new Room(ri));
				}
			}
		}
		else
		{
			for( RoomInfo ri : neededRooms.keySet() )
			{
				for( int i = 0; i < neededRooms.get(ri); i++ )
				{
					retlist.add(new Room(ri));
				}				
			}
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
		lastCount = new Hashtable<RoomInfo, Integer>();
		for( Room r : rooms )
		{
			if( !lastCount.containsKey(r.rinfo) )
			{
				lastCount.put(r.rinfo, new Integer(0));
			}
			lastCount.put(r.rinfo, lastCount.get(r.rinfo) + 1);
		}
	}

	@Override
	public boolean isOk() {
		return isOk;
	}

	public int totalNeeded() {
		return totalNeeded;
	}
	
}
