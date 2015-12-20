package com.alesegdia.troidgen.restriction;

public class RestrictionSet {

	private boolean[] restrictions;
	
	public RestrictionSet ( int size, boolean... needed_constraints )
	{
		assert size == needed_constraints.length : "not enough parameter to fill all the requested constraints";
		restrictions = new boolean[size];
		for( int i = 0; i < needed_constraints.length; i++ )
		{
			restrictions[i] = needed_constraints[i];
		}
	}
	
	public boolean has( int index )
	{
		assert( index >= 0 && index < 8 );
		return restrictions[index];
	}

}
