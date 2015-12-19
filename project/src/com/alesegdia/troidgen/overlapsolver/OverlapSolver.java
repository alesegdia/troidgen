package com.alesegdia.troidgen.overlapsolver;

import java.io.IOException;
import java.util.List;

import com.alesegdia.troidgen.util.Rect;
import com.alesegdia.troidgen.util.Vec2;

public class OverlapSolver {

	public static final float SEPARATION_PARAMETER = 1f;
	
	public void solve( List<Rect> rects )
	{
		boolean overlap = true;
		while( overlap )
		{
			overlap = false;
			for( Rect r1 : rects )
			{
				Vec2 push = new Vec2(0,0);
				boolean thisOverlap = false;
				
				// compute possible push vector
				for( Rect r2 : rects )
				{
					if( r1 != r2 && r1.collideWith(r2) )
					{
						overlap = true;
						thisOverlap = true;
						Vec2 normal = r1.position.sub(r2.position);
						System.out.println("nor:" + normal);
						push.x += normal.x;
						push.y += normal.y;
					}
				}

				// if there was overlap, displace
				if( thisOverlap )
				{
					// can give random push if (0,0), left for testing
					if( push.x != 0 || push.y != 0 )
					{
						float mod = push.mod();
						if( mod == 0 ) mod = 1;
						push.nor();
						push.scale(SEPARATION_PARAMETER/mod);
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
			}
			
			(new RectDebugger(rects, 400, 400)).Show();
			try {
				System.in.read();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		for( Rect r : rects )
		{
			r.position.x = (float) Math.floor(r.position.x);
			r.position.y = (float) Math.floor(r.position.y);
		}
	}
}
