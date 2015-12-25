package com.alesegdia.troidgen;

import java.util.List;

import com.alesegdia.troidgen.room.LinkPair;
import com.alesegdia.troidgen.room.Room;
import com.alesegdia.troidgen.util.RNG;

public class LinkBuilder {

	public void generate( List<Room> rooms )
	{
		for( Room r1 : rooms )
		{
			for( Room r2 : r1.neighboors )
			{
				if( r1 != r2 && r1.isTouching(r2) && !r1.directlyConnectedWith(r2) )
				{
					List<LinkPair> possibleLinks = r1.getPossibleConnections(r2);
					if( possibleLinks != null )
					{
						LinkPair lp = possibleLinks.get(RNG.rng.nextInt(0, possibleLinks.size()-1));
						lp.connect();
						
						assert(lp.lA.isConnected());
						assert(lp.lB.isConnected());
					}
				}
			}
		}
	}
	
}
