package com.priyanshbalyan.someprojects;

import android.app.*;
import android.os.*;
import android.view.*;
import android.widget.*;
import android.view.GestureDetector.*;
import android.support.v4.view.*;
import android.view.View.*;

public class GestureControls extends Activity implements GestureDetector.OnGestureListener, GestureDetector.OnDoubleTapListener
{

	TextView gtext,vtext;
	ImageView image;
	GestureDetectorCompat gd ;
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		// TODO: Implement this method
		super.onCreate(savedInstanceState);
		setContentView(R.layout.gesturecontrols);
		gtext = (TextView)findViewById(R.id.tvgesture);
		vtext=(TextView)findViewById(R.id.tvvelocity);
		image=(ImageView)findViewById(R.id.iv);
		this.gd = new GestureDetectorCompat(this,this);
		gd.setOnDoubleTapListener(this);
		image.setOnTouchListener(new OnTouchListener(){

				@Override
				public boolean onTouch(View p1, MotionEvent event)
				{
					// TODO: Implement this method
					gd.onTouchEvent(event);
					return true;
				}

			
		});
	}
	
	@Override
	public boolean onDown(MotionEvent p1)
	{
		// TODO: Implement this method
		gtext.setText("down");
		return true;
	}

	@Override
	public void onShowPress(MotionEvent p1)
	{
		// TODO: Implement this method
		gtext.setText("You Pressed");
	}

	@Override
	public boolean onSingleTapUp(MotionEvent p1)
	{
		// TODO: Implement this method
		gtext.setText("You Single Tapped");
		return true;
	}

	@Override
	public boolean onScroll(MotionEvent p1, MotionEvent p2, float p3, float p4)
	{
		// TODO: Implement this method
		gtext.setText("You Scrolled");
		return true;
	}

	@Override
	public void onLongPress(MotionEvent p1)
	{
		// TODO: Implement this method
		gtext.setText("You Long Pressed");
	}

	@Override
	public boolean onFling(MotionEvent p1, MotionEvent p2, float p3, float p4)
	{
		// TODO: Implement this method
		gtext.setText("You Flinged");
		vtext.setText("Velocity x : "+p3+" Velocity y : "+p4);
		return false;
	}
	

	@Override
	public boolean onSingleTapConfirmed(MotionEvent p1)
	{
		// TODO: Implement this method
		gtext.setText("You Single Tap Confirmed");
		return true;
	}

	@Override
	public boolean onDoubleTap(MotionEvent p1)
	{
		// TODO: Implement this methodConfirmed
		gtext.setText("You Double Tapped");
		return true;
	}

	@Override
	public boolean onDoubleTapEvent(MotionEvent p1)
	{
		// TODO: Implement this method
		gtext.setText("ondoubletapevent");
		return true;
	}

	/*@Override
	public boolean onTouchEvent(MotionEvent event)
	{
		// TODO: Implement this method
		this.gd.onTouchEvent(event);
		return super.onTouchEvent(event);
	}*/
}
