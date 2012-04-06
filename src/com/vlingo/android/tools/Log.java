package com.vlingo.android.tools;

/**
 * This provides a simple shell around the Android LogCat, so we will use 
 * a common TAG for all entries.
 * 
 * @author J Carter
 */
public class Log
{
	static final String TAG = "DeviceExplorer";
	
	static public void i(String _msg) { android.util.Log.i(TAG,_msg); }
	static public void d(String _msg) { android.util.Log.d(TAG,_msg); }	
}
