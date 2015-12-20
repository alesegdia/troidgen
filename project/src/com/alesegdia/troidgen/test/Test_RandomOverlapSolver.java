package com.alesegdia.troidgen.test;

import java.util.LinkedList;
import java.util.List;

import com.alesegdia.troidgen.overlapsolver.OverlapSolver;
import com.alesegdia.troidgen.overlapsolver.OverlapSolverConfig;
import com.alesegdia.troidgen.renderer.RectDebugger;
import com.alesegdia.troidgen.util.RNG;
import com.alesegdia.troidgen.util.Rect;

public class Test_RandomOverlapSolver {
	public static void main( String[] args )
	{
		RNG.rng = new RNG();
		List<Rect> rects = new LinkedList<Rect>();
		for( int i = 0; i < 50; i++ )
		{
			int x = RNG.rng.nextInt(-10, 10);
			int y = RNG.rng.nextInt(-10, 10);
			int s = RNG.rng.nextInt(1, 3);
			rects.add(new Rect(x, y, s, s));
		}
		
		OverlapSolver os = new OverlapSolver();
		OverlapSolverConfig osc = new OverlapSolverConfig();
		
		new RectDebugger(rects, 800, 600).Show();
		rects = os.solve(rects, osc);
		new RectDebugger(rects, 800, 600).Show();
	}
	
}
