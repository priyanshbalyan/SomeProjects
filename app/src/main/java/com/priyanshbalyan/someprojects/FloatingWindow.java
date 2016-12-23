package com.priyanshbalyan.someprojects;

import android.app.*;
import android.content.*;
import android.graphics.*;
import android.os.*;
import android.view.*;
import android.widget.*;
import com.priyanshbalyan.someprojects.*;
import android.view.View.*;
import android.webkit.*;
import android.view.inputmethod.*;
import java.net.*;

public class FloatingWindow extends Service implements OnClickListener
{

	private WindowManager wm ;
	private ImageView floatingicon ;
	private LinearLayout overlay ;
	private LayoutInflater inflater ;

	private int initx,inity ;
	private float touchx,touchy ;
	
	ImageView back,forward,refresh,history,go,move ;
	EditText url ;
	WebView mybrowser ;

	@Override
	public IBinder onBind(Intent p1)
	{
		// TODO: Implement this method
		return null;
	}

	@Override
	public void onCreate()
	{
		// TODO: Implement this method
		super.onCreate();
		
		wm = (WindowManager) getSystemService(WINDOW_SERVICE);
		floatingicon = new ImageView(this);
		
		
		inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
		overlay = (LinearLayout) inflater.inflate(R.layout.overlay, null);

		back = (ImageView)overlay.findViewById(R.id.ivback);
		forward = (ImageView)overlay.findViewById(R.id.ivforward);
		refresh = (ImageView)overlay.findViewById(R.id.ivrefresh);
		history = (ImageView)overlay.findViewById(R.id.ivhistory);
		move = (ImageView)overlay.findViewById(R.id.ivmove);
		url = (EditText)overlay.findViewById(R.id.eturl);
		mybrowser = (WebView)overlay.findViewById(R.id.wvbrowser);
		
		back.setOnClickListener(this);
		forward.setOnClickListener(this);
		refresh.setOnClickListener(this);
		history.setOnClickListener(this);
		url.setOnClickListener(this);
		
		mybrowser.getSettings().setJavaScriptEnabled(true);
		mybrowser.setWebViewClient(new MyViewClient());
		mybrowser.loadUrl("http://www.google.com");
		
		floatingicon.setImageResource(R.drawable.ic_launcher);

		final WindowManager.LayoutParams params = new WindowManager.LayoutParams(
			WindowManager.LayoutParams.WRAP_CONTENT,
			WindowManager.LayoutParams.WRAP_CONTENT,
			WindowManager.LayoutParams.TYPE_PHONE,
			WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE|WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM,
			PixelFormat.TRANSLUCENT
		);
		

		final WindowManager.LayoutParams llparameters = 
			new WindowManager.LayoutParams(WindowManager.LayoutParams.WRAP_CONTENT,
				WindowManager.LayoutParams.WRAP_CONTENT,
				WindowManager.LayoutParams.TYPE_PHONE, 
				WindowManager.LayoutParams.FLAG_LOCAL_FOCUS_MODE,
				PixelFormat.TRANSLUCENT);


		params.gravity = Gravity.TOP | Gravity.LEFT;
		params.x = 0 ;
		params.y = 100 ;

		llparameters.x = params.x ;
		llparameters.y = params.y ;
		llparameters.gravity = Gravity.TOP | Gravity.LEFT ;

		wm.addView(floatingicon, params);

		floatingicon.setOnClickListener(new View.OnClickListener(){

				@Override
				public void onClick(View p1)
				{
					// TODO: Implement this method
				
					llparameters.x = params.x ;
					llparameters.y = params.y ;
					wm.removeView(floatingicon);
					wm.addView(overlay, llparameters);

				}


			});

		move.setOnClickListener(new View.OnClickListener(){

				@Override
				public void onClick(View p1)
				{
					// TODO: Implement this method
					params.x = llparameters.x ;
					params.y = llparameters.y ;
					wm.removeView(overlay);
					wm.addView(floatingicon, params);

				}


			});



		floatingicon.setOnTouchListener(new View.OnTouchListener(){
				@Override
				public boolean onTouch(View p1, MotionEvent e)
				{
					// TODO: Implement this method
					switch (e.getAction())
					{
						case MotionEvent.ACTION_DOWN :
							initx = params.x;
							inity = params.y;
							touchx = e.getRawX();
							touchy = e.getRawY();
							return false;

						case MotionEvent.ACTION_UP:
							if (((int)(e.getRawY()) >= 850))
								wm.removeView(floatingicon);
							return false ;

						case MotionEvent.ACTION_MOVE:
							params.x = initx + (int)(e.getRawX() - touchx);
							params.y = inity + (int)(e.getRawY() - touchy);
							wm.updateViewLayout(floatingicon, params);

							return false;

					}
					return false;
				}


			});

		move.setOnTouchListener(new View.OnTouchListener(){
				@Override
				public boolean onTouch(View p1, MotionEvent e)
				{
					// TODO: Implement this method
					switch (e.getAction())
					{
						case MotionEvent.ACTION_DOWN :
							initx = llparameters.x;
							inity = llparameters.y;
							touchx = e.getRawX();
							touchy = e.getRawY();
							return false;

						case MotionEvent.ACTION_UP:
							Toast.makeText(FloatingWindow.this, "App currently in development", Toast.LENGTH_SHORT).show() ;
							if (((int)(e.getRawY()) >= 850))
								wm.removeView(overlay);
							return false ;

						case MotionEvent.ACTION_MOVE:
							llparameters.x = initx + (int)(e.getRawX() - touchx);
							llparameters.y = inity + (int)(e.getRawY() - touchy);
							wm.updateViewLayout(overlay, llparameters);

							return false;

					}
					return false;
				}


			});

	}


	@Override
	public void onDestroy()
	{
		// TODO: Implement this method
		super.onDestroy();
		if (floatingicon != null)
		{
			wm.removeView(floatingicon);
			wm.removeView(overlay);
		}
	}
	
	@Override
	public void onClick(View p1)
	{
		// TODO: Implement this method
		switch(p1.getId())
		{
			case R.id.ivgo :
					String webword = url.getText().toString() ;
					if(webword.contains("."))
					{
						mybrowser.loadUrl(String.format("http://www.%s",webword));
					}else{
						mybrowser.loadUrl(String.format("http://www.google.co.in/search?q=%s&btnG=",webword));
					}
					InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
					imm.hideSoftInputFromWindow(url.getWindowToken(),0);
				break ;
				
			case R.id.ivback :
					if(mybrowser.canGoBack())
						mybrowser.goBack();
				break ;
				
			case R.id.ivforward :
					if(mybrowser.canGoForward())
						mybrowser.goForward() ;
				break ;
				
			case R.id.ivrefresh :
					mybrowser.reload() ;
				break ;
				
			case R.id.ivhistory :
					mybrowser.clearHistory() ;
				break ;
			
			case R.id.eturl :
					WindowManager.LayoutParams newparams = new WindowManager.LayoutParams(
						WindowManager.LayoutParams.WRAP_CONTENT,
						WindowManager.LayoutParams.WRAP_CONTENT,
						WindowManager.LayoutParams.TYPE_SYSTEM_OVERLAY,
						WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM,
						PixelFormat.TRANSLUCENT
					);
					//wm.removeView(overlay);
				//	wm.addView(overlay,newparams);
					InputMethodManager im = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
					if(im!=null)
					{
						im.toggleSoftInput(0,InputMethodManager.SHOW_IMPLICIT);
					}
				break ;
		}
	}
	
	public class MyViewClient extends WebViewClient
	{

		@Override
		public boolean shouldOverrideUrlLoading(WebView view, String url)
		{
			// TODO: Implement this method
			view.loadUrl(url);
			return true ;
		}
	
	}
}
