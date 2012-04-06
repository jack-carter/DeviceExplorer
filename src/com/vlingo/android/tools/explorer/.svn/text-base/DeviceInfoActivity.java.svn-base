package com.vlingo.android.tools.explorer;

import java.util.*;
import com.vlingo.android.tools.Device;

/**
 * This class provides a list of characteristics about the device on which DeviceExplorer is running.
 * 
 * @author J Carter
 */
public class DeviceInfoActivity extends ListWithSubtitlesActivity 
{
	@Override
	protected void onEmail()
	{
		send("Device Explorer: Device Info","",save());
	}
	
	@Override
	protected String save()
	{
		StringBuilder sb = new StringBuilder();
		for ( Map<String,String> entry : info() )
			sb.append(entry.get(FIELD)).append(": ").append(entry.get(VALUE)).append("\n");

		return saveToFile(filePath(),fileSpec("device_info"),sb.toString());
	}	

	@Override
	protected Contents info()
    {
    	android.util.Log.d("DeviceExplorer","getting device info ...");

    	Contents info = new Contents();

    	Iterator<Map.Entry<String,String>> entries = Device.info().entrySet().iterator();
    	while (entries.hasNext()) {
    		Map.Entry<String,String> entry = entries.next();
    		info.add( newEntry( entry.getKey(), entry.getValue() ) );
    	}
    	
    	return info;
    }    
}
