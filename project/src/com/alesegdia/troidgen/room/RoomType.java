package com.alesegdia.troidgen.room;

public enum RoomType {

	PASSAGE, ABILITY, COMMON;
	
	public static RoomType FromString(String s)
	{
		switch(s)
		{
		case "common": return COMMON;
		case "passage": return PASSAGE;
		case "ability": return ABILITY;
		}
		return COMMON;
	}
	
}
