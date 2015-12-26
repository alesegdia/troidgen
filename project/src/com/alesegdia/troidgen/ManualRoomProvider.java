package com.alesegdia.troidgen;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.alesegdia.troidgen.restriction.RestrictionSet;
import com.alesegdia.troidgen.room.Room;
import com.alesegdia.troidgen.util.RNG;
import com.alesegdia.troidgen.util.Rect;
import com.alesegdia.troidgen.util.Vec2;

public class ManualRoomProvider implements IRoomProvider {

	private List<Room> availableGeom = new LinkedList<Room>();
	private Map<Vec2, List<Room>> geomBySize = new Hashtable<Vec2, List<Room>>();
	private List<Vec2> availableSizes = new ArrayList<Vec2>();
	
	public void addGeometryModel( Room mg )
	{
		if( !geomBySize.containsKey(mg.size) )
		{
			geomBySize.put(mg.size, new ArrayList<Room>());
		}
		geomBySize.get(mg.size).add(mg);
		
		if( !availableSizes.contains( mg.size ) )
		{
			availableSizes.add(new Vec2(mg.size));
		}
		
		availableGeom.add(mg);
	}
	
	public Room pickRandomRoomWithSize( Vec2 size )
	{
		return geomBySize.get( size ).get( RNG.rng.nextInt( 0, geomBySize.get( size ).size()-1));
	}
	
	public void debug()
	{
		Iterator it = geomBySize.entrySet().iterator();
		while(it.hasNext())
		{
			Map.Entry pair = (Map.Entry)it.next();
			System.out.println(pair.getKey() + " = " + pair.getValue());
		}
	}

	@Override
	public Room createRandomRoom() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Room> provideRandomList(int numRooms, RestrictionSet rs) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
