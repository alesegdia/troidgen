package com.alesegdia.troidgen.util;

public class Rect {
	
	public Vec2 position = new Vec2(0,0);
	public Vec2 size = new Vec2(0,0);
	
	public Rect (float x, float y, float w, float h) {
		this.position.Set(x,y);
		this.size.Set(w,h);
	}
	
	public Rect(Rect r) {
		this.position.set(r.position);
		this.size.set(r.size);
	}

	@Override
	public String toString() {
		return "[" + position.x + "," + position.y + " - " + size.x + "," + size.y + "]";
	}
	
	public float x1()
	{
		return this.position.x;
	}
	
	public float y1()
	{
		return this.position.y;
	}
	
	public float x2()
	{
		return this.position.x + this.size.x;
	}
	
	public float y2()
	{
		return this.position.y + this.size.y;
	}
	
	public boolean collideWith( Rect other )
	{
		return x1() < other.x2() && x2() > other.x1() && y1() < other.y2() && y2() > other.y1();
	}
	
	public boolean isTouching( Rect other )
	{
		boolean tl = x1() == other.x2() && y1() == other.y2();
		boolean br = x2() == other.x1() && y2() == other.y1();

		boolean bl = x1() == other.x2() && y2() == other.y1();
		boolean tr = x2() == other.x1() && y1() == other.y2();
		
		return !( other.x1() - x2() > 0
			   || x1() - other.x2() > 0
			   || other.y1() - y2() > 0
			   || y1() - other.y2() > 0 ) && !tl && !br && !bl && !tr;
	}
	
}
