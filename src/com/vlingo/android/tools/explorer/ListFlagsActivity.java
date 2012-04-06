package com.vlingo.android.tools.explorer;

import com.vlingo.android.tools.*;

/**
 * This class provides a list of flags which are set in the "app.flags" extra
 * parameter which is passed in.
 * 
 * @author J Carter
 */
public class ListFlagsActivity extends ListWithSubtitlesActivity 
{
	@Override
	protected Contents info() 
	{
		int flags = getIntent().getExtras().getInt("app.flags");
		
		Contents info = new Contents();
		
		for ( Flag flag : Device.App.matchingFlags(flags) )
			info.add( newEntry( flag.title(), flag.hexValue() ) );
		
		return info;
	}
}
