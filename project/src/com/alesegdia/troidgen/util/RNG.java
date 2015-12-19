package com.alesegdia.troidgen.util;

import java.util.Random;

public class RNG extends Random {

	public static RNG rng;
	
	public RNG()
	{
		super();
		rng = this;
	}
	
	public int nextInt( int min, int max )
	{
		return nextInt( (max - min) + 1 ) + min;
	}
	
}
