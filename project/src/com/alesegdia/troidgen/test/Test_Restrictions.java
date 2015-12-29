package com.alesegdia.troidgen.test;

import java.util.ArrayList;
import java.util.List;

import com.alesegdia.troidgen.restriction.RestrictionSet;

public class Test_Restrictions {

	public static void main( String[] args )
	{
		List<RestrictionSet> restrictions = new ArrayList<RestrictionSet>();
		restrictions.add(new RestrictionSet(4, false, false, false, false));
		restrictions.add(new RestrictionSet(4, true, false, false, false));
		restrictions.add(new RestrictionSet(4, false, true, false, false));
		restrictions.add(new RestrictionSet(4, true, true, false, false));
		restrictions.add(new RestrictionSet(4, false, false, true, false));
		restrictions.add(new RestrictionSet(4, true, false, true, false));
		restrictions.add(new RestrictionSet(4, false, true, true, false));
		restrictions.add(new RestrictionSet(4, true, true, true, false));
		
		restrictions.add(new RestrictionSet(4, false, false, false, false));
		restrictions.add(new RestrictionSet(4, true, false, false, false));
		restrictions.add(new RestrictionSet(4, false, true, false, false));
		restrictions.add(new RestrictionSet(4, true, true, false, false));
		restrictions.add(new RestrictionSet(4, false, false, true, false));
		restrictions.add(new RestrictionSet(4, true, false, true, false));
		restrictions.add(new RestrictionSet(4, false, true, true, false));
		restrictions.add(new RestrictionSet(4, true, true, true, false));
		
		for( int i = 0; i < 16; i++ )
		{
			RestrictionSet baseRestriction = buildRestrictionFromNumber(i);
			System.out.println("br: " + baseRestriction);
			for( int j = 0; j < 16; j++ )
			{
				RestrictionSet testRestriction = buildRestrictionFromNumber(j);
				System.out.println("\t(" + j + ")tr: " + testRestriction);

				if( ((i & j)) == j )
				{
					assert(testRestriction.resolves(baseRestriction));
				}
				else
				{
					assert(!testRestriction.resolves(baseRestriction));
				}
			}
		}		
	}
	
	private static RestrictionSet buildRestrictionFromNumber( int n )
	{
		return new RestrictionSet( 4, (n & (1 << 0)) != 0, (n & (1 << 1)) != 0, (n & (1 << 2)) != 0, (n & (1 << 3)) != 0 );
	}
}
