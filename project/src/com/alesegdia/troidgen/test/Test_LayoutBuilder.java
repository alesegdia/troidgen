package com.alesegdia.troidgen.test;

import java.util.List;

import com.alesegdia.troidgen.IRoomProvider;
import com.alesegdia.troidgen.LayoutBuilder;
import com.alesegdia.troidgen.LayoutBuilderConfig;
import com.alesegdia.troidgen.MinSizeRoomGroupEvaluator;
import com.alesegdia.troidgen.OverlapSolverConfig;
import com.alesegdia.troidgen.RandomRoomProvider;
import com.alesegdia.troidgen.renderer.RectDebugger;
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
		
		lbc.osc = osc;
		
		IRoomProvider roomProvider = new RandomRoomProvider( 1, 4 );
		MinSizeRoomGroupEvaluator msrge = new MinSizeRoomGroupEvaluator( 30 );
		List<Rect> result = lb.generate(lbc, roomProvider, msrge);

		RectDebugger rd = new RectDebugger(result, 800, 600);
		rd.Show();
		
	}
	
}
