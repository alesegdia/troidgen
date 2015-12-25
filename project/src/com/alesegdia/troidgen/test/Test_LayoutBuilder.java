package com.alesegdia.troidgen.test;

import java.util.List;

import com.alesegdia.troidgen.GraphBuilder;
import com.alesegdia.troidgen.IRoomProvider;
import com.alesegdia.troidgen.LayoutBuilder;
import com.alesegdia.troidgen.LayoutBuilderConfig;
import com.alesegdia.troidgen.LinkBuilder;
import com.alesegdia.troidgen.MinSizeRoomGroupValidator;
import com.alesegdia.troidgen.OverlapSolverConfig;
import com.alesegdia.troidgen.RandomRoomProvider;
import com.alesegdia.troidgen.renderer.RectDebugger;
import com.alesegdia.troidgen.room.Room;
import com.alesegdia.troidgen.util.MiscRoomUtils;
import com.alesegdia.troidgen.util.Rect;

public class Test_LayoutBuilder {

	public static void main( String[] args )
	{
		LayoutBuilder lb = new LayoutBuilder();
		LayoutBuilderConfig lbc = new LayoutBuilderConfig();
		lbc.spawnRect = new Rect(-10, -10, 20, 20);
		lbc.numIterations = 20;
		
		OverlapSolverConfig osc = new OverlapSolverConfig();
		osc.separationParameter = 1f;
		osc.enableTweakNearSeparation = false;
		osc.resolution = 1;
		
		lbc.osc = osc;
		lbc.spawnRect = new Rect(-8, -8, 16, 16);
		
		IRoomProvider roomProvider = new RandomRoomProvider( 1, 4 );
		MinSizeRoomGroupValidator msrge = new MinSizeRoomGroupValidator( 30 );
		List<Room> result = lb.generate(lbc, roomProvider, msrge);

		GraphBuilder gb = new GraphBuilder();
		gb.generate(result);
		
		System.out.println(result);
		
		LinkBuilder linksb = new LinkBuilder();
		linksb.generate(result);

		RectDebugger rd = new RectDebugger(result, 800, 600);
		rd.Show();

	}
	
}
