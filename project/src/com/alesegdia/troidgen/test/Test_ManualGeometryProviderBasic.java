package com.alesegdia.troidgen.test;

import com.alesegdia.troidgen.ManualGeometryProvider;
import com.alesegdia.troidgen.geom.Geometry;
import com.alesegdia.troidgen.util.RNG;

public class Test_ManualGeometryProviderBasic {

	public static void main( String[] args )
	{
		RNG.rng = new RNG();

		ManualGeometryProvider mgp = new ManualGeometryProvider();
		
		mgp.addGeometryModel(new Geometry(3, 3, null));
		mgp.addGeometryModel(new Geometry(1, 2, null));
		mgp.addGeometryModel(new Geometry(2, 1, null));
		mgp.addGeometryModel(new Geometry(1, 3, null));
		mgp.addGeometryModel(new Geometry(3, 3, null));
		mgp.addGeometryModel(new Geometry(1, 3, null));
		mgp.addGeometryModel(new Geometry(2, 1, null));
		mgp.addGeometryModel(new Geometry(3, 3, null));
		mgp.addGeometryModel(new Geometry(3, 3, null));
		
		mgp.debug();

	}

}
