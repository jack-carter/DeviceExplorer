package com.vlingo.android.tools.explorer;

import java.util.ArrayList;
import java.util.HashMap;

import android.widget.ListAdapter;
import android.widget.SimpleAdapter;

/**
 * This class provides a common base for Activities that wish to show a list of items with a title
 * and sub-title.  It provides a standard SimpleAdapter, using built-in Android resources to display
 * the list.  Sub-classes must implement the info() method to populate the list with contents.
 * 
 * @author J Carter
 */
public abstract class ListWithSubtitlesActivity extends ExplorerListActivity 
{
	@Override
	protected ListAdapter adapter()
	{
    	return new SimpleAdapter(this,info(),android.R.layout.simple_list_item_2,new String[] { FIELD, VALUE },new int[]{ android.R.id.text1, android.R.id.text2 });
	}

	protected abstract Contents info();

	protected static final String FIELD = "FIELD";
	protected static final String VALUE = "VALUE";
	
	static public class FieldEntry extends HashMap<String,String> 
	{
		private static final long serialVersionUID = 1L;	
	};
	
	static public class Contents extends ArrayList<FieldEntry> 
	{
		private static final long serialVersionUID = 1L;		
	};
	
    static protected FieldEntry newEntry(String _field, String _value)
    {
    	FieldEntry map = new FieldEntry();
    	
    	map.put(FIELD, _field);
    	map.put(VALUE, _value);
    	
    	return map;
    }

    protected String noneIfNull(CharSequence _field)
    {
    	return or( _field == null ? null : _field.toString(),"<none>");
    }
    
    protected String noneIfNull(String _field)
    {
    	return or(_field,"<none>");
    }
    
    protected String or(String _field, String _alt)
    {
    	return _field == null ? _alt : _field;
    }    
}
