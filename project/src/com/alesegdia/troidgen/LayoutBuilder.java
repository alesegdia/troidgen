package com.alesegdia.troidgen;

import java.util.LinkedList;
import java.util.List;

import com.alesegdia.troidgen.util.RNG;
import com.alesegdia.troidgen.restriction.RestrictionSet;
import com.alesegdia.troidgen.room.Room;
import com.alesegdia.troidgen.util.MiscRoomUtils;

public class LayoutBuilder {
	public List<Room> generate( LayoutBuilderConfig lbc, IRoomProvider geometryProvider, IRoomGroupValidator rge ) {
		return generate(lbc, geometryProvider, rge, new LinkedList<Room>());
	}

	public List<Room> generate( LayoutBuilderConfig lbc, IRoomProvider geometryProvider, IRoomGroupValidator rge, List<Room> fixedGroup )
	{
		RNG.rng = new RNG();
		OverlapSolver os = new OverlapSolver();

		List<Room> outputLayout = fixedGroup;

		while( !rge.validate(outputLayout) )
		{
			List<Room> testRects = geometryProvider.provideList( lbc.numRooms );
			MiscRoomUtils.RandomPlaceInRange(testRects, lbc.spawnRect);
			testRects.addAll(outputLayout);
			testRects = os.solve(lbc.osc, testRects, outputLayout);

			GroupExtractor ge = new GroupExtractor();
			List<List<Room>> groups = ge.solve(testRects);
			outputLayout = getBestGroup(groups);
		}

		return outputLayout;
	}
	
	private List<Room> getBestGroup( List<List<Room>> groups )
	{
		List<Room> best = groups.get(0);
		for( List<Room> group : groups )
		{
			if( group.size() > best.size() )
			{
				best = group;
			}
		}
		return best;
	}

}
