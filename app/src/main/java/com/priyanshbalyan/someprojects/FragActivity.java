package com.priyanshbalyan.someprojects;

import android.os.*;
import android.support.v4.app.*;

public class FragActivity extends FragmentActivity implements TopFragment.TopListener
{


	@Override
	public void apply(String top, String bottom)
	{
		// TODO: Implement this method
		BottomFragment bottomfragment = (BottomFragment) getSupportFragmentManager().findFragmentById(R.id.fragment2);
		bottomfragment.settext(top,bottom);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		// TODO: Implement this method
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fragmentactivity);

	}

}
