package com.priyanshbalyan.someprojects;

import android.app.*;
import android.os.*;
import android.view.*;
import android.widget.*;
import android.content.*;
import com.priyanshbalyan.someprojects.MyService.MyLocalBinder ;

public class StartThread extends Activity
{
	TextView tv, tvservice;
	Button b ;
	
	MyService myservice ;
	boolean isbound = false ;
	
	Handler h = new Handler(){

		@Override
		public void handleMessage(Message msg)
		{
			// TODO: Implement this method
			tv.setText("You mastered threads");
		}
	};
	
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		// TODO: Implement this method
		super.onCreate(savedInstanceState);
		setContentView(R.layout.startthread);
		tv = (TextView)findViewById(R.id.tvthread);
		tvservice = (TextView)findViewById(R.id.tvservice);
		b = (Button)findViewById(R.id.bservice);
	
			
		Intent i = new Intent(this, MyService.class);
		bindService(i, myconnection, Context.BIND_AUTO_CREATE);
		
		b.setOnClickListener(new View.OnClickListener(){

				@Override
				public void onClick(View p1)
				{
					// TODO: Implement this method
					Toast.makeText(getApplicationContext(), "Button function called",Toast.LENGTH_SHORT).show();
				    String currenttime = myservice.getthestring();
					tvservice.setText(currenttime);
				}
		});
	}
	
	public void waitingfunction(View v){
		Runnable r = new Runnable(){

			@Override
			public void run()
			{
				// TODO: Implement this method
				long time = System.currentTimeMillis() + 10000 ;
				while(System.currentTimeMillis() < time){
					synchronized(this){     //prevents multiple same threads from running
						try{
							wait(time-System.currentTimeMillis());    //should not update UI elements inside a thread
						}catch(Exception e){}
					}
				}
				h.sendEmptyMessage(0);
			}
		};
		
		Thread t = new Thread(r);
		t.start();
		

	}
	
	private ServiceConnection myconnection= new ServiceConnection(){

		@Override
		public void onServiceConnected(ComponentName p1, IBinder p2)
		{
			// TODO: Implement this method
			
			MyLocalBinder binder = (MyLocalBinder) p2 ;
			myservice = binder.getService();
			isbound = true;
			//Toast.makeText(getApplicationContext(), "Service connected", Toast.LENGTH_SHORT).show();
		}

		@Override
		public void onServiceDisconnected(ComponentName p1)
		{
			// TODO: Implement this method
			isbound = false ;
			//Toast.makeText(getApplicationContext(), "Service disconnected", Toast.LENGTH_SHORT).show();
		}
	};
	
}
