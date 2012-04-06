package com.vlingo.android.tools.explorer;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;

import com.vlingo.android.tools.Log;
import com.vlingo.android.tools.explorer.R;

import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.Toast;

/**
 * This class encapsulates the common behaviors of lists in this app -- similar look and feel, same options menu options
 * common handling when the user presses "Save" or "Email", and a re-useable shell for derived classes to determine how
 * to save, but using a common save method where standard filenames and locations are used.  Sub-classes must define
 * the following methods:
 * 
 * adapter() - to populate the contents of the list
 * save()    - to serialize the contents of the list when a user select "Save" or "E-mail" from the options menu
 * 
 * Additionally sub-classes can override onEmail() to send the contents to a specified e-mail address.
 * 
 * @author J Carter
 */
public abstract class ExplorerListActivity extends ListActivity 
{
    @Override
    public void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        setListAdapter(adapter());
    }
    
	@Override
	public boolean onCreateOptionsMenu(Menu _menu)
	{
		getMenuInflater().inflate(R.menu.list_actions, _menu);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem _item)
	{
		switch (_item.getItemId())
		{
		case R.id.Save: onSave(); break;
		case R.id.Email: onEmail(); break;
		}
		
		return super.onOptionsItemSelected(_item);
	}

	protected String save()
	{
		return null;
	}
	
	protected abstract ListAdapter adapter();
	
	/*
	protected ListAdapter adapter()
	{
		return new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, contents());
	}
	
	protected String[] contents()
	{
		return new String[1];
	}
	*/
	protected void onSave()
	{
		Log.i("saving ...");
		onSaved(save());
	}

	protected void onSaved(String _destination)
	{
		Log.i("saved");
		new AlertDialog.Builder(this)
		.setTitle("Saved to:")
		.setMessage(_destination == null ? "Not saved" : _destination)
		.setPositiveButton("OK",null)
		.create()
		.show();		
	}
	
	protected void onEmail()
	{
		android.util.Log.d("DeviceExplorer","e-mailing list ...");
	}

	protected void send(String _subject, String _message)
	{
		send(_subject,_message,null);
	}

	protected void send(String _subject, String _message, String _attachment)
	{
		Intent i = new Intent(Intent.ACTION_SEND);
		
		i.setType("message/rfc822");
		i.putExtra(Intent.EXTRA_EMAIL  , new String[]{"recipient@example.com"});
		i.putExtra(Intent.EXTRA_SUBJECT, (_subject == null || _subject.length() == 0? "Device Explorer info" : _subject));
		i.putExtra(Intent.EXTRA_TEXT   , _message);
		
		if ( _attachment != null && _attachment.length() > 0 ) 
		{
			File attachment = new File(_attachment);
			i.putExtra(Intent.EXTRA_STREAM, Uri.parse("file://"+ attachment.getPath()));
		}

		try {
			Log.i("sending ...");
		    startActivity(Intent.createChooser(i, "Send mail..."));
		} catch (android.content.ActivityNotFoundException ex) {
		    Toast.makeText(ExplorerListActivity.this, "There are no email clients installed.", Toast.LENGTH_SHORT).show();
		}		
	}
	
	protected String filePath()
	{
    	return "/mnt/sdcard";	
	}
	protected String fileSpec(String _prefix)
	{
		Log.d("file prefix = " + _prefix);
    	Calendar c = Calendar.getInstance();
    	String timestamp = (String) DateFormat.format("yyyyMMdd_kkmmss", c);
    	
		return _prefix + "_" + Build.MANUFACTURER + "_" + Build.MODEL + "_" + Build.DISPLAY + "_" + timestamp + ".txt"; 
	}
	
	protected String saveToFile( String _path, String _filename, String _contents )
	{
		File outFile = new File(filePath(), _filename);
    	String fullpath = outFile.getAbsolutePath();

    	Log.i("saving file " + fullpath + " ...");
    	
		FileOutputStream fos = null;

		try {

			fos = new FileOutputStream(outFile);
			
			if (fos != null) {
				try {
					fos.write(_contents.toString().getBytes("UTF-8"));
					fos.write("\n".getBytes());
				}
				catch(IOException ioe) {
					Log.d("EXCEPTION: " + ioe.toString());
				}
			}
			
		} catch(FileNotFoundException fnfe) {
			Log.d("EXCEPTION: " + fnfe.toString());
		} finally {
			
			if (fos != null) {
				try {fos.close();} catch(IOException ioe) {}
			}			
			
		}
		
		return fullpath;		
	}
	
	protected <T> ListAdapter standardArrayAdapter( T[] contents )
	{
		return new ArrayAdapter<T>(this,android.R.layout.simple_list_item_1, contents);
	}
}
