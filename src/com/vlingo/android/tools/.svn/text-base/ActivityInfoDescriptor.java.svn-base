package com.vlingo.android.tools;

import java.util.Formatter;
import java.util.HashMap;
import java.util.Map;

import android.content.pm.ActivityInfo;
import android.view.WindowManager;

/**
 * This is a helper class that will convert an ActivityInfo instance into a
 * human-readable text format.
 * 
 * @author J Carter
 */
public class ActivityInfoDescriptor 
{
	static public ActivityInfoDescriptor describe(ActivityInfo _activity)
	{
		return new ActivityInfoDescriptor(_activity);
	}
	
	public ActivityInfoDescriptor(ActivityInfo _activity)
	{
		activity = _activity;
	}
	
	public String name()				{ return activity == null ? null : activity.name; }
	public int    theme()				{ return activity == null ? null : activity.theme; }
	public String targetActivity()		{ return activity == null ? null : activity.targetActivity; }
	public String processName()			{ return activity == null ? null : activity.processName; }
	public String configChanges()		{ return activity == null ? null : hex(activity.configChanges); }
	public String flags()				{ return activity == null ? null : hex(activity.flags); }
	public String launchMode()			{ return activity == null ? null : launchModes().get(activity.launchMode); }
	public String permission()			{ return activity == null ? null : activity.permission; }
	public String taskAffinity()		{ return activity == null ? null : activity.taskAffinity; }
	public String screenOrientation()	{ return activity == null ? null : orientations().get( activity.screenOrientation ); }
	
	public String softInputMode()		
	{ 
		String softInputMode = null;
		
		if ( activity != null )
		{
			StringBuffer value = new StringBuffer();
			
			int vis = activity.softInputMode & 0x0F;
			int adj = activity.softInputMode & 0xF0;
			
			String visibility = inputVisibility().get(vis);
			String adjustment = inputAdjustments().get(adj);
			
			value
				.append( (visibility == null ? "<unknown>" : visibility ) )
				.append( " " )
				.append(paran(hex2(vis)))
				.append( " / " )
				.append( (adjustment == null ? "<unknown>" : adjustment ) )
				.append( " " )
				.append( paran(hex2(vis)))
				;
			
			softInputMode = value.toString();
		}
		
		return softInputMode; 
	}

	// Instance variables
	
	private ActivityInfo activity;
	
	// Implementation support
	
	static private String paran(String _string)
	{
		return "(" + _string + ")";
	}
	
	static private String hex(Integer _value)
	{
		return new Formatter().format("0x%08X",_value).toString();
	}
	
	static private String hex2(Integer _value)
	{
		return new Formatter().format("0x%02X",_value).toString();
	}
	
	static private Map<Integer,String> orientations()
	{
		if ( orientations == null )
		{
			orientations = new HashMap<Integer,String>();
			
			orientations.put(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED,"SCREEN_ORIENTATION_UNSPECIFIED");
			orientations.put(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE,"SCREEN_ORIENTATION_LANDSCAPE");
			orientations.put(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT,"SCREEN_ORIENTATION_PORTRAIT");
			orientations.put(ActivityInfo.SCREEN_ORIENTATION_USER,"SCREEN_ORIENTATION_USER");
			orientations.put(ActivityInfo.SCREEN_ORIENTATION_BEHIND,"SCREEN_ORIENTATION_BEHIND");
			orientations.put(ActivityInfo.SCREEN_ORIENTATION_SENSOR,"SCREEN_ORIENTATION_SENSOR");
			orientations.put(ActivityInfo.SCREEN_ORIENTATION_NOSENSOR,"SCREEN_ORIENTATION_NOSENSOR");
			orientations.put(ActivityInfo.SCREEN_ORIENTATION_SENSOR_LANDSCAPE,"SCREEN_ORIENTATION_SENSOR_LANDSCAPE");
			orientations.put(ActivityInfo.SCREEN_ORIENTATION_SENSOR_PORTRAIT,"SCREEN_ORIENTATION_SENSOR_PORTRAIT");
			orientations.put(ActivityInfo.SCREEN_ORIENTATION_REVERSE_LANDSCAPE,"SCREEN_ORIENTATION_REVERSE_LANDSCAPE");
			orientations.put(ActivityInfo.SCREEN_ORIENTATION_REVERSE_PORTRAIT,"SCREEN_ORIENTATION_REVERSE_PORTRAIT");
			orientations.put(ActivityInfo.SCREEN_ORIENTATION_FULL_SENSOR,"SCREEN_ORIENTATION_FULL_SENSOR");								
		}
		
		return orientations;
	}
	
	static private Map<Integer,String> orientations;
	
	static private Map<Integer,String> launchModes()
	{
		if ( launchModes == null )
		{
			launchModes = new HashMap<Integer,String>();
			
			launchModes.put(ActivityInfo.LAUNCH_MULTIPLE,"LAUNCH_MULTIPLE");
			launchModes.put(ActivityInfo.LAUNCH_SINGLE_TOP,"LAUNCH_SINGLE_TOP");
			launchModes.put(ActivityInfo.LAUNCH_SINGLE_TASK,"LAUNCH_SINGLE_TASK");
			launchModes.put(ActivityInfo.LAUNCH_SINGLE_INSTANCE,"LAUNCH_SINGLE_INSTANCE");			
		}
		
		return launchModes;
	}
	
	static private Map<Integer,String> launchModes;
	
	static private Map<Integer,String> inputVisibility()
	{
		if ( inputVisibility == null )
		{
			inputVisibility = new HashMap<Integer,String>();
			
			inputVisibility.put(WindowManager.LayoutParams.SOFT_INPUT_STATE_UNSPECIFIED,"SOFT_INPUT_STATE_UNSPECIFIED");
			inputVisibility.put(WindowManager.LayoutParams.SOFT_INPUT_STATE_UNCHANGED,"SOFT_INPUT_STATE_UNCHANGED");
			inputVisibility.put(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN,"SOFT_INPUT_STATE_HIDDEN");
			inputVisibility.put(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE,"SOFT_INPUT_STATE_ALWAYS_VISIBLE");
			inputVisibility.put(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE,"SOFT_INPUT_STATE_VISIBLE");
		}
		
		return inputVisibility;
	}
	
	static private Map<Integer,String> inputAdjustments()
	{
		if ( inputAdjustments == null )
		{
			inputAdjustments = new HashMap<Integer,String>();
			
			inputAdjustments.put(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_UNSPECIFIED, "SOFT_INPUT_ADJUST_UNSPECIFIED");
			inputAdjustments.put(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE, "SOFT_INPUT_ADJUST_RESIZE");
			inputAdjustments.put(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN, "SOFT_INPUT_ADJUST_PAN");
		}
		
		return inputAdjustments;
	}
	
	static private Map<Integer,String> inputVisibility;
	static private Map<Integer,String> inputAdjustments;
}
