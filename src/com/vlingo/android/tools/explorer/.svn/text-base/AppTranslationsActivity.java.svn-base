package com.vlingo.android.tools.explorer;

import java.util.Iterator;
import java.util.Locale;
import java.util.Map;

import android.os.Bundle;

import com.vlingo.android.tools.Device;
import com.vlingo.android.tools.Log;

/**
 * This class provides a list of language translations for a particular app which is installed on the device.
 * Callers of this Activity must provide the canonical name of the app as the Intent extra entitled "app.name".
 * 
 * @author J Carter
 */
public class AppTranslationsActivity extends ListWithSubtitlesActivity 
{
    @Override
    public void onCreate(Bundle savedInstanceState) 
    {
        Log.i("showing details for '" + (app = getIntent().getExtras().getString("app.name")) + "'");		
        super.onCreate(savedInstanceState);
    }
    
	protected Contents info()
	{
		Contents list = new Contents();
		
		Iterator<Map.Entry<Locale,String>> translations = Device.getAppTranslations(this,app).entrySet().iterator();

		while ( translations.hasNext() ) {
			Map.Entry<Locale,String> translation = translations.next();
			list.add( newEntry( translation.getValue(), translation.getKey().getDisplayName() ) );
		}
		
		return list;
	}
	
	private String app;
}
