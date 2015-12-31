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
	
	public RestrictionSet( int size )
	{
		restrictions = new boolean[size];
	}
	
	public boolean has( int index )
	{
		assert( index >= 0 && index < 8 );
		return restrictions[index];
	}

	public boolean resolves(RestrictionSet target) {
		for( int i = 0; i < restrictions.length; i++ )
		{
			if( this.has(i) && !target.has(i) )
			{
				return false;
			}
		}
		
		return true;
	}
	
	@Override
	public String toString()
	{
		String s = "";
		for( int i = 0; i < restrictions.length; i++ )
		{
			if( has(i) )
			{
				s += "1";
			}
			else
			{
				s += "0";
			}
		}
		return s;
	}

	public void set(int i, boolean b) {
		restrictions[i] = b;
	}

	public static RestrictionSet FromString(String constraintStr) {
		RestrictionSet rs = new RestrictionSet(constraintStr.length());
		for( int i = 0; i < constraintStr.length(); i++ )
		{
			char bit = constraintStr.charAt(i);
			if( bit == '1' )
			{
				rs.set(i, true);
			}
			else if( bit == '0' )
			{
				rs.set(i, false);
			}
			else
			{
				assert(false);
			}
		}
		return rs;
	}

}
