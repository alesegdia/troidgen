package com.alesegdia.troidgen.test;

import java.util.LinkedList;
import java.util.List;

import com.alesegdia.troidgen.OverlapSolver;
import com.alesegdia.troidgen.OverlapSolverConfig;
import com.alesegdia.troidgen.renderer.RectDebugger;
import com.alesegdia.troidgen.util.RNG;
import com.alesegdia.troidgen.util.Rect;

public class Test_OverlapSolverManual {

	public static void main( String[] args )
	{
		RNG.rng = new RNG();

		List<Rect> rects = new LinkedList<Rect>();
		rects.add(new Rect(0, 0, 3, 3));
		rects.add(new Rect(2, 2, 4, 4));
		rects.add(new Rect(1, 2, 2, 2));
		rects.add(new Rect(3, 2, 3, 3));
		rects.add(new Rect(-1, 2, 2, 2));
		OverlapSolver os = new OverlapSolver();
		OverlapSolverConfig osc = new OverlapSolverConfig();
		new RectDebugger(rects, 400, 400).Show();
		rects = os.solve(osc, rects);
		new RectDebugger(rects, 400, 400).Show();
	}
	
}
