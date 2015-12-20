package com.alesegdia.troidgen.restriction;

public class RestrictionSetFactory {

	public static final int NUM_RESTRICTIONS = 4 ;
	
	public static RestrictionSet makeRS_0000()
	{
		return new RestrictionSet(NUM_RESTRICTIONS, false, false, false, false);
	}
	
	public static RestrictionSet makeRS_1000()
	{
		return new RestrictionSet(NUM_RESTRICTIONS, true, false, false, false);
	}
	
	public static RestrictionSet makeRS_0100()
	{
		return new RestrictionSet(NUM_RESTRICTIONS, false, true, false, false);
	}
	
	public static RestrictionSet makeRS_1010()
	{
		return new RestrictionSet(NUM_RESTRICTIONS, true, false, true, false);
	}
	
}
