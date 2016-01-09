package com.alesegdia.troidgen;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import com.alesegdia.troidgen.util.RNG;
import com.alesegdia.troidgen.renderer.RectDebugger;
import com.alesegdia.troidgen.restriction.RestrictionSet;
import com.alesegdia.troidgen.room.Room;
import com.alesegdia.troidgen.util.Logger;
import com.alesegdia.troidgen.util.MiscRoomUtils;

public class LayoutBuilder {
	
	// VER PARA QUITAR LAS HABITACIONES DENTRO DEL VALIDATOR!!
	
	public List<Room> generate( LayoutBuilderConfig lbc, IRoomProvider geometryProvider, IRoomGroupValidator rge ) {
		return generate(lbc, geometryProvider, rge, new LinkedList<Room>());
	}

	public List<Room> generate( LayoutBuilderConfig lbc, IRoomProvider geometryProvider, IRoomGroupValidator rge, List<Room> fixedGroup )
	{
		RNG.rng = new RNG();
		OverlapSolver os = new OverlapSolver();

		List<Room> outputLayout = new LinkedList<Room>();
		outputLayout.addAll(fixedGroup);

		List<Room> checkGroup = substraction(outputLayout, fixedGroup);
		int iters = 0;
		while( /* iters == 0 */ !rge.validate(checkGroup) )
		{
			iters++;
			Logger.Log("in");
			List<Room> testRects = geometryProvider.provideList( lbc.numRooms );
			MiscRoomUtils.RandomPlaceInRange(testRects, lbc.spawnRect);
			//testRects.addAll(outputLayout);

			testRects = os.solve(lbc.osc, testRects, outputLayout);
			Logger.Log("out");

			GroupExtractor ge = new GroupExtractor();
			List<List<Room>> groups = ge.solve(testRects);
			for( List<Room> lr : groups )
			{
				LinkBuilder lb = new LinkBuilder();
				lb.generate(lr);
			}
			outputLayout = getBestGroup(groups);
			geometryProvider.notifySelected(outputLayout);


			Logger.Log(outputLayout.size());
			
			/*
			(new RectDebugger(checkGroup, 800, 600)).Show();
			try {
				System.in.read();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			*/
			
			checkGroup = substraction(outputLayout, fixedGroup);

			Logger.Log(checkGroup.size());

		}

		return outputLayout;
	}
	
	private List<Room> substraction(List<Room> a, List<Room> b) {
		List<Room> l = new LinkedList<Room>();
		for( Room r1 : a )
		{
			if( !b.contains(r1) )
			{
				l.add(r1);
			}
		}
		return l;
	}

	private List<Room> getBestGroup( List<List<Room>> groups )
	{
		List<Room> best = groups.get(0);
		for( List<Room> group : groups )
		{
			if( group.size() > best.size() )
			{
				best = group;
			}
		}
		return best;
	}

}
