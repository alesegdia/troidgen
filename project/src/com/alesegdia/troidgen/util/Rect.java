package com.alesegdia.troidgen.util;

import com.alesegdia.troidgen.room.Direction;
import com.alesegdia.troidgen.room.LinkPair;
import com.alesegdia.troidgen.room.Room;

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
	
	public boolean collideWith( Rect other )
	{
		float ax1, ay1, ax2, ay2, bx1, by1, bx2, by2;
		ax1 = this.position.x;
		ay1 = this.position.y;
		ax2 = this.position.x + this.size.x;
		ay2 = this.position.y + this.size.y;
		bx1 = other.position.x;
		by1 = other.position.y;
		bx2 = other.position.x + other.size.x;
		by2 = other.position.y + other.size.y;
		return ax1 < bx2 && ax2 > bx1 && ay1 < by2 && ay2 > by1;
	}
	
	public boolean isTouching( Rect other )
	{
		int ax1, ay1, ax2, ay2;
		ax1 = (int) this.position.x;
		ay1 = (int) this.position.y;
		ax2 = (int) (this.position.x + this.size.x);
		ay2 = (int) (this.position.y + this.size.y);
		
		int bx1, by1, bx2, by2;
		bx1 = (int) other.position.x;
		by1 = (int) other.position.y;
		bx2 = (int) (other.position.x + other.size.x);
		by2 = (int) (other.position.y + other.size.y);
		
		boolean tl = ax1 == bx2 && ay1 == by2;
		boolean br = ax2 == bx1 && ay2 == by1;

		boolean bl = ax1 == bx2 && ay2 == by1;
		boolean tr = ax2 == bx1 && ay1 == by2;
		
		return !( bx1 - ax2 > 0
			   || ax1 - bx2 > 0
			   || by1 - ay2 > 0
			   || ay1 - by2 > 0 ) && !tl && !br && !bl && !tr;
	}
	
}
