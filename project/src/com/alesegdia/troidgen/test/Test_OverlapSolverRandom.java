package com.alesegdia.troidgen.test;

import java.util.LinkedList;
import java.util.List;

import com.alesegdia.troidgen.GroupExtractor;
import com.alesegdia.troidgen.OverlapSolver;
import com.alesegdia.troidgen.OverlapSolverConfig;
import com.alesegdia.troidgen.renderer.RectDebugger;
import com.alesegdia.troidgen.util.RNG;
import com.alesegdia.troidgen.util.Rect;

public class Test_OverlapSolverRandom {

	public static void main( String[] args )
	{
		RNG.rng = new RNG();
		List<Rect> rects = new LinkedList<Rect>();
		for( int i = 0; i < 30; i++ )
		{
			int x = RNG.rng.nextInt(-4, 4);
			int y = RNG.rng.nextInt(-4, 4);
			int w = RNG.rng.nextInt(1, 3);
			int h = RNG.rng.nextInt(1, 3);
			rects.add(new Rect(x, y, w, h));
		}
		
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
