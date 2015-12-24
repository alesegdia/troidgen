package com.alesegdia.troidgen.util;

public class Vec2 {

	public Vec2(float f, float g) {
		Set(f,g);
	}

	public Vec2(Vec2 o) {
		this.x = o.x;
		this.y = o.y;
	}

	public void Set(float f, float g) {
		this.x = f;
		this.y = g;
	}
	
	public float x;
	public float y;
	
	public String toString()
	{
		return "(" + this.x + "," + this.y + ")";
	}

	public Vec2 Add(Vec2 globalPosition) {
		return new Vec2( this.x + globalPosition.x, this.y + globalPosition.y );
	}

	public Float distance(Vec2 other) {
		return (float) Math.sqrt( Math.pow(this.x - other.x,2) + Math.pow(this.y - other.y, 2) );
	}

	public void set(Vec2 other) {
		this.x = other.x;
		this.y = other.y;
	}

	public Vec2 sub(Vec2 other) {
		return new Vec2(this.x - other.x, this.y - other.y);
	}

	public float mod()
	{
		float k = this.x * this.x + this.y * this.y;
		return (float) Math.sqrt(k);
	}
	
	public void nor() {
		float mod = this.mod();
		assert mod != 0: "The vector must not be 0,0";
		this.x /= this.mod();
		this.y /= this.mod();
	}

	public void scale(float f) {
		this.x *= f;
		this.y *= f;
	}
	
	@Override
	public boolean equals( Object o )
	{
		assert(o instanceof Vec2);
		if( o == this ) return true;
		Vec2 other = ((Vec2)o);
		return this.x == other.x && this.y == other.y;
	}
	
	@Override
	public Vec2 clone()
	{
		return new Vec2(x, y);
	}
	
	@Override
	public int hashCode()
	{
		return (int) (x * y * 1000);
	}
	
}
