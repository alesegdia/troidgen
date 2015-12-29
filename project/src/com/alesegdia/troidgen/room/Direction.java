package com.alesegdia.troidgen.room;

public enum Direction {
	TOP, DOWN, LEFT, RIGHT, NODIR;

	public static Direction getFromChar(char charAt) {
		switch(charAt)
		{
		case 'T': return TOP;
		case 'B': return DOWN;
		case 'L': return LEFT;
		case 'R': return RIGHT;
		}
		return NODIR;
	}
}
