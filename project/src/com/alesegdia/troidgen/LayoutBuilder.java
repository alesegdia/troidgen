package com.alesegdia.troidgen;

import java.util.LinkedList;
import java.util.List;

import com.alesegdia.troidgen.util.RNG;
import com.alesegdia.troidgen.util.Rect;
import com.alesegdia.troidgen.room.Room;
import com.alesegdia.troidgen.util.MiscRoomUtils;

public class LayoutBuilder {

	public List<Room> generate( LayoutBuilderConfig lbc, IRoomProvider geometryProvider, IRoomGroupValidator rge )
	{
		RNG.rng = new RNG();
		OverlapSolver os = new OverlapSolver();

		List<Room> outputLayout = new LinkedList<Room>();

		while( !rge.validate(outputLayout) )
		{
			//System.out.println(i);
			List<Room> testRects = geometryProvider.provideRandomList( lbc.numRooms, null );
			MiscRoomUtils.RandomPlaceInRange(testRects, lbc.spawnRect);
			testRects.addAll(outputLayout);
			testRects = os.solve(lbc.osc, testRects, outputLayout);


			GroupExtractor ge = new GroupExtractor();
			List<List<Room>> groups = ge.solve(testRects);
			outputLayout = getBestGroup(groups);
			
			//new RectDebugger(outputLayout, 800, 600).Show();

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
