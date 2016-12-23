package com.priyanshbalyan.someprojects;

import android.app.*;
import android.content.*;
import android.graphics.*;
import android.os.*;
import android.support.v7.app.*;
import android.support.v7.appcompat.*;
import android.view.*;
import android.view.View.*;
import android.widget.*;

import android.app.AlertDialog;
import android.support.v7.appcompat.R;


public class MainActivity extends Activity implements OnClickListener
{
	Button b,bpa,ba,bc,bie,bg,bast,bsf ;
	NotificationCompat.Builder notification ;
	int uniqueID = 12345;
	
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
        
		//setTitle(null);
		//Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
		//setSupportActionBar(toolbar);
		//toolbar.setLogo(R.drawable.ic_launcher);
		//toolbar.setLogoDescription(getResources().getString(R.string.app_name));
	
		initializebuttons();
		
		notification = new NotificationCompat.Builder(this);
		notification.setAutoCancel(true);
	}
	
	public void initializebuttons(){
		b= (Button) findViewById(R.id.bstart);
		bpa= (Button) findViewById(R.id.bpa);
		ba= (Button) findViewById(R.id.banim);
		bc=(Button)findViewById(R.id.bcardview);
		bie =(Button) findViewById(R.id.bimageeffects);
		bg=(Button)findViewById(R.id.bgesture);
		bast=(Button)findViewById(R.id.bast);
		bsf=(Button)findViewById(R.id.bstartfragment);
	
		bsf.setOnClickListener(this);
		b.setOnClickListener(this);
		bpa.setOnClickListener(this);
		ba.setOnClickListener(this);
		bc.setOnClickListener(this);
		bie.setOnClickListener(this);
		bg.setOnClickListener(this);
		bast.setOnClickListener(this);
		
	}
	
	@Override
	public void onClick(View p1)
	{
		// TODO: Implement this method
		switch(p1.getId())
		{
			case R.id.bstart :
					startService(new Intent(MainActivity.this, FloatingWindow.class));
				break ;
				
			case R.id.bpa :
					startActivity(new Intent(MainActivity.this, PropertyAnimation.class));
				break ;
			
			case R.id.banim :
					startActivity(new Intent(MainActivity.this, AnimationWindow.class));
				break;
				
			case R.id.bcardview:
					startActivity(new Intent(getApplicationContext(),CardViewExample.class));
				break;
				
			case R.id.bimageeffects:
					startActivity(new Intent(getApplicationContext(),ImageEffects.class));
				break;
				
			case R.id.bgesture:
					startActivity(new Intent(getApplicationContext(),GestureControls.class));
					break;
					
			case R.id.bast:
					startActivity(new Intent(getApplicationContext(), AsyncTaskExample.class));
					break;
					
			case R.id.bstartfragment:
					startActivity(new Intent(getApplicationContext(), FragActivity.class));
					break;
		
		}
	}
	
	public void startBroadcast(View v){
		Intent i = new Intent();
		i.setAction("com.priyanshbalyan.someprojects");
		i.setFlags(Intent.FLAG_INCLUDE_STOPPED_PACKAGES);
		sendBroadcast(i);
	}
	
	public void startThread(View v){
		startActivity(new Intent(getApplicationContext(), StartThread.class));
	}

	public void startNotification(View v){
		notification.setSmallIcon(R.drawable.ic_launcher);
		notification.setTicker("Here is the ticker.");
		notification.setContentTitle("Here is the content title");
		notification.setContentText("Here is content text");
		notification.setWhen(System.currentTimeMillis());
		
		Intent intent = new Intent(this, MainActivity.class);
		PendingIntent pendingintent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
		notification.setContentIntent(pendingintent);
		
		NotificationManager nm = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
		nm.notify(uniqueID, notification.build());
	}
	
	public void startToolbar(View v){
		startActivity(new Intent(getApplicationContext(), TopToolbar.class));
	}
	
	public void startMaterialTabs(View v){
		startActivity(new Intent(getApplicationContext(), MaterialTabs.class));
	}
	
	public void startFolderList(View v){
		startActivity(new Intent(getApplicationContext(), FolderList.class));
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		// TODO: Implement this method
		super.onCreateOptionsMenu(menu);
		MenuInflater mi = getMenuInflater() ;
		mi.inflate(R.layout.menu,menu);
		return true ;
		
		
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item)
	{
		LinearLayout ll = (LinearLayout)findViewById(R.id.ll) ;
		// TODO: Implement this method
		switch(item.getItemId()){
			case R.id.aboutme:
				AlertDialog d = new AlertDialog.Builder(MainActivity.this).create();
				d.setTitle("Who developed this stupid App ?");
				d.setMessage("App developed by Priyansh\nCopyright © 2015");
				d.setButton("FINE", new DialogInterface.OnClickListener(){

						@Override
						public void onClick(DialogInterface p1, int p2)
						{
							// TODO: Implement this method
						}


					});
				d.setButton2("Rate the App!", new DialogInterface.OnClickListener(){

						@Override
						public void onClick(DialogInterface p1, int p2)
						{
							// TODO: Implement this method
						}


					});
				d.show();
				break;
				
			case R.id.red:
				if(item.isChecked())
					item.setChecked(false);
				else
					item.setChecked(true);
					
				ll.setBackgroundColor(Color.RED);
				break;
				
			case R.id.blue:
				if(item.isChecked())
					item.setChecked(false);
				else
					item.setChecked(true);

				ll.setBackgroundColor(Color.BLUE);
				break;
				
			case R.id.green:
				if(item.isChecked())
					item.setChecked(false);
				else
					item.setChecked(true);

				ll.setBackgroundColor(Color.GREEN);
				break;
		}
		return false ;
	}
	
		
}
