package com.vlingo.android.tools.explorer;

import java.util.Timer;
import java.util.TimerTask;

import com.vlingo.android.tools.Log;
import com.vlingo.android.tools.explorer.R;

import android.os.*;
import android.app.Activity;
import android.content.Intent;

/**
 * This class provides a simple splash screen that times out after 5 seconds -- just
 * enough time to enjoy the Lego guy -- then displays the main menu.
 * 
 * @author J Carter
 */
public class SplashScreenActivity extends Activity 
{
    @Override
    public void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);
    }
    
    @Override
    public void onResume()
    {
    	super.onResume();
    	startTimer();
    }
    
    private void startTimer()
    {
    	TimerTask close = new TimerTask() {
    		public void run() {
    			close();
    		}};
    		
    	new Timer().schedule( close, 5000 );
    	Log.d("timer started");
    }
    
    private void close()
    {
    	startActivity( new Intent(this,MenuListActivity.class) );
		Log.d("closing Splash Screen");
		finish();    	
    }
}