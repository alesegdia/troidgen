package com.alesegdia.troidgen.test;

import com.alesegdia.troidgen.util.Rect;

public class Test_RectCollision {

	public static void main(String[] args)
	{
		Rect r1 = new Rect(0, 0, 2, 2);
		Rect r2 = new Rect(-1, -1, 2, 2);
		Rect r3 = new Rect(3, 3, 2, 2);
		Rect r4 = new Rect(2, 2, 2, 2);
		
		assert r1.collideWith(r2);
		assert !r1.collideWith(r3);
		assert !r1.collideWith(r4);
		
		assert r2.collideWith(r1);
		assert !r2.collideWith(r3);
		assert !r2.collideWith(r4);
		
		assert !r3.collideWith(r1);
		assert !r3.collideWith(r2);
		assert r3.collideWith(r4);
		
		assert !r4.collideWith(r1);
		assert !r4.collideWith(r2);
		assert r4.collideWith(r3);
	}
}
