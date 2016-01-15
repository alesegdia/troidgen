package com.alesegdia.troidgen.util;

public class Lerper implements IInterpolator {
	private float min;
	private float max;

	public Lerper(float min, float max)
	{
		this.min = min;
		this.max = max;
	}

	@Override
	public float get( float p )
	{
		return this.min + ((this.max - this.min) * p);
	}
	
}