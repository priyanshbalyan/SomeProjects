package com.priyanshbalyan.someprojects;

import android.os.*;
import android.support.v4.app.*;
import android.view.*;
import android.widget.*;

public class BottomFragment extends Fragment
{

	TextView top, bottom ;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		// TODO: Implement this method
		View view = inflater.inflate(R.layout.bottomfragment, container, false);
		top = (TextView)view.findViewById(R.id.tvtop);
		bottom = (TextView)view.findViewById(R.id.tvbottom);
		return view;
	}
	
	public void settext(String toptext, String bottomtext){
		top.setText(toptext);
		bottom.setText(bottomtext);
	}
	
}
