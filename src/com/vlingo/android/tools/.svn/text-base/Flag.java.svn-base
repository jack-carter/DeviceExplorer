package com.vlingo.android.tools;

import java.util.ArrayList;
import java.util.Formatter;
import java.util.List;

import android.content.pm.ActivityInfo;

public class Flag 
{
	public Flag(int _value, String _title)
	{
		value(_value);
		title = _title;
	}
	
	public String title()    { return title; }
	public String hexValue() { return hexValue; }
	public int value()       { return value; }
	
	public Flag value(int _value)
	{
		value = _value;
		hexValue = hex(_value);
		
		return this;
	}
	
	public boolean isSet(int _flags)
	{
		return (value & _flags) > 0;
	}
	
	static public Flag[] matchingFlags(int _flags)
	{
		List<Flag> flags = new ArrayList<Flag>();
		
		for ( Flag flag : flags() )
			if ( flag.isSet(_flags) )
				flags.add(flag);
		
		return flags.toArray( new Flag[1] );
	}
	
	static private String hex(Integer _value)
	{
		return new Formatter().format("0x%08X",_value).toString();
	}

	static private Flag[] flags()
	{
		if ( flags == null )
		{
			List<Flag> list = new ArrayList<Flag>();
			
			list.add( new Flag(ActivityInfo.FLAG_MULTIPROCESS,					"FLAG_MULTIPROCESS") );
			list.add( new Flag(ActivityInfo.FLAG_FINISH_ON_TASK_LAUNCH,			"FLAG_FINISH_ON_TASK_LAUNCH") );
			list.add( new Flag(ActivityInfo.FLAG_CLEAR_TASK_ON_LAUNCH,			"FLAG_CLEAR_TASK_ON_LAUNCH") );
			list.add( new Flag(ActivityInfo.FLAG_ALWAYS_RETAIN_TASK_STATE,		"FLAG_ALWAYS_RETAIN_TASK_STATE") );
			list.add( new Flag(ActivityInfo.FLAG_STATE_NOT_NEEDED,				"FLAG_STATE_NOT_NEEDED") );
			list.add( new Flag(ActivityInfo.FLAG_EXCLUDE_FROM_RECENTS,			"FLAG_EXCLUDE_FROM_RECENTS") );
			list.add( new Flag(ActivityInfo.FLAG_ALLOW_TASK_REPARENTING,		"FLAG_ALLOW_TASK_REPARENTING") );
			list.add( new Flag(ActivityInfo.FLAG_NO_HISTORY,					"FLAG_NO_HISTORY") );
			list.add( new Flag(ActivityInfo.FLAG_FINISH_ON_CLOSE_SYSTEM_DIALOGS,"FLAG_FINISH_ON_CLOSE_SYSTEM_DIALOGS") );
			list.add( new Flag(0x00000200,										"FLAG_HARDWARE_ACCELERATED") );
			
			flags = list.toArray( new Flag[1] );
		}
		
		return flags;
	}
	
	static private Flag[] flags;
	
	private String title;
	private String hexValue;
	private int value;
}
