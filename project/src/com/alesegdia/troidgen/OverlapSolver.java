package com.alesegdia.troidgen;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import com.alesegdia.troidgen.renderer.RectDebugger;
import com.alesegdia.troidgen.room.Room;
import com.alesegdia.troidgen.util.RNG;
import com.alesegdia.troidgen.util.Rect;
import com.alesegdia.troidgen.util.Vec2;

public class OverlapSolver {

	public List<Room> solve( OverlapSolverConfig osc, List<Room> prects )
	{
		return solve( osc, prects, null );
	}
	
	public List<Room> solve( OverlapSolverConfig osc, List<Room> testRects, List<Room> outputLayout )
	{
		List<Room> rects = new LinkedList<Room>();
		for( Room r : testRects )
		{
			Room rr = new Room(r);
			rects.add(rr);
			rr.position.x *= osc.resolution;
			rr.position.y *= osc.resolution;
			rr.size.x *= osc.resolution;
			rr.size.y *= osc.resolution;
		}
		
		boolean overlap = true;
		while( overlap )
		{
			overlap = false;
			for( Room r1 : rects )
			{
				if( outputLayout == null || !outputLayout.contains(r1) )
				{
					Vec2 push = new Vec2(0,0);

					boolean thisOverlap = false;
					
					// compute possible push vector
					for( Room r2 : rects )
					{
						if( r1 != r2 && r1.collideWith(r2) )
						{
							overlap = true;
							thisOverlap = true;
							Vec2 normal = r1.position.sub(r2.position);
							push.x += normal.x;
							push.y += normal.y;
						}
					}
	
					// if there was overlap, displace
					if( thisOverlap )
					{
						float sepPar = osc.separationParameter;
						if( osc.enableTweakNearSeparation )
						{
							sepPar = osc.separationParameter * (r1.size.x + r1.size.y);
						}
						
						if( push.x != 0 || push.y != 0 )
						{
							if( osc.enableRandomDisplacement )
							{
								push.x += RNG.rng.nextFloat() / 5;
								push.y += RNG.rng.nextFloat() / 5;
							}
							repulse(r1, push, sepPar);
						}
						else if( osc.enableRandomPushIfZero )
						{
							repulse(r1, new Vec2(nonZeroRnd(-1, 1), nonZeroRnd(-1, 1)), sepPar );
						}
						
						if( r1.position.x < osc.enclosingLeft() ) r1.position.x = osc.enclosingLeft();
						if( r1.position.x + r1.size.x > osc.enclosingRight() ) r1.position.x = osc.enclosingRight();
						if( r1.position.y < osc.enclosingTop() ) r1.position.y = osc.enclosingTop();
						if( r1.position.y + r1.size.y > osc.enclosingBottom() ) r1.position.y = osc.enclosingBottom();
						// bad, ugly forms
						//break;
					}
				}
			}
			
			if( osc.interactive )
			{
				(new RectDebugger(rects, 400, 400)).Show();
				try {
					System.in.read();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
		for( Rect r : rects )
		{
			r.position.x = (float) Math.round(r.position.x / osc.resolution);
			r.position.y = (float) Math.round(r.position.y / osc.resolution);
			r.size.x /= osc.resolution;
			r.size.y /= osc.resolution;
		}
		
		return rects;
	}

	private float nonZeroRnd(int min, int max) {
		int rnd = 0;
		while( rnd == 0 )
		{
			rnd = RNG.rng.nextInt(min, max);
		}
		return rnd;
	}

	private void repulse(Rect r1, Vec2 push, float separationParameter ) {
		float mod = push.mod();
		mod = Math.max(1, mod);
		push.nor();
		push.scale( separationParameter / Math.max(push.mod(), 1) );
		
		if( Math.abs(push.x) < 1 )
		{
			push.x = Math.signum(push.x);
		}
		
		if( Math.abs(push.y) < 1 )
		{
			push.y = Math.signum(push.y);
		}
		
		r1.position.x += push.x;
		r1.position.y += push.y;
	}
}
