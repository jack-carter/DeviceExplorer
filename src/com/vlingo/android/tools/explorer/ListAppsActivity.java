package com.vlingo.android.tools.explorer;

import android.view.View;
import android.content.Intent;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.vlingo.android.tools.*;

/**
 * This class provides a simple list of applications, displaying their canonical names, and allowing
 * for selection of an app to show further details.
 * 
 * @author J Carter
 */
public class ListAppsActivity extends ExplorerListActivity
{
	@Override
	protected ListAdapter adapter()
	{
		return standardArrayAdapter(contents());
	}
	
    protected String[] contents() 
    {	
		return Device.apps(this);
    }
	
	@Override
	protected void onEmail()
	{
		send("Device Explorer: App List","",save());
	}
	
	@Override
    protected String save() 
    {
		return saveToFile(filePath(),fileSpec("device_apps"),Device.appTranslations(this));
    }	

	@Override
	protected void onListItemClick(ListView _list, View _view, int _position, long _id) 
	{
		String item = (String) getListAdapter().getItem(_position);		
		Log.d("selected item #" + _position + " ('" + item + "')");
		startActivity( new Intent(this,AppInfoActivity.class).putExtra("app.name", item) );
	}		
}
