package com.alesegdia.troidgen;

import java.util.List;

import com.alesegdia.troidgen.util.Rect;

public class MinSizeRoomGroupEvaluator implements IRoomGroupEvaluator {

	private int min;

	public MinSizeRoomGroupEvaluator( int min )
	{
		this.min = min;
	}
	
	@Override
	public boolean evaluate(List<Rect> group) {
		return group.size() > this.min;
	}

}
