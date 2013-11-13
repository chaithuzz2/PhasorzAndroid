package com.chaithu.phasorzandroid;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Random;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class Third extends Activity {
	
	public int x;
	public int y;
	public Paint paint;
	public Canvas canvas;
	public Bitmap mBitmap;
	public ImageView im1;
	public Bitmap newMap;
	public boolean DRAW = false;
	public Button Draw;
	public Button save;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.third);
        Draw = (Button) findViewById(R.id.button1);
        Button Undo = (Button) findViewById(R.id.button2);
        save = (Button) findViewById(R.id.button3);
        im1 = (ImageView) findViewById(R.id.imageView2);
        MyApp app1 = new MyApp();
    	paint = new Paint();
    	paint.setColor(Color.RED);
    	paint.setStyle(Paint.Style.STROKE);
        mBitmap =  app1.photo ;
        newMap = Bitmap.createBitmap(mBitmap);
        //im1.setAdjustViewBounds(true);
        canvas = new Canvas(newMap);
        im1.setImageBitmap(newMap);
        //im1.draw(canvas);
        Draw.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(DRAW == false){
					DRAW = true;
					Draw.setText("stop");
				}
				else{
					DRAW = false;
					Draw.setText("Draw");
				}
			}
		});
        im1.setOnTouchListener(new OnTouchListener() {
			
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				// TODO Auto-generated method stub
				if(DRAW == true){
				int radius = 20;
				x = (int) event.getRawX();
				y = (int) event.getRawY();
				float x1 = (float) (x*0.3);
				float y1 = (float) (y*0.25);
				canvas.drawCircle(x1, y1, radius, paint);
				im1.invalidate();
				}
				return true;
				
			}
		});
        Undo.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				MyApp app3 = new MyApp();
				im1.setImageBitmap(app3.photo);
		        Bitmap newMap1 = Bitmap.createBitmap(mBitmap);
		        canvas = new Canvas(newMap1);
		        im1.setAdjustViewBounds(true);
		        im1.setImageBitmap(newMap1);
			}
		});
        save.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				im1.buildDrawingCache();
				Bitmap bmap = im1.getDrawingCache();
				String root = Environment.getExternalStorageDirectory().toString();
				File myDir = new File(root + "/Dev");    
				myDir.mkdirs();
				Random generator = new Random();
				int n = 10000;
				n = generator.nextInt(n);
				String fname = "Image-"+ n +".png";
				File file = new File (myDir, fname);
				if (file.exists ()) file.delete (); 
				try {
				       FileOutputStream out = new FileOutputStream(file);
				       bmap.compress(Bitmap.CompressFormat.PNG, 90, out);
				       out.flush();
				       out.close();
						Toast.makeText(getBaseContext(), 
		                        "successfully saved to /Dev", 
		                        Toast.LENGTH_SHORT).show();

				} catch (Exception e) {
					Toast.makeText(getBaseContext(), 
	                        "error saving file", 
	                        Toast.LENGTH_SHORT).show();
				       e.printStackTrace();
				}
			}    
		});
        
        
		

     }
 
 
    
    

}
