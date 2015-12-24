package com.alesegdia.troidgen;

import java.util.LinkedList;
import java.util.List;

import com.alesegdia.troidgen.renderer.RectDebugger;
import com.alesegdia.troidgen.util.RNG;
import com.alesegdia.troidgen.util.Rect;
import com.alesegdia.troidgen.util.RectUtils;

public class LayoutBuilder {

	public List<Rect> generate( LayoutBuilderConfig lbc, IRoomProvider geometryProvider )
	{
		RNG.rng = new RNG();
		OverlapSolver os = new OverlapSolver();

		List<Rect> outputLayout = new LinkedList<Rect>();

		for( int i = 0; i < lbc.numIterations; i++ )
		{
			System.out.println(i);
			List<Rect> testRects = geometryProvider.provideRandomList( lbc.numRooms );
			RectUtils.RandomPlaceInRange(testRects, lbc.spawnRect);
			testRects.addAll(outputLayout);
			testRects = os.solve(lbc.osc, testRects, outputLayout);


			GroupExtractor ge = new GroupExtractor();
			List<List<Rect>> groups = ge.solve(testRects);
			outputLayout = getBestGroup(groups);
			
			//new RectDebugger(outputLayout, 800, 600).Show();

		}

		return outputLayout;
	}
	
	private List<Rect> getBestGroup( List<List<Rect>> groups )
	{
		List<Rect> best = groups.get(0);
		for( List<Rect> group : groups )
		{
			if( group.size() > best.size() )
			{
				best = group;
			}
		}
		return best;
	}

}
