package com.alesegdia.troidgen.test;

import java.util.List;

import com.alesegdia.troidgen.GroupExtractor;
import com.alesegdia.troidgen.IRoomProvider;
import com.alesegdia.troidgen.OverlapSolver;
import com.alesegdia.troidgen.OverlapSolverConfig;
import com.alesegdia.troidgen.RandomRoomProvider;
import com.alesegdia.troidgen.renderer.RectDebugger;
import com.alesegdia.troidgen.room.Room;
import com.alesegdia.troidgen.util.RNG;
import com.alesegdia.troidgen.util.MiscRoomUtils;

public class Test_OverlapSolverRandomProvider {
	public static void main( String[] args )
	{
		RNG.rng = new RNG();
		IRoomProvider geoProv = new RandomRoomProvider( 1, 4 );

		List<Room> rects = geoProv.provideList(20);
		MiscRoomUtils.RandomPlaceInRange(rects, new Room(-4, -4, 8, 8));
		
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
