package com.vlingo.android.tools;

import java.util.HashMap;
import java.util.Map;

import com.vlingo.android.tools.formats.CsvFormat;
import com.vlingo.android.tools.formats.PlainTextFormat;
import com.vlingo.android.tools.formats.XmlFormat;

/**
 * Since this app provides a variety of output formats, we encapsulate the formatters
 * in this helper class to ease the use of each.
 * 
 * @author J Carter
 */
public abstract class Format
{
	public enum Type { PLAIN_TEXT, CSV_WITH_HEADER, XML };
	
	static public Format formatFor( Format.Type _format )
	{
		Format format = supportedFormats().get(_format);
		return format == null ? supportedFormats().get(Type.XML) : format;
	}
	
	public abstract String format( DeviceDescriptor _desc );
	
	static public Map<Format.Type,Format> supportedFormats()
	{
		HashMap<Format.Type,Format> formats = new HashMap<Format.Type,Format>();
		
		formats.put( Type.PLAIN_TEXT, new PlainTextFormat() );
		formats.put( Type.CSV_WITH_HEADER, new CsvFormat(CsvFormat.WITH_HEADER) );
		formats.put( Type.XML, new XmlFormat() );
		
		return formats;
	}
}
