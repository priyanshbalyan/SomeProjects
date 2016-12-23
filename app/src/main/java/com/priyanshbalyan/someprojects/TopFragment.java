package com.priyanshbalyan.someprojects;

import android.app.*;
import android.os.*;
import android.support.v4.app.*;
import android.view.*;
import android.widget.*;

import android.support.v4.app.Fragment;

public class TopFragment extends Fragment
{
	EditText top, bottom ;
	Button b ;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		// TODO: Implement this method
		View view = inflater.inflate(R.layout.topfragment, container, false);
		
		top = (EditText)view.findViewById(R.id.ettop);
		bottom = (EditText)view.findViewById(R.id.etbottom);
		b = (Button)view.findViewById(R.id.bapply);
		b.setOnClickListener(new View.OnClickListener(){

				@Override
				public void onClick(View p1)
				{
					// TODO: Implement this method
					activitycommand.apply(top.getText().toString(), bottom.getText().toString());
				}
		});   
		return view ;
	}

	@Override
	public void onAttach(Activity activity)
	{
		// TODO: Implement this method
		super.onAttach(activity);
		try{
			activitycommand = (TopListener) activity;
		}catch(ClassCastException e){
			throw new ClassCastException(activity.toString());
		}
	}
	
	public interface TopListener{
		public void apply(String top, String bottom);
	}
	
	TopListener activitycommand ;
	
}
