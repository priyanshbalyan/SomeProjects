package com.priyanshbalyan.someprojects;
import android.app.*;
import android.os.*;
import java.io.*;
import java.util.*;
import android.widget.*;
import android.view.*;

public class FolderList extends ListActivity
{
	File currentfile ;
	List<String> folderlist,filenamelist ;
	File[] filesinfolder ;
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		// TODO: Implement this method
		super.onCreate(savedInstanceState);
		
		folderlist = new ArrayList<>();
		filenamelist = new ArrayList<>();
		String rootsd = Environment.getExternalStorageDirectory().toString();
		Toast.makeText(getApplicationContext(),rootsd,Toast.LENGTH_LONG).show();
		currentfile = new File("/storage/sdcard1");
	
		filllistviewwithfiles();
	}
               
	public void filllistviewwithfiles(){
		
		Toast.makeText(getApplicationContext(), currentfile.getPath(), Toast.LENGTH_SHORT).show();
		filesinfolder = currentfile.listFiles();
		filenamelist.clear();
		folderlist.clear();
		for(int i=0 ; i<filesinfolder.length ; i++){
			if(filesinfolder[i].isDirectory()|| filesinfolder[i].toString().endsWith(".mp4")){
				folderlist.add(filesinfolder[i].toString());
				filenamelist.add(filesinfolder[i].getName().toString());
			}
		}

		setListAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,filenamelist));
		getListView().setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
		//getListView().isItemChecked(position)
	}
	
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id)
	{
		// TODO: Implement this method
		super.onListItemClick(l, v, position, id);

		/*File file = new File(mylist.get(position));
		File parentfile = new File(file.getParent());
		String parentfilename = parentfile.getName().toString();
		*/
		
		currentfile = new File(folderlist.get(position));
		if(currentfile.isDirectory())
			filllistviewwithfiles();
	}

	@Override
	public void onBackPressed()
	{
		// TODO: Implement this method
		
		currentfile = new File(currentfile.getParent());
		
		if(currentfile.getPath().matches("/storage"))
			super.onBackPressed();
		else
			filllistviewwithfiles();
	}
	
	
}
