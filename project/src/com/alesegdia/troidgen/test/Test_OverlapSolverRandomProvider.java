package com.alesegdia.troidgen.test;

import java.util.List;

import com.alesegdia.troidgen.GroupExtractor;
import com.alesegdia.troidgen.IRoomProvider;
import com.alesegdia.troidgen.OverlapSolver;
import com.alesegdia.troidgen.OverlapSolverConfig;
import com.alesegdia.troidgen.RandomRoomProvider;
import com.alesegdia.troidgen.renderer.RectDebugger;
import com.alesegdia.troidgen.util.RNG;
import com.alesegdia.troidgen.util.Rect;
import com.alesegdia.troidgen.util.RectUtils;

public class Test_OverlapSolverRandomProvider {
	public static void main( String[] args )
	{
		RNG.rng = new RNG();
		IRoomProvider geoProv = new RandomRoomProvider( 1, 4 );

		List<Rect> rects = geoProv.provideRandomList(20);
		RectUtils.RandomPlaceInRange(rects, new Rect(-4, -4, 8, 8));
		
		OverlapSolver os = new OverlapSolver();
		OverlapSolverConfig osc = new OverlapSolverConfig();
		osc.separationParameter = 1f;
		osc.enableTweakNearSeparation = false;
		
		new RectDebugger(rects, 800, 600).Show();
		rects = os.solve(osc, rects);
		new RectDebugger(rects, 800, 600).Show();

		GroupExtractor ge = new GroupExtractor();
		List<List<Rect>> groups = ge.solve(rects);

		List<Rect> best = groups.get(0);
		for( List<Rect> group : groups )
		{
			if( group.size() > best.size() )
			{
				best = group;
			}
			System.out.println("size: " + group.size());
			System.out.println("data: " + group);
			System.out.println("========================");
		}

		new RectDebugger( best, 800, 600 ).Show();
		

	}
	
}
