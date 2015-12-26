package com.alesegdia.troidgen.util;

import java.util.List;

public class Util {

	public static void shuffle(List l)
	{
		for( int i = 0; i < l.size(); i++ )
		{
			int i1 = RNG.rng.nextInt(l.size());
			int i2 = RNG.rng.nextInt(l.size());
			
			Object tmp = l.get(i1);
			l.set(i1, l.get(i2));
			l.set(i2, tmp);
		}
	}
	
}
