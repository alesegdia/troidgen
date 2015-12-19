package com.alesegdia.troidgen.test;

import java.util.LinkedList;
import java.util.List;

import com.alesegdia.troidgen.overlapsolver.OverlapSolver;
import com.alesegdia.troidgen.overlapsolver.RectDebugger;
import com.alesegdia.troidgen.util.RNG;
import com.alesegdia.troidgen.util.Rect;

public class Test_RandomOverlapSolver {
	public static void main( String[] args )
	{
		RNG.rng = new RNG();
		List<Rect> rects = new LinkedList<Rect>();
		for( int i = 0; i < 10; i++ )
		{
			int x = RNG.rng.nextInt(-4, 4);
			int y = RNG.rng.nextInt(-4, 4);
			int s = RNG.rng.nextInt(2, 4);
			rects.add(new Rect(x, y, s, s));
		}
		OverlapSolver os = new OverlapSolver();
		new RectDebugger(rects, 400, 400).Show();
		os.solve(rects);
		new RectDebugger(rects, 400, 400).Show();
	}
	
}
