package com.chaithu.phasorzandroid;

import java.io.ByteArrayOutputStream;

import android.app.Activity;
import android.app.Application;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.hardware.Camera;
import android.hardware.Camera.CameraInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

class MyApp extends Application{
	
	public static Bitmap photo ;
	
	public void setPhoto(Bitmap bmp){
		
		photo = bmp ;
	}
		
		
	}
	
	
	
	
	
	
public class second extends Activity {

    private static final int CAMERA_REQUEST = 1888; 
    private ImageView imageView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second);
        this.imageView = (ImageView)this.findViewById(R.id.imageView1);
        Button photoButton = (Button) this.findViewById(R.id.button1);
        photoButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE); 
                startActivityForResult(cameraIntent, CAMERA_REQUEST); 
            }
        });
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {  
        if (requestCode == CAMERA_REQUEST && resultCode == RESULT_OK) {
        	Bitmap photo = (Bitmap) data.getExtras().get("data");
        	MyApp app = new MyApp();
        	app.setPhoto(photo);
            Intent intent = new Intent(this,Third.class);
            startActivity(intent);
            
        }  
    } 
}


	
