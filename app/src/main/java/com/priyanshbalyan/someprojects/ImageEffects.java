package com.priyanshbalyan.someprojects;

import android.app.*;
import android.graphics.*;
import android.graphics.drawable.*;
import android.os.*;
import android.widget.*;
import android.provider.*;

public class ImageEffects extends Activity
{

	Drawable wallpaper ;
	Bitmap imagebitmap ;
	ImageView myimageview ;
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		// TODO: Implement this method
		super.onCreate(savedInstanceState);
		setContentView(R.layout.imageeffects);
		
		myimageview = (ImageView)findViewById(R.id.ivimage);
		
		wallpaper = getResources().getDrawable(R.drawable.jump1);
		imagebitmap = ((BitmapDrawable)wallpaper).getBitmap();
		
		Bitmap newimage = invertimage(imagebitmap);
		myimageview.setImageBitmap(newimage);
		
		
		/*Drawable[] layers = new Drawable[2] ;
		layers[0] = getResources().getDrawable(R.drawable.jump1);
		layers[1] = getResources().getDrawable(R.drawable.jump2);
		LayerDrawable layerdrawable = new LayerDrawable(layers);
		myimageview.setImageDrawable(layerdrawable);
		*/
		
		//save the image to device 
		MediaStore.Images.Media.insertImage(getContentResolver(), newimage, "title", "description");
	}
	
	public Bitmap invertimage(Bitmap original){
	
		Bitmap finalimage = Bitmap.createBitmap(original.getWidth(), original.getHeight(), original.getConfig());
		int A,R,G,B ;
		int pixelColor ;
		int height = original.getHeight() ;
		int width = original.getWidth();
		
		for( int y=0 ; y<height ; y++){
			for(int x=0 ; x<width ; x++){
				pixelColor = original.getPixel(x,y);
				A = Color.alpha(pixelColor);
				R = 255 - Color.red(pixelColor);
				G = 255 - Color.green(pixelColor);
				B = 255 - Color.blue(pixelColor);
				finalimage.setPixel(x,y, Color.argb(A,R,G,B));
			}
		}
		
		return finalimage;
	}
}
