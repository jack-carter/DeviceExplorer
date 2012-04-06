package com.vlingo.android.tools.explorer;

import android.content.Intent;
import android.view.View;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.vlingo.android.tools.*;

/**
 * This class provides a main menu in the form of a list selection.
 * 
 * @author J Carter
 */
public class MenuListActivity extends ExplorerListActivity
{
	@Override
	protected ListAdapter adapter()
	{
		return standardArrayAdapter(options());
	}
	
	@Override
	protected void onListItemClick(ListView _list, View _view, int _position, long _id) 
	{
		MenuOption item = (MenuOption) getListAdapter().getItem(_position);
		startActivity( new Intent(this,item.activity) );
	}
	
	@Override
	protected void onEmail()
	{
		send("Device Explorer: All Device Info","",save());
	}
	
	@Override
	protected String save()
	{
		Log.d("MenuListActivity.save()");
		return saveToFile(
				filePath(),
				fileSpec("device_all"),
				DeviceDescriptor.create(this)
					.include("device.info")
					.include("device.languages")
					.include("device.apps")
					.toString(Format.Type.XML)
				);
	}
	
	static private MenuOption[] options()
	{
		return new MenuOption[] 
		{ 
		new MenuOption("Device Info",DeviceInfoActivity.class),
		new MenuOption("Installed Apps",ListAppsActivity.class),
		new MenuOption("Supported Languages",ListLanguagesActivity.class)
		};
	}
	
	static private class MenuOption
	{
		public MenuOption(String _title, Class<?> _activity)
		{
			title = _title;
			activity = _activity;
		}
		
		public String toString()
		{
			return title;
		}
		
		public String title;
		public Class<?> activity;
	};
}
