package com.vlingo.android.tools.explorer;

import java.util.Map;

import com.vlingo.android.tools.ActivityInfoDescriptor;
import com.vlingo.android.tools.Device;
import com.vlingo.android.tools.Log;

import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

/**
 * This class provides a list displaying the details of a particular app which is installed on the phone.
 * Callers of this Activity must provide a canonical name for the app as "app.name" in the Intent.
 * 
 * @author J Carter
 */
public class AppInfoActivity extends ListWithSubtitlesActivity 
{
    @Override
    public void onCreate(Bundle savedInstanceState) 
    {
        Log.i("showing details for '" + (app = getIntent().getExtras().getString("app.name")) + "'");		
        super.onCreate(savedInstanceState);
    }
    
	@Override
	protected void onListItemClick(ListView _list, View _view, int _position, long _id) 
	{
		@SuppressWarnings("unchecked")
		Map<String,Object> item = (Map<String,Object>) getListAdapter().getItem(_position);		
		Log.d("selected item #" + _position + " ('" + item + "')");
		
		if ( "Translations".equals(item.get(FIELD)) )
			startActivity( new Intent(this,AppTranslationsActivity.class).putExtra("app.name", app) );
		else if ( "Flags".equals(item.get(FIELD)) )
			startActivity( new Intent(this,ListFlagsActivity.class).putExtra("app.flags", info.activityInfo.flags ) );
	}		

	@Override
	protected Contents info()
    {
    	android.util.Log.d("DeviceExplorer","getting app info for '" + app + "'");

    	info = Device.app(this,app);
    	
    	Contents fields = new Contents();

    	fields.add( newEntry( "Canonical Name", noneIfNull(app) ));
    	fields.add( newEntry( "Package Name", noneIfNull(info.resolvePackageName) ));
    	fields.add( newEntry( "Label", noneIfNull(info.nonLocalizedLabel) ));
    	fields.add( newEntry( "Default", Boolean.toString(info.isDefault) ));
//    	fields.add( newEntry( "Icon ID", info.icon ));
//    	fields.add( newEntry( "Label ID", info.labelRes ));
//    	fields.add( newEntry( "Match Affinity", info.match ));
//    	fields.add( newEntry( "Order Preference", info.preferredOrder ));
//    	fields.add( newEntry( "Match Priority", info.priority ));
//    	fields.add( newEntry( "Specific Result Index", info.specificIndex ));
    	
    	// Intent info
    	if ( info.filter != null ) 
    	{
    		
    	}
    	
    	// Activity info
    	if ( info.activityInfo != null )
    	{
    		ActivityInfoDescriptor activity = ActivityInfoDescriptor.describe(info.activityInfo);
    		
    		fields.add( newEntry( "Public Name", noneIfNull(activity.name()) ));
    		fields.add( newEntry( "Activity Class", or(activity.targetActivity(),"<same>") ));
    		fields.add( newEntry( "Process", noneIfNull(activity.processName()) ));
    		fields.add( newEntry( "Allowable Config Changes", noneIfNull(activity.configChanges()) ));
    		fields.add( newEntry( "Flags", noneIfNull(activity.flags()) ));
    		fields.add( newEntry( "Launch Mode", or(activity.launchMode(),"<unknown>") ));
    		fields.add( newEntry( "Permission", noneIfNull(activity.permission()) ));
    		fields.add( newEntry( "Screen Orientation", noneIfNull(activity.screenOrientation()) ));
    		fields.add( newEntry( "Soft Input Mode", noneIfNull(activity.softInputMode()) ));
    		fields.add( newEntry( "Task Affinity", noneIfNull(activity.taskAffinity()) ));
    		fields.add( newEntry( "Theme ID", Integer.toString(activity.theme()) ));
//    		fields.add( newEntry( "Logo ID", activity.logo ));
    	}
    	
    	// Application info
    	if ( info.activityInfo.applicationInfo != null )
    	{
    		ApplicationInfo application = info.activityInfo.applicationInfo;
    		
    		fields.add( newEntry( "Application Class", noneIfNull(application.className) ));
    		fields.add( newEntry( "Application Process", noneIfNull(application.processName) ));
    		fields.add( newEntry( "Target SDK", Integer.toString(application.targetSdkVersion) ));
    	}
    	
    	// Service info
    	if ( info.serviceInfo != null )
    	{
    	}
    	
    	fields.add( newEntry( "Translations", "for supported languages" ) );
    	
    	return fields;
    }
    
	private ResolveInfo info;
    private String app;
}
