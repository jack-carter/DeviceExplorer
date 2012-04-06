package com.vlingo.android.tools;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;

/**
 * This class is a typical Facade (i.e. GoF Patterns) for grabbing information from the
 * Android framework on the underlying device, and the apps which are installed on it.
 * This class also retrieves translations for apps.
 * 
 * @author J Carter
 */
public class Device
{
	/**
	 * A helper class to contain details about each app installed on the device.
	 * 
	 * @author J Carter
	 */
	static public class App 
	{
		public String name;
		public String pkg;
		public String activity;

		public App name( String _name ) { name = _name; return this; }
		public App pkg( String _package ) { pkg = _package; return this; }
		public App activity( String _activity ) { activity = _activity; return this; }

		public Map<Locale,String> translations = new HashMap<Locale,String>();
		
		public App addTranslation( Locale _locale, String _translation )
		{
			translations.put( _locale, _translation );
			return this;
		}
		
		static public Flag[] matchingFlags( int _flags )
		{
			return Flag.matchingFlags(_flags);
		}
	};
	
	/**
	 * A helper class to help keep me from having to type HashMap<String,String> all
	 * the damn time.
	 * 
	 * @author J Carter
	 */
	static public class Info extends HashMap<String,String> 
	{
		static final long serialVersionUID = 0L;
	};
	
	/**
	 * This class pulls information from the Build class within Android, and constructs of map of key/value pairs
	 * to make the information more accessible for UI views.
	 * 
	 * @return Map<String,String> of key/value pairs for information from the Build class of the Android framework
	 */
	static public Info info()
	{
		Log.d("Device.info()");
		
		Info info = new Info();
	
		info.put( "Manufacturer",Build.MANUFACTURER );
		info.put( "Brand",Build.BRAND );
		info.put( "Model",Build.MODEL );
		info.put( "Device",Build.DEVICE );
		info.put( "Serial#",Build.SERIAL );
		info.put( "Display",Build.DISPLAY );
		info.put( "Product",Build.PRODUCT );
		info.put( "ID",Build.ID );
		info.put( "Hardware",Build.HARDWARE );
		info.put( "Build",Build.FINGERPRINT );
		
		return info;
	}
	
	/**
	 * This method returns an array of locale names for this device.
	 * 
	 * @return String[] of locales which are available on this device
	 */
	static public String[] languages()
    {    	
		Log.d("Device.languages()");
		
    	Locale[] locales = Locale.getAvailableLocales();
    	Set<String> languages = new HashSet<String>();
    	
    	for ( Locale locale : locales )
    		languages.add( locale.getDisplayLanguage() );
    	
    	String[] list = languages.toArray( new String[1] );    	
    	Arrays.sort(list);
    	
    	return list;
    }
	
	/**
	 * This method scours the PackageManager to create a list of canonical names for the apps installed 
	 * on the device.
	 * 
	 * @param _context
	 * @return String[] of canonical names for apps installed on this device.
	 */
    static public String[] apps(Context _context) 
    {	
    	Log.d("Device.apps(Context)");
    	
    	PackageManager pm = _context.getPackageManager();

    	Intent intent = new Intent(Intent.ACTION_MAIN).addCategory(Intent.CATEGORY_LAUNCHER);    	
    	Iterator<ResolveInfo> applications = pm.queryIntentActivities(intent, 0).iterator();

    	List<String> apps = new ArrayList<String>();
    	
    	while(applications.hasNext()) 
    	{
    		ResolveInfo info = applications.next();

    		// Get display label. There are 5 cases we have to cover here:
    		// -Activity has a string resource label (activity's localized label is used)
    		// -Activity has a hardcoded string label (activity's nonlocalized label is used)
    		// -Activity has no label, application has string resource label (application's localized label is used)
    		// -Activity has no label, application has hardcoded string label (application's nonlocalized label is used)
    		// -Activity has no label, application has no label (activity name is used, ex: com.vlingo.dumpapps.DumpAppsActivity)
    		
    		int labelRes = info.activityInfo.labelRes;
    		String labelString = (String)info.activityInfo.nonLocalizedLabel;
    		
    		if ( labelRes == 0 && labelString == null ) 
    		{
    			// Activity does not have a label, use application label
    			labelRes = info.activityInfo.applicationInfo.labelRes;
    			labelString = (String)info.activityInfo.applicationInfo.nonLocalizedLabel;
    			
    			if ( labelRes == 0 && labelString == null ) {
    				labelString = info.activityInfo.name;
    			}
    		}

    		try {
    			
    	        Configuration config = new Configuration();
    	        config.locale = Locale.ENGLISH;
    	        
    			Resources res = pm.getResourcesForApplication(info.activityInfo.applicationInfo.packageName);
    	        res.updateConfiguration(config, null);
    	        
                apps.add(info.loadLabel(pm).toString().toLowerCase().replaceAll("\\W", ""));
                
    		} catch(Exception e) {
    			
    			Log.d("EXCEPTION: " + e.toString());
    			continue;
    		}    		
    	}    	

    	String[] appnames = apps.toArray( new String[1] );
		Arrays.sort(appnames);
		
		return appnames;
    }

    /**
     * This method scours the PackageManager looking for the app specified by _name, and upon
     * locating the app populates and returns an instance of ResolveInfo.  If no app is found
     * it returns null.
     * 
     * @param _context
     * @param _name, canonical name of the app to be queried
     * @return ResolveInfo instance for the app specified by _name
     */
    static public ResolveInfo app(Context _context, String _name) 
    {	
    	Log.d("[Device] pulling details for '" + _name + "'");
    	
    	PackageManager pm = _context.getPackageManager();

    	Intent intent = new Intent(Intent.ACTION_MAIN).addCategory(Intent.CATEGORY_LAUNCHER);    	
    	Iterator<ResolveInfo> applications = pm.queryIntentActivities(intent, 0).iterator();

    	ResolveInfo info = null;
    	
    	while(applications.hasNext()) 
    	{
    		info = applications.next();

    		// Get display label. There are 5 cases we have to cover here:
    		// -Activity has a string resource label (activity's localized label is used)
    		// -Activity has a hardcoded string label (activity's nonlocalized label is used)
    		// -Activity has no label, application has string resource label (application's localized label is used)
    		// -Activity has no label, application has hardcoded string label (application's nonlocalized label is used)
    		// -Activity has no label, application has no label (activity name is used, ex: com.vlingo.dumpapps.DumpAppsActivity)
    		
    		int labelRes = info.activityInfo.labelRes;
    		String labelString = (String)info.activityInfo.nonLocalizedLabel;
    		
    		if ( labelRes == 0 && labelString == null ) 
    		{
    			// Activity does not have a label, use application label
    			labelRes = info.activityInfo.applicationInfo.labelRes;
    			labelString = (String)info.activityInfo.applicationInfo.nonLocalizedLabel;
    			
    			if ( labelRes == 0 && labelString == null ) {
    				labelString = info.activityInfo.name;
    			}
    		}

    		try {
    			
    	        Configuration config = new Configuration();
    	        config.locale = Locale.ENGLISH;
    	        
    			Resources res = pm.getResourcesForApplication(info.activityInfo.applicationInfo.packageName);
    	        res.updateConfiguration(config, null);
    	        
    	        String name = info.loadLabel(pm).toString().toLowerCase().replaceAll("\\W", "");
    	        
                if (name.equals(_name))
                	break;
                
    		} catch(Exception e) {
    			
    			Log.d("EXCEPTION: " + e.toString());
    			continue;
    		}    		
    	}    	

		return info;
    }

    /**
     * This method creates a pseudo-CSV format to list the language translations
     * for all apps installed on the device.  This formatting code was taken
     * directly from the DumpApps tool.
     * 
     * The format which is created by this method is as follows:
     * 
     * R3_launchable_cf,ExecName,ExecPackage,ASR_cf, locale, ...
     * canonical name, activity, package, locale translation, ...
     * 
     * @param _context
     * @return a fully formatted string
     */
    static public String appTranslations(Context _context) 
    {
    	Log.d("Device.appTranslations(Context)");
    	
    	Locale[] locales = supportedLocales();
    	
    	StringBuilder sb = new StringBuilder();

    	// include the file header
    	sb.append("R3_launchable_cf,ExecName,ExecPackage,ASR_cf,");
    	
    	// include the list of locales
		for (Locale locale : locales) {
			sb.append(locale.toString());
			sb.append(',');
		}		
		sb.setLength(sb.length()-1);
		sb.append("\n");
		
		// include every app translation
		Iterator<App> apps = getAppTranslations(_context,locales).iterator();
		while (apps.hasNext())
		{
			App app = apps.next();
			
			sb
			.append( app.name ).append( "," )
			.append( app.activity ).append( "," )
			.append( app.pkg ).append(",")
			.append(",")
			;
			
			// now append all the name translations
			for (Locale locale : locales)
				sb.append( app.translations.get(locale) ).append(",");
			
			// remove the final comma ','
			sb.setLength( sb.length() - 1 );
			
			// terminate with a cr-lf
			sb.append("\n");
		}
		
		return sb.toString();
    }
    
    /**
     * A helper method to generate language translations for all apps installed on the device,
     * and for all Locales presently supported by the VoiceTalk app.
     * 
     * @param _context
     * @return list of App instances which contain the canonical name, and the translations for an app
     */
    static public List<App> getAppTranslations(Context _context)
    {
    	return getAppTranslations(_context,supportedLocales());
    }

    /**
     * A helper method to generate language translations for only a single app, given its
     * conanical name.
     * 
     * @param _context
     * @param _app, canonical name of the app to tranlate
     * @return map of translations for all Locales presently supported by the VoiceTalk app.
     */
    static public Map<Locale,String> getAppTranslations(Context _context, String _app)
    {
    	List<App> apps = getAppTranslations(_context);
    	
    	for ( App app : apps.toArray( new App[1] ) )
    		if ( app.name.equals(_app) && app.translations != null )
    			return app.translations;
    	
    	return new HashMap<Locale,String>();
    }

    /**
     * This method generates language translations for every app installed on the device
     * for the Locales which are included in _locales.  This is the code logic which was
     * originally housed in the DumpApps tool.
     * 
     * @param _context
     * @param _locales, array of Locales to be used for translations
     * @return List<App>, a list of App instances which contain the canonical name for each app, and the language translations
     */
    static public List<App> getAppTranslations(Context _context, Locale[] _locales) 
    {
    	Log.d("Device.getAppTranslations(Context,Locale[])");
    	
    	List<App> apps = new ArrayList<App> ();
    	
    	Intent intent = new Intent(Intent.ACTION_MAIN).addCategory(Intent.CATEGORY_LAUNCHER);
    	
    	PackageManager pm = _context.getPackageManager();
    	Iterator<ResolveInfo> activities = pm.queryIntentActivities(intent, 0).iterator();
    	
    	while (activities.hasNext()) 
    	{
    		ResolveInfo info = activities.next();
    		App app = new App();
    		
    		// Get display label. There are 5 cases we have to cover here:
    		// -Activity has a string resource label (activity's localized label is used)
    		// -Activity has a hardcoded string label (activity's nonlocalized label is used)
    		// -Activity has no label, application has string resource label (application's localized label is used)
    		// -Activity has no label, application has hardcoded string label (application's nonlocalized label is used)
    		// -Activity has no label, application has no label (activity name is used, ex: com.vlingo.dumpapps.DumpAppsActivity)
    		
			int id = info.activityInfo.labelRes;
    		String name = (String)info.activityInfo.nonLocalizedLabel;
    		
    		if (id == 0 && name == null) 
    		{
    			id = info.activityInfo.applicationInfo.labelRes;
    			name = (String)info.activityInfo.applicationInfo.nonLocalizedLabel;

    			if (id == 0 && name == null) {
    				name = info.activityInfo.name;
    			}
    		}

    		try {
    			
    	        Configuration config = new Configuration();
    	        config.locale = Locale.ENGLISH;
    	        
    			Resources res = pm.getResourcesForApplication(info.activityInfo.applicationInfo.packageName);
    	        res.updateConfiguration(config, null);
                CharSequence label = info.loadLabel(pm);
                String labelStr = label.toString().toLowerCase().replaceAll("\\W", "");
                
                app.name(labelStr).pkg(info.activityInfo.packageName).activity(info.activityInfo.name);
                
    	        for (Locale locale : _locales) 
    	        {
    	        	String translation = name;
    	        	
        	        if (id != 0) 
        	        {
        	        	config.locale = locale;
            	        res.updateConfiguration(config, null);
            	        translation = res.getString(id);
        	        }
        	        
        	        app.addTranslation(locale,translation);
    	        }
    	        
    		} catch(Exception e) {
    			Log.d("EXCEPTION: " + e.toString());
    			continue;
    		}
    		
    		apps.add(app);
    	}    	
    	
    	return apps;
    }
        
    /**
     * This method returns an array of Locales presently supported by the VoiceTalk app
     * 
     * @return Locale[], includes a list of all locales present supported by the VoiceTalk app
     */
    static public Locale[] supportedLocales()
    {
    	Log.d("Device.supportedLocales()");
    	
    	return new Locale[] 
    	{
    	Locale.ENGLISH, 
    	Locale.FRENCH, 
    	Locale.ITALIAN, 
    	Locale.GERMAN, 
    	new Locale("es"), 
    	Locale.CHINESE, 
    	Locale.JAPANESE, 
    	Locale.KOREAN, 
    	new Locale("ru", "RU")
    	};
    }
}
