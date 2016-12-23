package com.priyanshbalyan.someprojects;

import android.app.*;
import android.content.*;
import android.os.*;
import java.text.*;
import java.util.*;

public class MyService extends Service
{
	private IBinder myibinder = new MyLocalBinder();

	public MyService(){
		
	}
	
	@Override
	public IBinder onBind(Intent p1)
	{
		// TODO: Implement this method
		return myibinder;
	}
	
	public String getthestring(){      //when this service is bound to a activity that activity can call this function
		String s = "Simple" ;
		return s ;
	}
	
	

	public class MyLocalBinder extends Binder{
		MyService getService(){
			return MyService.this ;
		}
	}
	
}
