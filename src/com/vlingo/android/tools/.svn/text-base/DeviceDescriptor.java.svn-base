package com.vlingo.android.tools;

import java.util.HashSet;
import java.util.Set;

import android.content.Context;

/**
 * This is a helper class that can allow the app to determine what should be included on
 * a description of the device.  Formatters will query an instance of this class to
 * determine what to format for output, and what to exclude.
 * 
 * @author J Carter
 */
public class DeviceDescriptor 
{
	static public DeviceDescriptor create(Context _context)
	{
		return new DeviceDescriptor(_context);
	}
	
	public DeviceDescriptor(Context _context)
	{
		context = _context;
	}
	
	public DeviceDescriptor include( String _property )
	{
		inclusions().add(_property);
		return this;
	}
	
	public boolean included( String _property )
	{
		return inclusions().contains(_property);
	}
	
	public DeviceDescriptor format(Format.Type _format)
	{
		format = _format;
		return this;
	}
	
	public String toString(Format.Type _format)
	{
		return Format.formatFor(_format).format(this);
	}
	
	public String toString()
	{
		return toString(format());
	}
	
	public Context context()
	{
		return context;
	}
	
	public Format.Type format()
	{
		return format;
	}
	
	private Set<String> inclusions()
	{
		return inclusions;
	}
	
	private Set<String> inclusions = new HashSet<String>();
	private Format.Type format;
	private Context context;
}
