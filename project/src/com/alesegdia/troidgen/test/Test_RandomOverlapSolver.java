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
		for( int i = 0; i < 20; i++ )
		{
			int x = RNG.rng.nextInt(-4, 4);
			int y = RNG.rng.nextInt(-4, 4);
			int w = RNG.rng.nextInt(1, 4);
			int h = RNG.rng.nextInt(1, 4);
			rects.add(new Rect(x, y, w, h));
		}
		
		OverlapSolver os = new OverlapSolver();
		OverlapSolverConfig osc = new OverlapSolverConfig();
		osc.separationParameter = 1f;
		osc.enableTweakNearSeparation = false;
		
		new RectDebugger(rects, 800, 600).Show();
		rects = os.solve(rects, osc);
		new RectDebugger(rects, 800, 600).Show();
	}
	
}
