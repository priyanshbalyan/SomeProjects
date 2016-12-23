package com.priyanshbalyan.someprojects;

import android.app.*;
import android.os.*;
import android.view.*;
import android.widget.*;
import android.transition.*;

public class AnimationWindow extends Activity
{
	ViewGroup ll ;
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		// TODO: Implement this method
		super.onCreate(savedInstanceState);
		setContentView(R.layout.animationwindow);
		ll = (ViewGroup)findViewById(R.id.ll);
		ll.setOnTouchListener(new LinearLayout.OnTouchListener(){

				@Override
				public boolean onTouch(View p1, MotionEvent p2)
				{
					// TODO: Implement this method
					View mybutton= (View)findViewById(R.id.bmove);
					
					TransitionManager.beginDelayedTransition(ll);
					
					RelativeLayout.LayoutParams positionrules = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,RelativeLayout.LayoutParams.WRAP_CONTENT);
					positionrules.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM, RelativeLayout.TRUE);
					positionrules.addRule(RelativeLayout.ALIGN_PARENT_RIGHT,RelativeLayout.TRUE);
					mybutton.setLayoutParams(positionrules);
					
					ViewGroup.LayoutParams sizerules = mybutton.getLayoutParams();
					sizerules.width = 450;
					sizerules.height = 200;
					mybutton.setLayoutParams(sizerules);
					
					return false;
				}
		});
	}
	
}
