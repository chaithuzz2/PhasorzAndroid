package com.chaithu.phasorzandroid;

import android.app.Activity;
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

public class second extends Activity {

	private final static String DEBUG_TAG = "MakePhotoActivity";
	private Camera camera;
	private int cameraId = 0;
	private ImageView im ;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.second);
		im = (ImageView) findViewById(R.id.imageView1);
	    // do we have a camera?
	    if (!getPackageManager()
	        .hasSystemFeature(PackageManager.FEATURE_CAMERA)) {
	      Toast.makeText(this, "No camera on this device", Toast.LENGTH_LONG)
	          .show();
	    } else {
	      cameraId = findFrontFacingCamera();
	      if (cameraId < 0) {
	        Toast.makeText(this, "No front facing camera found.",
	            Toast.LENGTH_LONG).show();
	      } else {
	        camera = Camera.open(cameraId);
	      }
	    }	
	    Button photoButton = (Button) this.findViewById(R.id.button1);
	    photoButton.setOnClickListener(new View.OnClickListener() {

	       @Override
	       public void onClick(View v) {
	            Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE); 
	            startActivityForResult(cameraIntent, 1888); 
	            }
	        });
	
	}
    @Override   
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {  
    	super.onActivityResult(requestCode, resultCode, data); 
    	if (requestCode == 1888 && resultCode == RESULT_OK) {  
	     Bitmap photo = (Bitmap) data.getExtras().get("data"); 
	     im.setImageBitmap(photo);
	     }  
	  } 	
	
 
	
	private int findFrontFacingCamera() {
	    int cameraId = -1;
	    // Search for the front facing camera
	    int numberOfCameras = Camera.getNumberOfCameras();
	    for (int i = 0; i < numberOfCameras; i++) {
	      CameraInfo info = new CameraInfo();
	      Camera.getCameraInfo(i, info);
	      if (info.facing == CameraInfo.CAMERA_FACING_FRONT) {
	        Log.d(DEBUG_TAG, "Camera found");
	        cameraId = i;
	        break;
	      }
	    }
	    return cameraId;
	  }
	  @Override
	  protected void onPause() {
	    if (camera != null) {
	      camera.release();
	      camera = null;
	    }
        super.onPause();
      }


}
