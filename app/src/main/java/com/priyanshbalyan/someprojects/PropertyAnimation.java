package com.priyanshbalyan.someprojects;

import android.animation.*;
import android.app.*;
import android.graphics.*;
import android.os.*;
import android.widget.*;

public class PropertyAnimation extends Activity
{

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		// TODO: Implement this method
		super.onCreate(savedInstanceState);
		setContentView(R.layout.propertyanimation);
		
		ImageView icon3 = (ImageView) findViewById(R.id.ivicon3);
		
		AnimatorSet iconrotate = (AnimatorSet)AnimatorInflater.loadAnimator(this, R.animator.spin);
		iconrotate.setTarget(icon3);
		iconrotate.start();
		
		ImageView circle = (ImageView)findViewById(R.id.ivcircularshape);
		AnimatorSet circlemove = (AnimatorSet)AnimatorInflater.loadAnimator(this, R.animator.move);
		circlemove.setTarget(circle);
		circlemove.start();
		
		ValueAnimator bganim = ObjectAnimator.ofInt(findViewById(R.id.layout), "backgroundColor", Color.rgb(0x66,0xcc,0xff), Color.rgb(0x00, 0x66, 0x99));
		bganim.setDuration(3000);
		bganim.setRepeatCount(ValueAnimator.INFINITE);
		bganim.setRepeatMode(ValueAnimator.REVERSE);
		bganim.setEvaluator(new ArgbEvaluator());
		bganim.start();
		
		ObjectAnimator iconmoveanim = ObjectAnimator.ofFloat(findViewById(R.id.ivicon1), "x", -350);
		iconmoveanim.setDuration(3000);
		iconmoveanim.setRepeatCount(ObjectAnimator.INFINITE);
		iconmoveanim.setRepeatMode(ObjectAnimator.REVERSE);
		iconmoveanim.start();
		
		ObjectAnimator iconmoveanim2 = ObjectAnimator.ofFloat(findViewById(R.id.ivicon2), "x", -300);
		iconmoveanim2.setDuration(3000);
		iconmoveanim2.setRepeatCount(ObjectAnimator.INFINITE);
		iconmoveanim2.setRepeatMode(ObjectAnimator.REVERSE);
		iconmoveanim2.start();
	}
	
}
