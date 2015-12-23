package com.alesegdia.troidgen.test;

import java.util.LinkedList;
import java.util.List;

import com.alesegdia.troidgen.IGeometryProvider;
import com.alesegdia.troidgen.OverlapSolver;
import com.alesegdia.troidgen.OverlapSolverConfig;
import com.alesegdia.troidgen.RandomGeometryProvider;
import com.alesegdia.troidgen.renderer.RectDebugger;
import com.alesegdia.troidgen.util.RNG;
import com.alesegdia.troidgen.util.Rect;
import com.alesegdia.troidgen.util.Vec2;

public class Test_OverlapSolverRandomProvider {
	public static void main( String[] args )
	{
		RNG.rng = new RNG();
		IGeometryProvider geoProv = new RandomGeometryProvider( 1, 4 );

		List<Rect> rects = new LinkedList<Rect>();
		for( int i = 0; i < 20; i++ )
		{
			int x = RNG.rng.nextInt(-4, 4);
			int y = RNG.rng.nextInt(-4, 4);
			
			Vec2 size = geoProv.pickRandomSize();
			int w = (int) size.x;
			int h = (int) size.y;
			rects.add(new Rect(x, y, w, h));
		}
		
		OverlapSolver os = new OverlapSolver();
		OverlapSolverConfig osc = new OverlapSolverConfig();
		osc.separationParameter = 1f;
		osc.enableTweakNearSeparation = false;
		
		new RectDebugger(rects, 800, 600).Show();
		rects = os.solve(osc, rects);
		new RectDebugger(rects, 800, 600).Show();

	}
	
}
