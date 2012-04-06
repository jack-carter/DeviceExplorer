package com.vlingo.android.tools.explorer;

import android.widget.ListAdapter;

import com.vlingo.android.tools.Device;

/**
 * This class provides a simple list of Locales which are available on the device.  The list
 * of locales provided by this Activity is NOT the same as you will see in the Settings app.
 *
 * @author J Carter
 */
public class ListLanguagesActivity extends ExplorerListActivity 
{
	@Override
	protected ListAdapter adapter()
	{
		return standardArrayAdapter(contents());
	}

	protected String[] contents()
    {    	
    	return Device.languages();
    }	

	@Override
	protected void onEmail()
	{
		send("Device Explorer: Languages List","",save());
	}
	
	@Override
	protected String save()
	{
		StringBuilder sb = new StringBuilder();
		for ( String line : contents() )
			sb.append(line).append("\n");

		return saveToFile(filePath(),fileSpec("device_languages"),sb.toString());
	}	
}
