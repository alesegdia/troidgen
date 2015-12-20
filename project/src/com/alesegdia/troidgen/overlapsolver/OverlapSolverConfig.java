package com.alesegdia.troidgen.overlapsolver;

import com.alesegdia.troidgen.util.Rect;

public class OverlapSolverConfig
{
	public float separationParameter = 1f;
	public int resolution = 64;
	public boolean interactive = false;
	public boolean enableRandomDisplacement = true;
	public boolean enableRandomPushIfZero = true;
	public boolean enableTweakNearSeparation = true;
	public Rect enclosingRect = new Rect( -400, -400, 800, 800 );

	public float enclosingLeft() {
		return enclosingRect.position.x;
	}
	
	public float enclosingRight() {
		return enclosingRect.position.x + enclosingRect.size.x;
	}
	
	public float enclosingTop() {
		return enclosingRect.position.y;
	}
	
	public float enclosingBottom() {
		return enclosingRect.position.y + enclosingRect.size.y;
	}
	
	
	
}