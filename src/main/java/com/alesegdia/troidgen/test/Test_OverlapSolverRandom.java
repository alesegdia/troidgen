package com.alesegdia.troidgen.test;

import java.util.LinkedList;
import java.util.List;

import com.alesegdia.troidgen.GroupExtractor;
import com.alesegdia.troidgen.OverlapSolver;
import com.alesegdia.troidgen.OverlapSolverConfig;
import com.alesegdia.troidgen.renderer.RectDebugger;
import com.alesegdia.troidgen.room.Room;
import com.alesegdia.troidgen.util.RNG;

public class Test_OverlapSolverRandom {

	public static void main( String[] args )
	{
		RNG.rng = new RNG();
		List<Room> rects = new LinkedList<Room>();
		for( int i = 0; i < 20; i++ )
		{
			int x = RNG.rng.nextInt(-4, 4);
			int y = RNG.rng.nextInt(-4, 4);
			int w = RNG.rng.nextInt(1, 3);
			int h = RNG.rng.nextInt(1, 3);
			rects.add(new Room(x, y, w, h));
		}
		
		OverlapSolver os = new OverlapSolver();
		OverlapSolverConfig osc = new OverlapSolverConfig();
		osc.separationParameter = 1f;
		osc.enableTweakNearSeparation = false;		
		
		new RectDebugger(rects, 800, 600).Show();
		rects = os.solve(osc, rects);
		new RectDebugger(rects, 800, 600).Show();
		
		GroupExtractor ge = new GroupExtractor();
		List<List<Room>> groups = ge.solve(rects);

		List<Room> best = groups.get(0);
		for( List<Room> group : groups )
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
