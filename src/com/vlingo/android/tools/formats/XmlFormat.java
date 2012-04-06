package com.vlingo.android.tools.formats;

import java.net.URLEncoder;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;

import com.vlingo.android.tools.Device;
import com.vlingo.android.tools.DeviceDescriptor;
import com.vlingo.android.tools.Format;

public class XmlFormat extends Format 
{
	@Override
	public String format( DeviceDescriptor _desc )
	{
		StringBuilder xml = new StringBuilder();
		
		xml.append("<device>").append("\n");
		
		if ( _desc.included("device.info") )
		{
			xml.append("\n");
			xml.append("<info>").append("\n");
			xml.append( info(_desc) );
			xml.append("</info>").append("\n");			
			xml.append("\n");
		}
		
		if ( _desc.included("device.languages") )
		{
			xml.append("\n");
			xml.append("<languages>").append("\n");
			xml.append( languages(_desc) );
			xml.append("</languages>").append("\n");			
			xml.append("\n");
		}
		
		if ( _desc.included("device.apps") )
		{
			xml.append("\n");
			xml.append("<apps>").append("\n");
			xml.append( apps(_desc) );
			xml.append("</apps>").append("\n");			
			xml.append("\n");
		}
		
		xml.append("</device>");
		
		return xml.toString();
	}
	
	private String info( DeviceDescriptor _desc )
	{
		StringBuilder xml = new StringBuilder();

		Device.Info info = Device.info();
		
		Iterator<Map.Entry<String,String>> items = info.entrySet().iterator();
		while (items.hasNext()) {
			Map.Entry<String,String> item = items.next();
			xml
			.append("<item name=\"").append(item.getKey()).append("\">")
			.append(item.getValue())
			.append("</item>")
			.append("\n");
		}
			
		return xml.toString();
	}
	
	private String languages( DeviceDescriptor _desc )
	{
		StringBuilder xml = new StringBuilder();
		
		for ( String language : Device.languages() ) {
			xml
			.append("<language>")
			.append(language)
			.append("</language>")
			.append("\n");
		}
			
		return xml.toString();
	}
	
	private String apps( DeviceDescriptor _desc )
	{
		StringBuilder xml = new StringBuilder();
		
		Iterator<Device.App> apps = Device.getAppTranslations(_desc.context(),Device.supportedLocales()).iterator();
		
		while (apps.hasNext()) 
		{
			Device.App app = apps.next();
			
			xml
			.append("<app")
			.append(" name=\"").append( encode(app.name) ).append("\"")
			.append(" package=\"").append(app.pkg).append("\"")
			.append(" activity=\"").append(app.activity).append("\"")
			.append(">")
			.append("\n");
			
			Iterator<Map.Entry<Locale,String>> names = app.translations.entrySet().iterator();
			
			while (names.hasNext()) 
			{
				Map.Entry<Locale,String> name = names.next();
				
				xml
				.append("<translation")
				.append(" locale=\"").append(name.getKey()).append("\"")
				.append(">")
				.append(name.getValue())
				.append("</translation>")
				.append("\n");
			}
			
			xml
			.append("</app>")
			.append("\n");
		}
		
		return xml.toString();
	}
	
	private String encode( String _string )
	{
		return URLEncoder.encode(_string);
	}
}
