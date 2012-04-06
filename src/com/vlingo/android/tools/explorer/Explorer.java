package com.vlingo.android.tools.explorer;

import android.app.Application;
import android.content.Context;

public class Explorer extends Application 
{
	static public Context getContext()
	{
		return explorer;
	}
	
	static private Explorer explorer = new Explorer();
}
