package com.priyanshbalyan.someprojects;

import android.content.*;
import android.database.*;
import android.graphics.*;
import android.os.*;
import android.provider.*;
import android.support.v4.app.*;
import android.support.v7.widget.*;
import android.view.*;
import android.view.animation.*;
import android.widget.*;
import java.io.*;
import android.view.View.*;

public class TabFragment1 extends Fragment implements MyItemClickListener
{
	Cursor c ;
	int count ;
	String[] p = {MediaStore.Video.Media.DISPLAY_NAME, MediaStore.Video.Media.DATA,MediaStore.Video.Media._ID, MediaStore.Video.Media.HEIGHT};

	Bitmap[] bitmaplist;
	int vnameindex, vresindex;

	RVAdapter rvadapter ;
	ThumbnailLoader thumbloader ;
	
	public TabFragment1(){
	
	}

	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		// TODO: Implement this method
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		// TODO: Implement this method
		View v = inflater.inflate(R.layout.tabfragment1, container, false);

		RecyclerView rv = (RecyclerView)v.findViewById(R.id.recyclerview);
		rv.setLayoutManager(new GridLayoutManager(getActivity(),2));
		rv.setHasFixedSize(true);

		c= getActivity().managedQuery(MediaStore.Video.Media.EXTERNAL_CONTENT_URI, p, null, null, MediaStore.Video.Media.DISPLAY_NAME + " ASC");
		count = c.getCount();

		vnameindex = c.getColumnIndexOrThrow(MediaStore.Video.Media.DISPLAY_NAME);
		vresindex = c.getColumnIndexOrThrow(MediaStore.Video.Media.HEIGHT);

		rvadapter = new RVAdapter(getActivity().getApplicationContext());
		rv.setAdapter(rvadapter);

		rvadapter.setOnItemClickListener(this);

		bitmaplist = new Bitmap[count];
		thumbloader = new ThumbnailLoader();
		thumbloader.execute();
		
		return v ;
	}
	
	
	@Override
	public void onItemClick(View v, int position)
	{
		// TODO: Implement this method
		v.setClickable(true);
		Toast.makeText(getActivity().getApplicationContext(), "item "+position,Toast.LENGTH_SHORT).show();
	}

	public class RVAdapter extends RecyclerView.Adapter<RVAdapter.ItemViewHolder>
	{
		Context context ;
		MyItemClickListener listener ;

		RVAdapter(Context context){
			this.context = context;
		}

		public void setOnItemClickListener(MyItemClickListener mylistener){
			this.listener = mylistener ;
		}

		@Override
		public int getItemCount()
		{
			// TODO: Implement this method
			return count ;
		}

		@Override
		public ItemViewHolder onCreateViewHolder(ViewGroup vg, int i)
		{
			// TODO: Implement this method
			View v = LayoutInflater.from(vg.getContext()).inflate(R.layout.cardviewlistitem,vg,false);
			ItemViewHolder ivh = new ItemViewHolder(v,listener);
			
			return ivh ;
		}

		@Override
		public void onBindViewHolder(ItemViewHolder ivh, int i)
		{
			// TODO: Implement this method
			c.moveToPosition(i);
			ivh.vname.setText(c.getString(vnameindex));
			ivh.vres.setText(c.getString(vresindex));

			if(bitmaplist[i] != null)
				ivh.vthumb.setImageBitmap(bitmaplist[i]);
			else
				ivh.vthumb.setImageResource(R.drawable.ic_launcher);


			File pathfile = new File(c.getString(c.getColumnIndexOrThrow(MediaStore.Video.Media.DATA)));
			File parentfile = new File(pathfile.getParent());
			String pathfoldername = parentfile.getName().toString();
			ivh.vfolder.setText(pathfoldername);

			animate(ivh);
		}

		public void animate(RecyclerView.ViewHolder ivh){
			final Animation animAnticipateOvershoot = AnimationUtils.loadAnimation(context, R.animator.bounceinterpolator);
			ivh.itemView.setAnimation(animAnticipateOvershoot);
		}

		public class ItemViewHolder extends RecyclerView.ViewHolder{
			TextView vname,vres,vfolder;
			ImageView vthumb ;
			MyItemClickListener mylistener ;

			ItemViewHolder(View v, MyItemClickListener listener){
				super(v);
				vname = (TextView)v.findViewById(R.id.tvcardview);
				vres = (TextView)v.findViewById(R.id.tvres);
				vthumb = (ImageView)v.findViewById(R.id.ivthumbnail);
				vfolder = (TextView)v.findViewById(R.id.tvfolder);

				this.mylistener = listener;
				v.setOnClickListener(new View.OnClickListener(){
						@Override
						public void onClick(View p1)
						{
							// TODO: Implement this method
							if(mylistener != null){
								mylistener.onItemClick(p1, getPosition());
							}
						}
					});
			}
		}


	}

	private class ThumbnailLoader extends AsyncTask<Object,Void,Bitmap>
	{

		int position ;

		public ThumbnailLoader(){

		}

		@Override
		protected Bitmap doInBackground(Object[] p1)
		{
			// TODO: Implement this method
			c.moveToPosition(position);
			for(int i=0 ; i<count ; i++){
				final int j= i;
				c.moveToPosition(i);
				long vcolumnid = c.getLong(c.getColumnIndex(MediaStore.Video.Media._ID));
				BitmapFactory.Options options = new BitmapFactory.Options();
				options.inSampleSize = 1;
				Bitmap b = MediaStore.Video.Thumbnails.getThumbnail(getActivity().getContentResolver(), vcolumnid, MediaStore.Video.Thumbnails.MINI_KIND, options);
				if(b != null)
					bitmaplist[i] = b ;
				getActivity().runOnUiThread(new Runnable(){
						@Override
						public void run()
						{
							// TODO: Implement this method
							rvadapter.notifyItemChanged(j);
						}
					});

			}

			return null;
		}

		@Override
		protected void onPostExecute(Bitmap result)
		{
			// TODO: Implement this method
			Toast.makeText(getActivity().getApplicationContext(), "All thumbnails loaded", Toast.LENGTH_SHORT).show();
		}

	}
	
}
