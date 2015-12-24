package com.alesegdia.troidgen.test;

import java.util.LinkedList;
import java.util.List;

import com.alesegdia.troidgen.OverlapSolver;
import com.alesegdia.troidgen.OverlapSolverConfig;
import com.alesegdia.troidgen.renderer.RectDebugger;
import com.alesegdia.troidgen.util.RNG;
import com.alesegdia.troidgen.util.Rect;

public class Test_OverlapSolverRandomBig {

	public static void main( String[] args )
	{
		RNG.rng = new RNG();
		List<Rect> rects = new LinkedList<Rect>();
		for( int i = 0; i < 200; i++ )
		{
			int x = RNG.rng.nextInt(-20, 20);
			int y = RNG.rng.nextInt(-20, 20);
			int w = RNG.rng.nextInt(1, 3);
			int h = RNG.rng.nextInt(1, 3);
			rects.add(new Rect(x, y, w, h));
		}
		
		OverlapSolver os = new OverlapSolver();
		OverlapSolverConfig osc = new OverlapSolverConfig();
		osc.separationParameter = 1f;
		osc.enableTweakNearSeparation = false;
		osc.enclosingRect.size.x += 200;
		osc.enclosingRect.size.y += 200;
		osc.enclosingRect.position.x -= 100;
		osc.enclosingRect.position.y -= 100;
		
		
		new RectDebugger(rects, 800, 600).Show();
		rects = os.solve(osc, rects);
		new RectDebugger(rects, 800, 600).Show();
	}
	
}
