package com.priyanshbalyan.someprojects;

import android.database.*;
import android.os.*;
import android.provider.*;
import android.support.v7.app.*;
import android.support.v7.widget.*;
import android.view.*;
import android.widget.*;
import java.io.*;
import java.util.*;

import android.support.v7.widget.Toolbar;
import android.widget.AdapterView.*;

public class TopToolbar extends AppCompatActivity
{

	Cursor c ;
	ListView lv ;
	String[] p = {MediaStore.Video.Media.DISPLAY_NAME, MediaStore.Video.Media.DATA, MediaStore.Video.Media._ID};
	List<VideoFolders> videofolders ;
	
	List<String> test ;
	int vnameindex,vpathindex,vresindex,vidindex ;
	Toolbar toolbar ;
	TextView tv ;
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		// TODO: Implement this method
		super.onCreate(savedInstanceState);
		setContentView(R.layout.toptoolbar);
		tv = (TextView)findViewById(R.id.tvclassdata);
		//setTitle(null);
		toolbar = (Toolbar) findViewById(R.id.toolbar);
		setSupportActionBar(toolbar);
		toolbar.setLogo(R.drawable.ic_launcher);
		toolbar.setLogoDescription(getResources().getString(R.string.app_name));
		
		videofolders = new ArrayList<>();
		test = new ArrayList<>();
		
		lv = (ListView)findViewById(R.id.lv);
		c = managedQuery(MediaStore.Video.Media.EXTERNAL_CONTENT_URI,p,null,null,MediaStore.Video.Media.DISPLAY_NAME + " ASC");
		
		vnameindex = c.getColumnIndexOrThrow(MediaStore.Video.Media.DISPLAY_NAME);
		vpathindex = c.getColumnIndexOrThrow(MediaStore.Video.Media.DATA);
		vidindex = c.getColumnIndexOrThrow(MediaStore.Video.Media._ID);
		
		videofoldersorter();
		
		for(int i=0 ; i<videofolders.size() ; i++){
			test.add(videofolders.get(i).foldername);
		}
		
		lv.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,test));
		
		lv.setOnItemClickListener(new AdapterView.OnItemClickListener(){
				@Override
				public void onItemClick(AdapterView<?> p1, View p2, int p3, long p4)
				{
					// TODO: Implement this method
					
					if(toolbar.getTitle().equals(getString(R.string.app_name))){
						toolbar.setTitle(videofolders.get(p3).foldername);
						lv.setAdapter(new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1,videofolders.get(p3).videonamesinfolder));
					}else
						Toast.makeText(getApplicationContext(), toolbar.getTitle(),Toast.LENGTH_SHORT).show();
				}
		});
		
		String s = "";
		for(int i=0 ; i<videofolders.size() ; i++){
			s+= "\n"+videofolders.get(i).foldername+"\n" ;
			for(int j=0 ; j<videofolders.get(i).videopathsinfolder.size() ; j++){
				s += videofolders.get(i).videopathsinfolder.get(j)+"\n" ;
			}
		}
		tv.setText(s);
	}

	public void videofoldersorter(){
		
		String comparestring = "Folder Name" ;
		boolean flag=false ;
		int pos=-1,flagpos=0;
		
		VideoFolders adderclass;
		videofolders.clear();
		
		for(int i=0 ; i<c.getCount() ; i++){
			c.moveToPosition(i);
			File file = new File(c.getString(vpathindex));
			File parentfile = new File(file.getParent());
			String fname = parentfile.getName();  //getting folder name from path

			if(!comparestring.equals(fname)){  
				comparestring = fname ;
				for(int j=0 ; j<videofolders.size() ; j++)
				{
					if(fname.equals(videofolders.get(j).foldername)){    //to check if item is already in the list
						flag = true ;  //folder is already in list
						flagpos = j;
						break ;
					}else
						flag = false ;
				}
				if(!flag){     //if folder name is not already in list
					pos++ ;
					adderclass = new VideoFolders();
					adderclass.foldername = fname ;
					videofolders.add(adderclass);
				}
			}
			if(fname.equals(videofolders.get(pos).foldername)){		//if current path folder is same as class foldername at pos
				videofolders.get(pos).videopathsinfolder.add(c.getString(vpathindex));  //Adding video paths to generated video folder class
				videofolders.get(pos).videonamesinfolder.add(c.getString(vnameindex));
				videofolders.get(pos).videoresinfolder.add(c.getString(vresindex));
			}
			
			if(fname.equals(videofolders.get(flagpos).foldername)){
				videofolders.get(flagpos).videopathsinfolder.add(c.getString(vpathindex));
				videofolders.get(flagpos).videonamesinfolder.add(c.getString(vnameindex));
				videofolders.get(flagpos).videoresinfolder.add(c.getString(vresindex));
			}
		}
		
	}

	@Override
	public void onBackPressed()
	{
		// TODO: Implement this method
		if(toolbar.getTitle().equals(getString(R.string.app_name)))
			super.onBackPressed();
		else{
			lv.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,test));
			toolbar.setTitle(getString(R.string.app_name));
		}
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		// TODO: Implement this method
		getMenuInflater().inflate(R.menu.main_menu, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item)
	{
		// TODO: Implement this method
		
		switch(item.getItemId()){
			case R.id.action_settings : 
				return true ;
			
			case R.id.action_refresh :
				
				break;
			
			case R.id.action_new :
				
				break;
		}
		
		return super.onOptionsItemSelected(item);
	}
	
	public class VideoFolders
	{
		String foldername ;
		List<String> videonamesinfolder;
		List<String> videopathsinfolder;
		List<String> videoresinfolder ;
		
		public VideoFolders(){
			videonamesinfolder = new ArrayList<>();
			videopathsinfolder = new ArrayList<>();
			videoresinfolder = new ArrayList<>();
		}
	}
	
	
}
