package com.alesegdia.troidgen.test;

import com.alesegdia.troidgen.util.Rect;

public class Test_RectTouching {

	public static void main( String[] args )
	{
		Rect rr = new Rect(0,0,3,3);
		
		Rect r1 = new Rect(-3,3,3,3);
		Rect r2 = new Rect(3,3,3,3);
		
		Rect r3 = new Rect(-3,-3,3,3);
		Rect r4 = new Rect(3,-3,3,3);

		System.out.println("TL: " + rr.isTouching(r1));
		System.out.println("BR: " + rr.isTouching(r2));
		System.out.println("BL: " + rr.isTouching(r3));
		System.out.println("TR: " + rr.isTouching(r4));
		
		Rect r5 = new Rect(-3, 0, 3, 3);
		System.out.println("r5 touching: " + rr.isTouching(r5));
		System.out.println("r5 collidin: " + rr.collideWith(r5));
		
	}
	
}
